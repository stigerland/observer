package org.observertc.observer.evaluators.depots;

import org.observertc.observer.common.UUIDAdapter;
import org.observertc.observer.samples.ObservedSfuSample;
import org.observertc.schemas.reports.SfuOutboundRtpPadReport;
import org.observertc.schemas.samples.Samples;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;
import java.util.function.Supplier;

public class SfuOutboundRtpPadReportsDepot implements Supplier<List<SfuOutboundRtpPadReport>> {

    private static final Logger logger = LoggerFactory.getLogger(SfuOutboundRtpPadReportsDepot.class);

    private ObservedSfuSample observedSfuSample = null;
    private Samples.SfuSample.SfuOutboundRtpPad sfuOutboundRtpPad = null;
    private String callId = null;
    private String clientId = null;
    private String trackId = null;
    private List<SfuOutboundRtpPadReport> buffer = new LinkedList<>();


    public SfuOutboundRtpPadReportsDepot setObservedSfuSample(ObservedSfuSample value) {
        this.observedSfuSample = value;
        return this;
    }

    public SfuOutboundRtpPadReportsDepot setSfuOutboundRtpPad(Samples.SfuSample.SfuOutboundRtpPad value) {
        this.sfuOutboundRtpPad = value;
        return this;
    }

    public SfuOutboundRtpPadReportsDepot setCallId(UUID value) {
        if (Objects.isNull(value)) return this;
        this.callId = value.toString();
        return this;
    }

    public SfuOutboundRtpPadReportsDepot setClientId(UUID value) {
        if (Objects.isNull(value)) return this;
        this.clientId = value.toString();
        return this;
    }

    public SfuOutboundRtpPadReportsDepot setTrackId(UUID value) {
        if (Objects.isNull(value)) return this;
        this.trackId = value.toString();
        return this;
    }

    private SfuOutboundRtpPadReportsDepot clean() {
        this.observedSfuSample = null;
        this.sfuOutboundRtpPad = null;
        this.callId = null;
        this.clientId = null;
        this.trackId = null;
        return this;
    }

    public void assemble() {
        try {
            if (Objects.isNull(sfuOutboundRtpPad)) {
                logger.warn("Cannot assemble {} without sfuTransport", this.getClass().getSimpleName());
                return;
            }
            if (Objects.isNull(observedSfuSample)) {
                logger.warn("Cannot assemble {} without observedSfuSample", this.getClass().getSimpleName());
                return;
            }
            var sfuSample = observedSfuSample.getSfuSample();
            String transportId = UUIDAdapter.toStringOrNull(sfuOutboundRtpPad.transportId);
            String sfuId = UUIDAdapter.toStringOrNull(sfuSample.sfuId);
            String padId = UUIDAdapter.toStringOrNull(sfuOutboundRtpPad.padId);
            String streamId = UUIDAdapter.toStringOrNull(sfuOutboundRtpPad.streamId);
            String sinkId = UUIDAdapter.toStringOrNull(sfuOutboundRtpPad.sinkId);
            var report = SfuOutboundRtpPadReport.newBuilder()

                    /* Report MetaFields */
                    /* .setServiceId() // not given */
                    .setServiceId(observedSfuSample.getServiceId())
                    .setMediaUnitId(observedSfuSample.getMediaUnitId())
                    .setSfuId(sfuId)
                    .setMarker(sfuSample.marker)
                    .setTimestamp(sfuSample.timestamp)

                    /* Report Fields */
                    .setTransportId(transportId)
                    .setSfuStreamId(streamId)
                    .setSfuSinkId(sinkId)
                    .setRtpPadId(padId)
                    .setInternal(sfuOutboundRtpPad.internal)
                    .setSsrc(sfuOutboundRtpPad.ssrc)

                    .setTrackId(trackId)
                    .setClientId(clientId)
                    .setCallId(callId)

                    /* RTP Stats */
                    .setMediaType(sfuOutboundRtpPad.mediaType)
                    .setPayloadType(sfuOutboundRtpPad.payloadType)
                    .setMimeType(sfuOutboundRtpPad.mimeType)
                    .setClockRate(sfuOutboundRtpPad.clockRate)
                    .setSdpFmtpLine(sfuOutboundRtpPad.sdpFmtpLine)
                    .setRid(sfuOutboundRtpPad.rid)
                    .setRtxSsrc(sfuOutboundRtpPad.rtxSsrc)
                    .setTargetBitrate(sfuOutboundRtpPad.targetBitrate)
                    .setVoiceActivityFlag(sfuOutboundRtpPad.voiceActivityFlag)
                    .setFirCount(sfuOutboundRtpPad.firCount)
                    .setPliCount(sfuOutboundRtpPad.pliCount)
                    .setNackCount(sfuOutboundRtpPad.nackCount)
                    .setSliCount(sfuOutboundRtpPad.sliCount)
                    .setPacketsLost(sfuOutboundRtpPad.packetsLost)
                    .setPacketsSent(sfuOutboundRtpPad.packetsSent)
                    .setPacketsDiscarded(sfuOutboundRtpPad.packetsDiscarded)
                    .setPacketsRetransmitted(sfuOutboundRtpPad.packetsRetransmitted)
                    .setPacketsFailedEncryption(sfuOutboundRtpPad.packetsFailedEncryption)
                    .setPacketsDuplicated(sfuOutboundRtpPad.packetsDuplicated)
                    .setFecPacketsSent(sfuOutboundRtpPad.fecPacketsSent)
                    .setFecPacketsDiscarded(sfuOutboundRtpPad.fecPacketsDiscarded)
                    .setBytesSent(sfuOutboundRtpPad.bytesSent)
                    .setRtcpSrSent(sfuOutboundRtpPad.rtcpSrSent)
                    .setRtcpRrReceived(sfuOutboundRtpPad.rtcpRrReceived)
                    .setRtxPacketsSent(sfuOutboundRtpPad.rtxPacketsSent)
                    .setRtxPacketsDiscarded(sfuOutboundRtpPad.rtxPacketsDiscarded)
                    .setFramesSent(sfuOutboundRtpPad.framesSent)
                    .setFramesEncoded(sfuOutboundRtpPad.framesEncoded)
                    .setKeyFramesEncoded(sfuOutboundRtpPad.keyFramesEncoded)
                    .setRoundTripTime(sfuOutboundRtpPad.roundTripTime)

                    .build();
            this.buffer.add(report);
        } catch (Exception ex) {
            logger.error("Unexpected error occurred while assembling in {}", this.getClass().getSimpleName(), ex);
        } finally {
            this.clean();
        }

    }

    @Override
    public List<SfuOutboundRtpPadReport> get() {
        if (this.buffer.size() < 1) return Collections.EMPTY_LIST;
        var result = this.buffer;
        this.buffer = new LinkedList<>();
        return result;
    }
}
