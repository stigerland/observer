package org.observertc.observer.evaluators.listeners;

import io.micronaut.context.annotation.Prototype;
import org.observertc.observer.common.UUIDAdapter;
import org.observertc.observer.configs.ObserverConfig;
import org.observertc.observer.dto.SfuRtpPadDTO;
import org.observertc.observer.dto.SfuSinkDTO;
import org.observertc.observer.dto.SfuStreamDTO;
import org.observertc.observer.evaluators.listeners.attachments.RtpPadAttachment;
import org.observertc.observer.events.SfuEventType;
import org.observertc.observer.repositories.SfuRtpPadEvents;
import org.observertc.schemas.reports.SfuEventReport;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import java.util.List;
import java.util.Objects;

@Prototype
class SfuRtpPadRemoved extends EventReporterAbstract.SfuEventReporterAbstract<SfuRtpPadDTO> {

    private static final Logger logger = LoggerFactory.getLogger(SfuRtpPadRemoved.class);

    @Inject
    ObserverConfig observerConfig;

    @Inject
    SfuRtpPadEvents sfuRtpPadEvents;

    @PostConstruct
    void setup() {
        this.sfuRtpPadEvents
                .disposedSfuRtpPads()
                .subscribe(this::receiveDisposedSfuRtpPads);
    }

    private void receiveDisposedSfuRtpPads(List<SfuRtpPadEvents.Payload> payloads) {
        if (Objects.isNull(payloads) || payloads.size() < 1) {
            return;
        }
        for (var payload : payloads) {
            var report = this.makeReport(payload.sfuRtpPadDTO, payload.sfuStreamDTO, payload.sfuSinkDTO, payload.timestamp);
            this.forward(report);
        }
    }

    @Override
    protected SfuEventReport makeReport(SfuRtpPadDTO sfuRtpPadDTO, Long timestamp){
        logger.warn("SHould not happen");
        return null;
    }

    private SfuEventReport makeReport(SfuRtpPadDTO sfuRtpPad, SfuStreamDTO sfuStream, SfuSinkDTO sfuSink, Long timestamp) {
        try {
            var attachment = RtpPadAttachment.builder()
                    .withStreamDirection(sfuRtpPad.streamDirection)
                    .withInternal(sfuRtpPad.internal)
                    .build().toBase64();
            String sfuPadId = UUIDAdapter.toStringOrNull(sfuRtpPad.rtpPadId);
            String sfuPadStreamDirection = Objects.nonNull(sfuRtpPad.streamDirection) ? sfuRtpPad.streamDirection.toString() : "Unknown";
            String callId = null;
            String streamId = null;
            String sinkId = null;
            if (Objects.nonNull(sfuSink)) {
                callId = UUIDAdapter.toStringOrNull(sfuSink.callId);
                sinkId = UUIDAdapter.toStringOrNull(sfuSink.sfuSinkId);
            } else if (Objects.nonNull(sfuStream)) {
                callId = UUIDAdapter.toStringOrNull(sfuStream.callId);
                streamId = UUIDAdapter.toStringOrNull(sfuSink.sfuStreamId);
            }

            var builder = SfuEventReport.newBuilder()
                    .setName(SfuEventType.SFU_RTP_PAD_REMOVED.name())
                    .setSfuId(sfuRtpPad.sfuId.toString())
                    .setCallId(callId)
                    .setTransportId(sfuRtpPad.transportId.toString())
                    .setMediaStreamId(streamId)
                    .setMediaSinkId(sinkId)
                    .setRtpPadId(sfuPadId)
                    .setAttachments(attachment)
                    .setMessage("Sfu Rtp Pad is added")
                    .setServiceId(sfuRtpPad.serviceId)
                    .setMediaUnitId(sfuRtpPad.mediaUnitId)
                    .setTimestamp(timestamp);
            logger.info("SFU Pad (id: {}, streamId: {}, sinkId: {}) is REMOVED (mediaUnitId: {}, serviceId {}), direction is {}",
                    sfuPadId, sfuRtpPad.streamId, sfuRtpPad.sinkId, sfuRtpPad.mediaUnitId, sfuRtpPad.serviceId, sfuPadStreamDirection
            );
            return builder.build();
        } catch (Exception ex) {
            logger.warn("Unexpected exception occurred while making report", ex);
            return null;
        }
    }
}