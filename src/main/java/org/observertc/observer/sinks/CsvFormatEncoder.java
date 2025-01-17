package org.observertc.observer.sinks;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;
import org.observertc.observer.reports.Report;
import org.observertc.observer.reports.ReportType;
import org.observertc.observer.reports.ReportTypeVisitor;
import org.observertc.schemas.reports.csvsupport.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

import static java.util.stream.Collectors.groupingBy;

public class CsvFormatEncoder<K, V> implements FormatEncoder<K, V> {

    private static final Logger defaultLogger = LoggerFactory.getLogger(CsvFormatEncoder.class);

    private final ReportTypeVisitor<Report, Iterable<?>> mapper = ReportTypeVisitor.<Report, Iterable<?>>createFunctionalVisitor(
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

    private final CSVFormat format;
    private final int maxChunkSize;
    private final Function<ReportType, K> typeMapper;
    private final Function<String, V> formatMapper;
    private final Logger logger;

    public CsvFormatEncoder(int maxChunkSize, Function<ReportType, K> typeMapper, Function<String, V> formatMapper, CSVFormat format, Logger logger) {
        this.format = format;
        this.maxChunkSize = maxChunkSize;
        this.typeMapper = typeMapper;
        this.formatMapper = formatMapper;
        this.logger = logger;
    }

    @Override
    public Map<K, List<V>> map(List<Report> reports) {
        try {
            Map<K, List<V>> records = new HashMap<K, List<V>>();
            var stringBuilder = new StringBuffer();
            var csvPrinter = new CSVPrinter(stringBuilder, format);
            var chunkSize = 0;
            var reportsByTypes = reports.stream().collect(groupingBy(r -> r.type));
            for (var it = reportsByTypes.entrySet().iterator(); it.hasNext(); ) {
                var entry = it.next();
                var type = entry.getKey();
                var groupedReports = entry.getValue();
                for (var jt = groupedReports.iterator(); jt.hasNext(); ) {
                    var report = jt.next();
                    var iterable = mapper.apply(report, type);
                    csvPrinter.printRecord(iterable);
                    if (++chunkSize < maxChunkSize && jt.hasNext()) {
                        continue;
                    }
                    csvPrinter.flush();
                    var lines = stringBuilder.toString();
                    K mappedType = typeMapper.apply(report.type);
                    V myRecord = this.formatMapper.apply(lines);

                    if (mappedType == null) {
                        continue;
                    }
                    var mappedRecords = records.get(mappedType);
                    if (mappedRecords == null) {
                        mappedRecords = new LinkedList<>();
                        records.put(mappedType, mappedRecords);
                    }

                    mappedRecords.add(myRecord);
                    stringBuilder = new StringBuffer();
                    csvPrinter = new CSVPrinter(stringBuilder, CSVFormat.DEFAULT);
                    chunkSize = 0;
                }
            }
            logger.info("Received {} reports ({} types) mapped to {} different type of records", reports.size(), reportsByTypes.size(), records.size());
            return records;
        } catch (IOException e) {
            this.logger.warn("Exception occurred while encoding", e);
            return null;
        }
    }

}
