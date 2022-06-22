package org.observertc.observer.sinks.firehose;


import io.micronaut.context.annotation.Prototype;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;
import org.observertc.observer.common.AwsUtils;
import org.observertc.observer.common.JsonUtils;
import org.observertc.observer.common.Utils;
import org.observertc.observer.configbuilders.AbstractBuilder;
import org.observertc.observer.configbuilders.Builder;
import org.observertc.observer.configs.InvalidConfigurationException;
import org.observertc.observer.mappings.JsonMapper;
import org.observertc.observer.mappings.Mapper;
import org.observertc.observer.reports.Report;
import org.observertc.observer.reports.ReportType;
import org.observertc.observer.reports.ReportTypeVisitor;
import org.observertc.observer.security.credentialbuilders.AwsCredentialsProviderBuilder;
import org.observertc.observer.sinks.Sink;
import org.observertc.schemas.reports.csvsupport.*;
import software.amazon.awssdk.auth.credentials.AwsCredentialsProvider;
import software.amazon.awssdk.auth.credentials.DefaultCredentialsProvider;
import software.amazon.awssdk.core.SdkBytes;
import software.amazon.awssdk.services.firehose.FirehoseClient;
import software.amazon.awssdk.services.firehose.model.Record;

import javax.validation.constraints.NotNull;
import java.util.*;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collectors;

@Prototype
public class FirehoseSinkBuilder extends AbstractBuilder implements Builder<Sink> {

    private static AwsCredentialsProvider getCredentialProvider(Map<String, Object> config) {
        if (config == null) {
            logger.info("Default AWS credential is used for sink");
            return DefaultCredentialsProvider.create();
        }
        var providerBuilder = new AwsCredentialsProviderBuilder();
        providerBuilder.withConfiguration(config);
        return providerBuilder.build();
    }

    @Override
    public Sink build() {
        var config = this.convertAndValidate(Config.class);
        Supplier<FirehoseClient> clientProvider = () -> {
            var region = AwsUtils.getRegion(config.regionId);
            var credentialsProvider = getCredentialProvider(config.credentials);
            return FirehoseClient.builder()
                    .credentialsProvider(credentialsProvider)
                    .region(region)
                    .build();
        };
        var result = new FirehoseSink();
        if (config.defaultDeliveryStreamId == null && config.streams == null) {
            throw new InvalidConfigurationException("Either the default delivery stream id or delivery stream ids must be provided to configure the sink");
        }
        final Map<ReportType, String> deliveryStreamIds;
        if (config.streams != null) {
            deliveryStreamIds = config.streams.entrySet().stream()
                    .collect(Collectors.toMap(
                            entry -> ReportType.valueOf(Utils.camelCaseToSnakeCase(entry.getKey()).toUpperCase()),
                            Map.Entry::getValue
                    ));
            logger.info("Mappings: {}", JsonUtils.objectToString(deliveryStreamIds));
        } else {
            deliveryStreamIds = Collections.EMPTY_MAP;
        }

        Function<ReportType, String> getDeliveryStreamId;
        if (config.defaultDeliveryStreamId != null && config.streams != null) {
            getDeliveryStreamId = type -> deliveryStreamIds.getOrDefault(type, config.defaultDeliveryStreamId);
        } else if (config.streams != null) {
            getDeliveryStreamId = type -> deliveryStreamIds.get(type);
        } else {
            getDeliveryStreamId = type -> config.defaultDeliveryStreamId;
        }
        result.clientSupplier = clientProvider;
        switch (config.encodingType) {
            case CSV -> result.encoder = this.makeCsvEncoder(getDeliveryStreamId, config.csvFormat, config.csvChunkSize);
            case JSON -> result.encoder = this.makeJsonEncoder(getDeliveryStreamId);
        }
        return result;
    }

    private Mapper<List<Report>, Map<String, List<Record>>> makeJsonEncoder(Function<ReportType, String> getDeliveryStreamId) {
        var mapper = JsonMapper.createObjectToBytesMapper();
        return Mapper.create(reports -> {
            Map<String, List<Record>> records = new HashMap<>();
            for (var report : reports) {
                byte[] bytes = mapper.map(report.payload);
                if (bytes == null) {
                    logger.warn("Cannot map report {}", JsonUtils.objectToString(report));
                    continue;
                }

                Record myRecord = Record.builder()
                        .data(SdkBytes.fromByteArray(bytes))
                        .build();

                var deliveryStreamId = getDeliveryStreamId.apply(report.type);
                if (deliveryStreamId == null) {
                    continue;
                }
                var deliveryRecords = records.get(deliveryStreamId);
                if (deliveryRecords == null) {
                    deliveryRecords = new LinkedList<>();
                    records.put(deliveryStreamId, deliveryRecords);
                }
                deliveryRecords.add(myRecord);
            }
            return records;
        });
    }

    private Mapper<List<Report>, Map<String, List<Record>>> makeCsvEncoder(Function<ReportType, String> getDeliveryStreamId, CSVFormat format, int maxChunkSize) {
        var mapper = ReportTypeVisitor.<Report, Iterable<?>>createFunctionalVisitor(
                new ObserverEventReportToIterable(),
                new CallEventReportToIterable(),
                new CallMetaReportToIterable(),
                new ClientExtensionReportToIterable(),
                new ClientTransportReportToIterable(),
                new ClientDataChannelReportToIterable(),
                new InboundAudioTrackReportToIterable(),
                new InboundVideoTrackReportToIterable(),
                new OutboundAudioTrackReportToIterable(),
                new OutboundVideoTrackReportToIterable(),
                new SfuEventReportToIterable(),
                new SfuMetaReportToIterable(),
                new SfuExtensionReportToIterable(),
                new SFUTransportReportToIterable(),
                new SfuInboundRtpPadReportToIterable(),
                new SfuOutboundRtpPadReportToIterable(),
                new SfuSctpStreamReportToIterable()
        );
        return Mapper.create(reports -> {
            var records = new HashMap<String, List<Record>>();
            var stringBuilder = new StringBuffer();
            var csvPrinter = new CSVPrinter(stringBuilder, format);
            var chunkSize = 0;
            for (var it = reports.iterator(); it.hasNext(); ) {
                var report = it.next();
                var iterable = mapper.apply(report, report.type);
                csvPrinter.printRecord(iterable);
                if (++chunkSize < maxChunkSize && it.hasNext()) {
                    continue;
                }
                csvPrinter.flush();
                var lines = stringBuilder.toString();
                var bytes = lines.getBytes();
                Record myRecord = Record.builder()
                        .data(SdkBytes.fromByteArray(bytes))
                        .build();

                String deliveryStreamId = getDeliveryStreamId.apply(report.type);
                if (deliveryStreamId == null) {
                    continue;
                }
                var deliveryRecords = records.get(deliveryStreamId);
                if (deliveryRecords == null) {
                    deliveryRecords = new LinkedList<>();
                    records.put(deliveryStreamId, deliveryRecords);
                }

                deliveryRecords.add(myRecord);
                stringBuilder = new StringBuffer();
                csvPrinter = new CSVPrinter(stringBuilder, CSVFormat.DEFAULT);
                chunkSize = 0;
            }
            return records;
        });
    }

    public static class Config {

        public enum EncodingType {
            JSON,
            CSV
        }

        @NotNull
        public String regionId;

        public String defaultDeliveryStreamId;

        public Map<String, String> streams = null;

        public EncodingType encodingType = EncodingType.CSV;

        public Map<String, Object> credentials = null;

        public CSVFormat csvFormat = CSVFormat.DEFAULT;

        public int csvChunkSize = 100;

    }
}
