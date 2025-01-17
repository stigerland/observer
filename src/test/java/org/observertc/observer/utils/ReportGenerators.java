package org.observertc.observer.utils;

import org.observertc.observer.reports.Report;
import org.observertc.observer.reports.ReportType;
import org.observertc.observer.reports.ReportTypeVisitor;
import org.observertc.schemas.reports.*;

import java.util.List;
import java.util.UUID;

public class ReportGenerators {

    private final RandomGenerators randomGenerator = new RandomGenerators();

    public ReportGenerators() {
    }

    final ReportTypeVisitor<Object, Report> reportGenerator = ReportTypeVisitor.createFunctionalVisitor(
            obj -> Report.fromObserverEventReport(this.generateObserverEventReport()),
            obj -> Report.fromCallEventReport(this.generateCallEventReport()),
            obj -> Report.fromCallMetaReport(this.generateCallMetaReport()),
            obj -> Report.fromClientExtensionReport(this.generateClientExtensionReport()),
            obj -> Report.fromClientTransportReport(this.generateClientTransportReport()),
            obj -> Report.fromClientDataChannelReport(this.generateClientDataChannelReport()),
            obj -> Report.fromInboundAudioTrackReport(this.generateInboundAudioTrackReport()),
            obj -> Report.fromInboundVideoTrackReport(this.generateInboundVideoTrackReport()),
            obj -> Report.fromOutboundAudioTrackReport(this.getnerateOutboundAudioTrackReport()),
            obj -> Report.fromOutboundVideoTrackReport(this.generateOutboundVideoTrackReport()),
            obj -> Report.fromSfuEventReport(this.generateSfuEventReport()),
            obj -> Report.fromSfuMetaReport(this.generateSfuMetaReport()),
            obj -> Report.fromSfuExtensionReport(this.generateSfuExtensionReport()),
            obj -> Report.fromSfuTransportReport(this.generateSfuTransportReport()),
            obj -> Report.fromSfuInboundRtpPadReport(this.generateSfuInboundRtpPadReport()),
            obj -> Report.fromSfuOutboundRtpPadReport(this.generateSfuOutboundRtpPadReport()),
            obj -> Report.fromSfuSctpStreamReport(this.generateSfuSctpStreamReport())
    );

    public Report generateReport() {
        var reportType = this.randomGenerator.getRandomFromList(List.of(ReportType.values()));
        var report = reportGenerator.apply(null, reportType);
        return report;
    }


    public CallEventReport generateCallEventReport() {
        var callEventReport = new CallEventReport();
        callEventReport.mediaTrackId = UUID.randomUUID().toString();
        callEventReport.SSRC = this.randomGenerator.getRandomSSRC();
        callEventReport.name = this.randomGenerator.getRandomCallEventType().name();
        callEventReport.message = this.randomGenerator.getRandomString();
        callEventReport.value = this.randomGenerator.getRandomString();
        callEventReport.attachments = this.randomGenerator.getRandomString(128);
        callEventReport.sampleTimestamp = this.randomGenerator.getRandomTimestamp();
        callEventReport.sampleSeq = this.randomGenerator.getRandomPositiveInteger();
        callEventReport.serviceId = this.randomGenerator.getRandomServiceId();
        callEventReport.mediaUnitId = this.randomGenerator.getRandomSFUSideMediaUnitId();
        callEventReport.marker = this.randomGenerator.getRandomMarker();
        callEventReport.timestamp = this.randomGenerator.getRandomTimestamp();
        callEventReport.callId = UUID.randomUUID().toString();
        callEventReport.roomId = this.randomGenerator.getRandomTestUserIds();
        callEventReport.clientId = UUID.randomUUID().toString();
        callEventReport.userId = this.randomGenerator.getRandomTestUserIds();
        callEventReport.peerConnectionId = UUID.randomUUID().toString();
        callEventReport.sampleSeq = this.randomGenerator.getRandomPositiveInteger();
        return callEventReport;
    }

    public CallMetaReport generateCallMetaReport() {
        var callMetaReport = new CallMetaReport();
        callMetaReport.sampleTimestamp = this.randomGenerator.getRandomTimestamp();
        callMetaReport.sampleSeq = this.randomGenerator.getRandomPositiveInteger();
        callMetaReport.type = this.randomGenerator.getRandomString();
        callMetaReport.payload = this.randomGenerator.getRandomString(128);
        callMetaReport.serviceId = this.randomGenerator.getRandomServiceId();
        callMetaReport.mediaUnitId = this.randomGenerator.getRandomSFUSideMediaUnitId();
        callMetaReport.marker = this.randomGenerator.getRandomMarker();
        callMetaReport.timestamp = this.randomGenerator.getRandomTimestamp();
        callMetaReport.callId = UUID.randomUUID().toString();
        callMetaReport.roomId = this.randomGenerator.getRandomTestUserIds();
        callMetaReport.clientId = UUID.randomUUID().toString();
        callMetaReport.userId = this.randomGenerator.getRandomTestUserIds();
        callMetaReport.peerConnectionId = UUID.randomUUID().toString();
        callMetaReport.sampleSeq = this.randomGenerator.getRandomPositiveInteger();
        return callMetaReport;
    }

    public ClientDataChannelReport generateClientDataChannelReport() {
        var clientDataChannelReport = new ClientDataChannelReport();
        clientDataChannelReport.serviceId = this.randomGenerator.getRandomServiceId();
        clientDataChannelReport.mediaUnitId = this.randomGenerator.getRandomSFUSideMediaUnitId();
        clientDataChannelReport.marker = this.randomGenerator.getRandomMarker();
        clientDataChannelReport.timestamp = this.randomGenerator.getRandomTimestamp();
        clientDataChannelReport.callId = UUID.randomUUID().toString();
        clientDataChannelReport.roomId = this.randomGenerator.getRandomTestUserIds();
        clientDataChannelReport.clientId = UUID.randomUUID().toString();
        clientDataChannelReport.userId = this.randomGenerator.getRandomTestUserIds();
        clientDataChannelReport.peerConnectionId = UUID.randomUUID().toString();
        clientDataChannelReport.peerConnectionLabel = this.randomGenerator.getRandomPeerConnectionLabels();
        clientDataChannelReport.sampleSeq = this.randomGenerator.getRandomPositiveInteger();
        clientDataChannelReport.label = this.randomGenerator.getRandomString();
        clientDataChannelReport.protocol = this.randomGenerator.getRandomNetworkTransportProtocols();
        clientDataChannelReport.state = this.randomGenerator.getRandomDataChannelState();
        clientDataChannelReport.messagesSent = this.randomGenerator.getRandomPositiveInteger();
        clientDataChannelReport.bytesSent = this.randomGenerator.getRandomPositiveLong();
        clientDataChannelReport.messagesReceived = this.randomGenerator.getRandomPositiveInteger();
        clientDataChannelReport.bytesReceived = this.randomGenerator.getRandomPositiveLong();
        return clientDataChannelReport;
    }

    public ClientExtensionReport generateClientExtensionReport() {
        var clientExtensionReport = new ClientExtensionReport();
        clientExtensionReport.callId = UUID.randomUUID().toString();
        clientExtensionReport.roomId = this.randomGenerator.getRandomTestRoomIds();
        clientExtensionReport.clientId = UUID.randomUUID().toString();
        clientExtensionReport.userId = this.randomGenerator.getRandomTestUserIds();
        clientExtensionReport.peerConnectionId =  UUID.randomUUID().toString();
        clientExtensionReport.sampleSeq = this.randomGenerator.getRandomPositiveInteger();
        clientExtensionReport.serviceId = this.randomGenerator.getRandomServiceId();
        clientExtensionReport.mediaUnitId = this.randomGenerator.getRandomSFUSideMediaUnitId();
        clientExtensionReport.marker = this.randomGenerator.getRandomMarker();
        clientExtensionReport.timestamp = this.randomGenerator.getRandomTimestamp();
        clientExtensionReport.extensionType = this.randomGenerator.getRandomString();
        clientExtensionReport.payload = this.randomGenerator.getRandomString();
        return clientExtensionReport;
    }

    public ClientTransportReport generateClientTransportReport() {
        var clientTransportReport = new ClientTransportReport();
        clientTransportReport.serviceId = this.randomGenerator.getRandomServiceId();
        clientTransportReport.mediaUnitId = this.randomGenerator.getRandomClientSideMediaUnitId();
        clientTransportReport.marker = this.randomGenerator.getRandomMarker();
        clientTransportReport.timestamp = this.randomGenerator.getRandomTimestamp();
        clientTransportReport.callId = UUID.randomUUID().toString();
        clientTransportReport.roomId = this.randomGenerator.getRandomTestRoomIds();
        clientTransportReport.clientId = UUID.randomUUID().toString();
        clientTransportReport.userId = this.randomGenerator.getRandomTestUserIds();
        clientTransportReport.peerConnectionId = UUID.randomUUID().toString();
        clientTransportReport.iceTransportState = this.randomGenerator.getRandomIceState();
        clientTransportReport.peerConnectionId = UUID.randomUUID().toString();
        clientTransportReport.label = this.randomGenerator.getRandomPeerConnectionLabels();
        clientTransportReport.packetsSent = this.randomGenerator.getRandomPositiveInteger();
        clientTransportReport.packetsReceived = this.randomGenerator.getRandomPositiveInteger();
        clientTransportReport.bytesSent = this.randomGenerator.getRandomPositiveLong();
        clientTransportReport.bytesReceived = this.randomGenerator.getRandomPositiveLong();
        clientTransportReport.iceRole = this.randomGenerator.getRandomIceRole();
        clientTransportReport.iceLocalUsernameFragment = this.randomGenerator.getRandomString();
        clientTransportReport.dtlsState = this.randomGenerator.getRandomDtlsState();
        clientTransportReport.tlsVersion = this.randomGenerator.getRandomVersionNumber();
        clientTransportReport.dtlsCipher = this.randomGenerator.getRandomDtlsCipher();
        clientTransportReport.srtpCipher = this.randomGenerator.getRandomSrtpCipher();
        clientTransportReport.tlsGroup = this.randomGenerator.getRandomString();
        clientTransportReport.selectedCandidatePairChanges = this.randomGenerator.getRandomPositiveInteger();
        clientTransportReport.localAddress = this.randomGenerator.getRandomIPv4Address();
        clientTransportReport.localPort = this.randomGenerator.getRandomPort();
        clientTransportReport.localProtocol = this.randomGenerator.getRandomNetworkTransportProtocols();
        clientTransportReport.localCandidateType = this.randomGenerator.getRandomICECandidateTypes();
        clientTransportReport.localCandidateICEServerUrl = this.randomGenerator.getRandomIceUrl();
        clientTransportReport.localCandidateRelayProtocol = this.randomGenerator.getRandomRelayProtocols();
        clientTransportReport.remoteAddress = this.randomGenerator.getRandomIPv4Address();
        clientTransportReport.remotePort = this.randomGenerator.getRandomPort();
        clientTransportReport.remoteProtocol = this.randomGenerator.getRandomNetworkTransportProtocols();
        clientTransportReport.remoteCandidateType = this.randomGenerator.getRandomICECandidateTypes();
        clientTransportReport.remoteCandidateICEServerUrl = this.randomGenerator.getRandomIceUrl();
        clientTransportReport.remoteCandidateRelayProtocol = this.randomGenerator.getRandomRelayProtocols();
        clientTransportReport.candidatePairState = this.randomGenerator.getRandomCandidatePairState();
        clientTransportReport.candidatePairPacketsSent = this.randomGenerator.getRandomPositiveInteger();
        clientTransportReport.candidatePairPacketsReceived = this.randomGenerator.getRandomPositiveInteger();
        clientTransportReport.candidatePairBytesSent = this.randomGenerator.getRandomPositiveLong();
        clientTransportReport.candidatePairBytesReceived =this.randomGenerator.getRandomPositiveLong();
        clientTransportReport.candidatePairLastPacketSentTimestamp = this.randomGenerator.getRandomTimestamp();
        clientTransportReport.candidatePairLastPacketReceivedTimestamp = this.randomGenerator.getRandomTimestamp();
        clientTransportReport.candidatePairFirstRequestTimestamp = this.randomGenerator.getRandomTimestamp();
        clientTransportReport.candidatePairLastRequestTimestamp = this.randomGenerator.getRandomTimestamp();
        clientTransportReport.candidatePairLastResponseTimestamp = this.randomGenerator.getRandomTimestamp();
        clientTransportReport.candidatePairTotalRoundTripTime = this.randomGenerator.getRandomPositiveDouble();
        clientTransportReport.candidatePairCurrentRoundTripTime = this.randomGenerator.getRandomPositiveDouble();
        clientTransportReport.candidatePairAvailableOutgoingBitrate = this.randomGenerator.getRandomPositiveDouble();
        clientTransportReport.candidatePairAvailableIncomingBitrate = this.randomGenerator.getRandomPositiveDouble();
        clientTransportReport.candidatePairCircuitBreakerTriggerCount = this.randomGenerator.getRandomPositiveInteger();
        clientTransportReport.candidatePairRequestsReceived = this.randomGenerator.getRandomPositiveInteger();
        clientTransportReport.candidatePairRequestsSent = this.randomGenerator.getRandomPositiveInteger();
        clientTransportReport.candidatePairResponsesReceived = this.randomGenerator.getRandomPositiveInteger();
        clientTransportReport.candidatePairResponsesSent = this.randomGenerator.getRandomPositiveInteger();
        clientTransportReport.candidatePairRetransmissionReceived = this.randomGenerator.getRandomPositiveInteger();
        clientTransportReport.candidatePairRetransmissionSent = this.randomGenerator.getRandomPositiveInteger();
        clientTransportReport.candidatePairConsentRequestsSent = this.randomGenerator.getRandomPositiveInteger();
        clientTransportReport.candidatePairConsentExpiredTimestamp = this.randomGenerator.getRandomTimestamp();
        clientTransportReport.candidatePairBytesDiscardedOnSend =  this.randomGenerator.getRandomPositiveLong();
        clientTransportReport.candidatePairPacketsDiscardedOnSend = this.randomGenerator.getRandomPositiveInteger();
        clientTransportReport.candidatePairRequestBytesSent =  this.randomGenerator.getRandomPositiveLong();
        clientTransportReport.candidatePairConsentRequestBytesSent =  this.randomGenerator.getRandomPositiveLong();
        clientTransportReport.candidatePairResponseBytesSent =  this.randomGenerator.getRandomPositiveLong();
        clientTransportReport.sctpSmoothedRoundTripTime = this.randomGenerator.getRandomPositiveDouble();
        clientTransportReport.sctpCongestionWindow = this.randomGenerator.getRandomPositiveDouble();
        clientTransportReport.sctpReceiverWindow = this.randomGenerator.getRandomPositiveDouble();
        clientTransportReport.sctpMtu = this.randomGenerator.getRandomPositiveInteger();
        clientTransportReport.sctpUnackData = this.randomGenerator.getRandomPositiveInteger();
        return clientTransportReport;
    }

    public InboundAudioTrackReport generateInboundAudioTrackReport() {
        var inboundAudioTrackReport = new InboundAudioTrackReport();
        inboundAudioTrackReport.label = this.randomGenerator.getRandomString();
        inboundAudioTrackReport.sfuStreamId =UUID.randomUUID().toString();
        inboundAudioTrackReport.remoteTrackId = UUID.randomUUID().toString();
        inboundAudioTrackReport.remoteUserId = this.randomGenerator.getRandomTestUserIds();
        inboundAudioTrackReport.remotePeerConnectionId = UUID.randomUUID().toString();
        inboundAudioTrackReport.sampleSeq = this.randomGenerator.getRandomPositiveInteger();
        inboundAudioTrackReport.serviceId = this.randomGenerator.getRandomServiceId();
        inboundAudioTrackReport.mediaUnitId = this.randomGenerator.getRandomSFUSideMediaUnitId();
        inboundAudioTrackReport.marker = this.randomGenerator.getRandomMarker();
        inboundAudioTrackReport.timestamp = this.randomGenerator.getRandomTimestamp();
        inboundAudioTrackReport.clientId = UUID.randomUUID().toString();
        inboundAudioTrackReport.callId = UUID.randomUUID().toString();
        inboundAudioTrackReport.roomId = this.randomGenerator.getRandomTestRoomIds();
        inboundAudioTrackReport.userId = this.randomGenerator.getRandomTestUserIds();
        inboundAudioTrackReport.trackId = UUID.randomUUID().toString();
        inboundAudioTrackReport.peerConnectionId = UUID.randomUUID().toString();
        inboundAudioTrackReport.remoteClientId = UUID.randomUUID().toString();
        inboundAudioTrackReport.sfuSinkId = UUID.randomUUID().toString();
        inboundAudioTrackReport.trackId = UUID.randomUUID().toString();
        inboundAudioTrackReport.peerConnectionId = UUID.randomUUID().toString();
        inboundAudioTrackReport.remoteClientId = UUID.randomUUID().toString();
        inboundAudioTrackReport.sfuSinkId = UUID.randomUUID().toString();
        inboundAudioTrackReport.ssrc = this.randomGenerator.getRandomSSRC();
        inboundAudioTrackReport.packetsReceived = this.randomGenerator.getRandomPositiveInteger();
        inboundAudioTrackReport.packetsLost = this.randomGenerator.getRandomPositiveInteger();
        inboundAudioTrackReport.jitter = this.randomGenerator.getRandomPositiveDouble();
        inboundAudioTrackReport.packetsDiscarded = this.randomGenerator.getRandomPositiveInteger();
        inboundAudioTrackReport.packetsRepaired = this.randomGenerator.getRandomPositiveInteger();
        inboundAudioTrackReport.burstPacketsLost = this.randomGenerator.getRandomPositiveInteger();
        inboundAudioTrackReport.burstPacketsDiscarded = this.randomGenerator.getRandomPositiveInteger();
        inboundAudioTrackReport.burstLossCount = this.randomGenerator.getRandomPositiveInteger();
        inboundAudioTrackReport.burstDiscardCount = this.randomGenerator.getRandomPositiveInteger();
        inboundAudioTrackReport.burstLossRate = this.randomGenerator.getRandomPositiveDouble();
        inboundAudioTrackReport.burstDiscardRate = this.randomGenerator.getRandomPositiveDouble();
        inboundAudioTrackReport.gapLossRate = this.randomGenerator.getRandomPositiveDouble();
        inboundAudioTrackReport.gapDiscardRate = this.randomGenerator.getRandomPositiveDouble();
        inboundAudioTrackReport.lastPacketReceivedTimestamp = this.randomGenerator.getRandomTimestamp();
        inboundAudioTrackReport.averageRtcpInterval = this.randomGenerator.getRandomPositiveDouble();
        inboundAudioTrackReport.headerBytesReceived =  this.randomGenerator.getRandomPositiveLong();
        inboundAudioTrackReport.fecPacketsReceived = this.randomGenerator.getRandomPositiveInteger();
        inboundAudioTrackReport.fecPacketsDiscarded = this.randomGenerator.getRandomPositiveInteger();
        inboundAudioTrackReport.bytesReceived = this.randomGenerator.getRandomPositiveLong();
        inboundAudioTrackReport.packetsFailedDecryption = this.randomGenerator.getRandomPositiveInteger();
        inboundAudioTrackReport.packetsDuplicated = this.randomGenerator.getRandomPositiveInteger();
        inboundAudioTrackReport.perDscpPacketsReceived = this.randomGenerator.getRandomPositiveInteger();
        inboundAudioTrackReport.nackCount = this.randomGenerator.getRandomPositiveInteger();
        inboundAudioTrackReport.totalProcessingDelay = this.randomGenerator.getRandomPositiveDouble();
        inboundAudioTrackReport.estimatedPlayoutTimestamp = this.randomGenerator.getRandomTimestamp();
        inboundAudioTrackReport.jitterBufferDelay = this.randomGenerator.getRandomPositiveDouble();
        inboundAudioTrackReport.jitterBufferEmittedCount = this.randomGenerator.getRandomPositiveInteger();
        inboundAudioTrackReport.decoderImplementation = this.randomGenerator.getRandomCodecType();
        inboundAudioTrackReport.voiceActivityFlag = this.randomGenerator.getRandomBoolean();
        inboundAudioTrackReport.packetsSent = this.randomGenerator.getRandomPositiveInteger();
        inboundAudioTrackReport.bytesSent = this.randomGenerator.getRandomPositiveLong();
        inboundAudioTrackReport.remoteTimestamp = this.randomGenerator.getRandomTimestamp();
        inboundAudioTrackReport.reportsSent = this.randomGenerator.getRandomPositiveInteger();
        inboundAudioTrackReport.ended = this.randomGenerator.getRandomBoolean();
        inboundAudioTrackReport.payloadType = this.randomGenerator.getRandomPayloadType();
        inboundAudioTrackReport.mimeType = this.randomGenerator.getRandomString();
        inboundAudioTrackReport.clockRate = this.randomGenerator.getRandomClockRate();
        inboundAudioTrackReport.channels = this.randomGenerator.getRandomPositiveInteger();
        inboundAudioTrackReport.sdpFmtpLine = this.randomGenerator.getRandomString();

        return inboundAudioTrackReport;
    }


    public InboundVideoTrackReport generateInboundVideoTrackReport() {
        var inboundVideoTrackReport = new InboundVideoTrackReport();
        inboundVideoTrackReport.serviceId = this.randomGenerator.getRandomServiceId();
        inboundVideoTrackReport.mediaUnitId = this.randomGenerator.getRandomSFUSideMediaUnitId();
        inboundVideoTrackReport.marker = this.randomGenerator.getRandomMarker();
        inboundVideoTrackReport.timestamp = this.randomGenerator.getRandomTimestamp();
        inboundVideoTrackReport.clientId = UUID.randomUUID().toString();
        inboundVideoTrackReport.callId = UUID.randomUUID().toString();
        inboundVideoTrackReport.roomId = this.randomGenerator.getRandomTestRoomIds();
        inboundVideoTrackReport.userId = this.randomGenerator.getRandomTestUserIds();
        inboundVideoTrackReport.trackId = UUID.randomUUID().toString();
        inboundVideoTrackReport.peerConnectionId = UUID.randomUUID().toString();
        inboundVideoTrackReport.remoteClientId = UUID.randomUUID().toString();
        inboundVideoTrackReport.sfuSinkId = UUID.randomUUID().toString();
        inboundVideoTrackReport.ssrc = this.randomGenerator.getRandomSSRC();
        inboundVideoTrackReport.packetsReceived = this.randomGenerator.getRandomPositiveInteger();
        inboundVideoTrackReport.packetsLost = this.randomGenerator.getRandomPositiveInteger();
        inboundVideoTrackReport.jitter = this.randomGenerator.getRandomPositiveDouble();
        inboundVideoTrackReport.packetsDiscarded = this.randomGenerator.getRandomPositiveInteger();
        inboundVideoTrackReport.packetsRepaired = this.randomGenerator.getRandomPositiveInteger();
        inboundVideoTrackReport.burstPacketsLost = this.randomGenerator.getRandomPositiveInteger();
        inboundVideoTrackReport.burstPacketsDiscarded = this.randomGenerator.getRandomPositiveInteger();
        inboundVideoTrackReport.burstLossCount = this.randomGenerator.getRandomPositiveInteger();
        inboundVideoTrackReport.burstDiscardCount = this.randomGenerator.getRandomPositiveInteger();
        inboundVideoTrackReport.burstLossRate = this.randomGenerator.getRandomPositiveDouble();
        inboundVideoTrackReport.burstDiscardRate = this.randomGenerator.getRandomPositiveDouble();
        inboundVideoTrackReport.gapLossRate = this.randomGenerator.getRandomPositiveDouble();
        inboundVideoTrackReport.gapDiscardRate = this.randomGenerator.getRandomPositiveDouble();
        inboundVideoTrackReport.lastPacketReceivedTimestamp = this.randomGenerator.getRandomTimestamp();
        inboundVideoTrackReport.averageRtcpInterval = this.randomGenerator.getRandomPositiveDouble();
        inboundVideoTrackReport.headerBytesReceived =  this.randomGenerator.getRandomPositiveLong();
        inboundVideoTrackReport.fecPacketsReceived = this.randomGenerator.getRandomPositiveInteger();
        inboundVideoTrackReport.fecPacketsDiscarded = this.randomGenerator.getRandomPositiveInteger();
        inboundVideoTrackReport.bytesReceived = this.randomGenerator.getRandomPositiveLong();
        inboundVideoTrackReport.packetsFailedDecryption = this.randomGenerator.getRandomPositiveInteger();
        inboundVideoTrackReport.packetsDuplicated = this.randomGenerator.getRandomPositiveInteger();
        inboundVideoTrackReport.perDscpPacketsReceived = this.randomGenerator.getRandomPositiveInteger();
        inboundVideoTrackReport.nackCount = this.randomGenerator.getRandomPositiveInteger();
        inboundVideoTrackReport.totalProcessingDelay = this.randomGenerator.getRandomPositiveDouble();
        inboundVideoTrackReport.estimatedPlayoutTimestamp = this.randomGenerator.getRandomTimestamp();
        inboundVideoTrackReport.jitterBufferDelay = this.randomGenerator.getRandomPositiveDouble();
        inboundVideoTrackReport.jitterBufferEmittedCount = this.randomGenerator.getRandomPositiveInteger();
        inboundVideoTrackReport.decoderImplementation = this.randomGenerator.getRandomCodecType();
        inboundVideoTrackReport.framesDropped = this.randomGenerator.getRandomPositiveInteger();
        inboundVideoTrackReport.framesDecoded = this.randomGenerator.getRandomPositiveInteger();
        inboundVideoTrackReport.partialFramesLost = this.randomGenerator.getRandomPositiveInteger();
        inboundVideoTrackReport.fullFramesLost = this.randomGenerator.getRandomPositiveInteger();
        inboundVideoTrackReport.keyFramesDecoded = this.randomGenerator.getRandomPositiveInteger();
        inboundVideoTrackReport.frameWidth = this.randomGenerator.getRandomPositiveInteger();
        inboundVideoTrackReport.frameHeight = this.randomGenerator.getRandomPositiveInteger();
        inboundVideoTrackReport.frameBitDepth = this.randomGenerator.getRandomPositiveInteger();
        inboundVideoTrackReport.framesPerSecond = this.randomGenerator.getRandomPositiveDouble();
        inboundVideoTrackReport.qpSum = this.randomGenerator.getRandomPositiveLong();
        inboundVideoTrackReport.totalDecodeTime = this.randomGenerator.getRandomPositiveDouble();
        inboundVideoTrackReport.totalInterFrameDelay = this.randomGenerator.getRandomPositiveDouble();
        inboundVideoTrackReport.totalSquaredInterFrameDelay = this.randomGenerator.getRandomPositiveDouble();
        inboundVideoTrackReport.firCount = this.randomGenerator.getRandomPositiveInteger();
        inboundVideoTrackReport.pliCount = this.randomGenerator.getRandomPositiveInteger();
        inboundVideoTrackReport.sliCount = this.randomGenerator.getRandomPositiveInteger();
        inboundVideoTrackReport.framesReceived = this.randomGenerator.getRandomPositiveInteger();
        inboundVideoTrackReport.packetsSent = this.randomGenerator.getRandomPositiveInteger();
        inboundVideoTrackReport.bytesSent = this.randomGenerator.getRandomPositiveLong();
        inboundVideoTrackReport.remoteTimestamp = this.randomGenerator.getRandomTimestamp();
        inboundVideoTrackReport.reportsSent = this.randomGenerator.getRandomPositiveInteger();
        inboundVideoTrackReport.ended = this.randomGenerator.getRandomBoolean();
        inboundVideoTrackReport.payloadType = this.randomGenerator.getRandomPayloadType();
        inboundVideoTrackReport.mimeType = this.randomGenerator.getRandomString();
        inboundVideoTrackReport.clockRate = this.randomGenerator.getRandomClockRate();
        inboundVideoTrackReport.sdpFmtpLine = this.randomGenerator.getRandomString();
        inboundVideoTrackReport.label = this.randomGenerator.getRandomString();
        inboundVideoTrackReport.sfuStreamId = UUID.randomUUID().toString();
        inboundVideoTrackReport.sfuSinkId = UUID.randomUUID().toString();
        inboundVideoTrackReport.remoteTrackId = UUID.randomUUID().toString();
        inboundVideoTrackReport.remoteUserId = this.randomGenerator.getRandomTestUserIds();
        inboundVideoTrackReport.remoteClientId = UUID.randomUUID().toString();
        inboundVideoTrackReport.remotePeerConnectionId = UUID.randomUUID().toString();
        inboundVideoTrackReport.sampleSeq = this.randomGenerator.getRandomPositiveInteger();
        inboundVideoTrackReport.lastPacketReceivedTimestamp = this.randomGenerator.getRandomTimestamp();
        return inboundVideoTrackReport;
    }

    public ObserverEventReport generateObserverEventReport() {
        var observerEventReport = new ObserverEventReport();
        observerEventReport.serviceId = this.randomGenerator.getRandomServiceId();
        observerEventReport.mediaUnitId = this.randomGenerator.getRandomSFUSideMediaUnitId();
        observerEventReport.marker = this.randomGenerator.getRandomMarker();
        observerEventReport.timestamp = this.randomGenerator.getRandomTimestamp();
        observerEventReport.clientId = UUID.randomUUID().toString();
        observerEventReport.callId = UUID.randomUUID().toString();
        observerEventReport.roomId = this.randomGenerator.getRandomTestRoomIds();
        observerEventReport.userId = this.randomGenerator.getRandomTestUserIds();
        observerEventReport.peerConnectionId = UUID.randomUUID().toString();
        observerEventReport.sampleTimestamp = this.randomGenerator.getRandomTimestamp();
        observerEventReport.sampleSeq = this.randomGenerator.getRandomPositiveInteger();
        observerEventReport.name = this.randomGenerator.getRandomString();
        observerEventReport.message = this.randomGenerator.getRandomString();
        observerEventReport.value = this.randomGenerator.getRandomString();
        observerEventReport.attachments = this.randomGenerator.getRandomString();
        return observerEventReport;
    }

    public OutboundAudioTrackReport getnerateOutboundAudioTrackReport() {
        var outboundAudioTrackReport = new OutboundAudioTrackReport();
        outboundAudioTrackReport.serviceId = this.randomGenerator.getRandomServiceId();
        outboundAudioTrackReport.mediaUnitId = this.randomGenerator.getRandomSFUSideMediaUnitId();
        outboundAudioTrackReport.marker = this.randomGenerator.getRandomMarker();
        outboundAudioTrackReport.timestamp = this.randomGenerator.getRandomTimestamp();
        outboundAudioTrackReport.clientId = UUID.randomUUID().toString();
        outboundAudioTrackReport.callId = UUID.randomUUID().toString();
        outboundAudioTrackReport.roomId = this.randomGenerator.getRandomTestRoomIds();
        outboundAudioTrackReport.userId = this.randomGenerator.getRandomTestUserIds();
        outboundAudioTrackReport.trackId = UUID.randomUUID().toString();
        outboundAudioTrackReport.peerConnectionId = UUID.randomUUID().toString();
        outboundAudioTrackReport.sfuStreamId = UUID.randomUUID().toString();
        outboundAudioTrackReport.label = this.randomGenerator.getRandomString();
        outboundAudioTrackReport.sampleSeq = this.randomGenerator.getRandomPositiveInteger();
        outboundAudioTrackReport.trackId = UUID.randomUUID().toString();
        outboundAudioTrackReport.peerConnectionId = UUID.randomUUID().toString();
        outboundAudioTrackReport.sfuStreamId = UUID.randomUUID().toString();
        outboundAudioTrackReport.ssrc =this.randomGenerator.getRandomSSRC();
        outboundAudioTrackReport.packetsSent = this.randomGenerator.getRandomPositiveInteger();
        outboundAudioTrackReport.bytesSent = this.randomGenerator.getRandomPositiveLong();
        outboundAudioTrackReport.rid = this.randomGenerator.getRandomString();
        outboundAudioTrackReport.lastPacketSentTimestamp = this.randomGenerator.getRandomTimestamp();
        outboundAudioTrackReport.headerBytesSent = this.randomGenerator.getRandomPositiveLong();
        outboundAudioTrackReport.packetsDiscardedOnSend = this.randomGenerator.getRandomPositiveInteger();
        outboundAudioTrackReport.bytesDiscardedOnSend = this.randomGenerator.getRandomPositiveLong();
        outboundAudioTrackReport.fecPacketsSent = this.randomGenerator.getRandomPositiveInteger();
        outboundAudioTrackReport.retransmittedPacketsSent = this.randomGenerator.getRandomPositiveInteger();
        outboundAudioTrackReport.retransmittedBytesSent =  this.randomGenerator.getRandomPositiveLong();
        outboundAudioTrackReport.targetBitrate = this.randomGenerator.getRandomPositiveInteger();
        outboundAudioTrackReport.totalEncodedBytesTarget = this.randomGenerator.getRandomPositiveLong();
        outboundAudioTrackReport.totalPacketSendDelay = this.randomGenerator.getRandomPositiveDouble();
        outboundAudioTrackReport.averageRtcpInterval = this.randomGenerator.getRandomPositiveDouble();
        outboundAudioTrackReport.perDscpPacketsSent = this.randomGenerator.getRandomPositiveInteger();
        outboundAudioTrackReport.nackCount = this.randomGenerator.getRandomPositiveInteger();
        outboundAudioTrackReport.encoderImplementation = this.randomGenerator.getRandomCodecType();
        outboundAudioTrackReport.totalSamplesSent = this.randomGenerator.getRandomPositiveInteger();
        outboundAudioTrackReport.samplesEncodedWithSilk = this.randomGenerator.getRandomPositiveInteger();
        outboundAudioTrackReport.samplesEncodedWithCelt = this.randomGenerator.getRandomPositiveInteger();
        outboundAudioTrackReport.voiceActivityFlag = this.randomGenerator.getRandomBoolean();
        outboundAudioTrackReport.packetsReceived = this.randomGenerator.getRandomPositiveInteger();
        outboundAudioTrackReport.packetsLost = this.randomGenerator.getRandomPositiveInteger();
        outboundAudioTrackReport.jitter = this.randomGenerator.getRandomPositiveDouble();
        outboundAudioTrackReport.packetsDiscarded = this.randomGenerator.getRandomPositiveInteger();
        outboundAudioTrackReport.packetsRepaired = this.randomGenerator.getRandomPositiveInteger();
        outboundAudioTrackReport.burstPacketsLost = this.randomGenerator.getRandomPositiveInteger();
        outboundAudioTrackReport.burstPacketsDiscarded = this.randomGenerator.getRandomPositiveInteger();
        outboundAudioTrackReport.burstLossCount = this.randomGenerator.getRandomPositiveInteger();
        outboundAudioTrackReport.burstDiscardCount = this.randomGenerator.getRandomPositiveInteger();
        outboundAudioTrackReport.burstLossRate = this.randomGenerator.getRandomPositiveDouble();
        outboundAudioTrackReport.burstDiscardRate = this.randomGenerator.getRandomPositiveDouble();
        outboundAudioTrackReport.gapLossRate = this.randomGenerator.getRandomPositiveDouble();
        outboundAudioTrackReport.gapDiscardRate = this.randomGenerator.getRandomPositiveDouble();
        outboundAudioTrackReport.roundTripTime = this.randomGenerator.getRandomPositiveDouble();
        outboundAudioTrackReport.totalRoundTripTime = this.randomGenerator.getRandomPositiveDouble();
        outboundAudioTrackReport.fractionLost = this.randomGenerator.getRandomPositiveDouble();
        outboundAudioTrackReport.reportsReceived = this.randomGenerator.getRandomPositiveInteger();
        outboundAudioTrackReport.roundTripTimeMeasurements = this.randomGenerator.getRandomPositiveInteger();
        outboundAudioTrackReport.relayedSource = this.randomGenerator.getRandomBoolean();
        outboundAudioTrackReport.audioLevel = this.randomGenerator.getRandomPositiveDouble();
        outboundAudioTrackReport.totalAudioEnergy = this.randomGenerator.getRandomPositiveDouble();
        outboundAudioTrackReport.totalSamplesDuration = this.randomGenerator.getRandomPositiveDouble();
        outboundAudioTrackReport.echoReturnLoss = this.randomGenerator.getRandomPositiveDouble();
        outboundAudioTrackReport.echoReturnLossEnhancement = this.randomGenerator.getRandomPositiveDouble();
        outboundAudioTrackReport.ended = this.randomGenerator.getRandomBoolean();
        outboundAudioTrackReport.payloadType = this.randomGenerator.getRandomPayloadType();
        outboundAudioTrackReport.mimeType = this.randomGenerator.getRandomString();
        outboundAudioTrackReport.clockRate = this.randomGenerator.getRandomClockRate();
        outboundAudioTrackReport.channels = this.randomGenerator.getRandomPositiveInteger();
        outboundAudioTrackReport.sdpFmtpLine = this.randomGenerator.getRandomString();
        return outboundAudioTrackReport;
    }

    public OutboundVideoTrackReport generateOutboundVideoTrackReport() {
        var outboundVideoTrackReport = new OutboundVideoTrackReport();
        outboundVideoTrackReport.serviceId = this.randomGenerator.getRandomServiceId();
        outboundVideoTrackReport.mediaUnitId = this.randomGenerator.getRandomSFUSideMediaUnitId();
        outboundVideoTrackReport.marker = this.randomGenerator.getRandomMarker();
        outboundVideoTrackReport.timestamp = this.randomGenerator.getRandomTimestamp();
        outboundVideoTrackReport.clientId = UUID.randomUUID().toString();
        outboundVideoTrackReport.callId = UUID.randomUUID().toString();
        outboundVideoTrackReport.peerConnectionId = UUID.randomUUID().toString();
        outboundVideoTrackReport.roomId = this.randomGenerator.getRandomTestRoomIds();
        outboundVideoTrackReport.userId = this.randomGenerator.getRandomTestUserIds();
        outboundVideoTrackReport.trackId = UUID.randomUUID().toString();
        outboundVideoTrackReport.peerConnectionId = UUID.randomUUID().toString();
        outboundVideoTrackReport.sfuStreamId = UUID.randomUUID().toString();
        outboundVideoTrackReport.ssrc = this.randomGenerator.getRandomSSRC();
        outboundVideoTrackReport.packetsSent = this.randomGenerator.getRandomPositiveInteger();
        outboundVideoTrackReport.bytesSent = this.randomGenerator.getRandomPositiveLong();
        outboundVideoTrackReport.rid = this.randomGenerator.getRandomString();
        outboundVideoTrackReport.lastPacketSentTimestamp = this.randomGenerator.getRandomTimestamp();
        outboundVideoTrackReport.headerBytesSent = this.randomGenerator.getRandomPositiveLong();
        outboundVideoTrackReport.label = this.randomGenerator.getRandomString();
        outboundVideoTrackReport.sampleSeq = this.randomGenerator.getRandomPositiveInteger();
        outboundVideoTrackReport.packetsDiscardedOnSend = this.randomGenerator.getRandomPositiveInteger();
        outboundVideoTrackReport.bytesDiscardedOnSend = this.randomGenerator.getRandomPositiveLong();
        outboundVideoTrackReport.fecPacketsSent = this.randomGenerator.getRandomPositiveInteger();
        outboundVideoTrackReport.retransmittedPacketsSent = this.randomGenerator.getRandomPositiveInteger();
        outboundVideoTrackReport.retransmittedBytesSent =  this.randomGenerator.getRandomPositiveLong();
        outboundVideoTrackReport.targetBitrate = this.randomGenerator.getRandomPositiveInteger();
        outboundVideoTrackReport.totalEncodedBytesTarget = this.randomGenerator.getRandomPositiveLong();
        outboundVideoTrackReport.totalPacketSendDelay = this.randomGenerator.getRandomPositiveDouble();
        outboundVideoTrackReport.averageRtcpInterval = this.randomGenerator.getRandomPositiveDouble();
        outboundVideoTrackReport.perDscpPacketsSent = this.randomGenerator.getRandomPositiveInteger();
        outboundVideoTrackReport.nackCount = this.randomGenerator.getRandomPositiveInteger();
        outboundVideoTrackReport.encoderImplementation = this.randomGenerator.getRandomCodecType();
        outboundVideoTrackReport.packetsReceived = this.randomGenerator.getRandomPositiveInteger();
        outboundVideoTrackReport.packetsLost = this.randomGenerator.getRandomPositiveInteger();
        outboundVideoTrackReport.jitter = this.randomGenerator.getRandomPositiveDouble();
        outboundVideoTrackReport.packetsDiscarded = this.randomGenerator.getRandomPositiveInteger();
        outboundVideoTrackReport.packetsRepaired = this.randomGenerator.getRandomPositiveInteger();
        outboundVideoTrackReport.burstPacketsLost = this.randomGenerator.getRandomPositiveInteger();
        outboundVideoTrackReport.burstPacketsDiscarded = this.randomGenerator.getRandomPositiveInteger();
        outboundVideoTrackReport.burstLossCount = this.randomGenerator.getRandomPositiveInteger();
        outboundVideoTrackReport.burstDiscardCount = this.randomGenerator.getRandomPositiveInteger();
        outboundVideoTrackReport.burstLossRate = this.randomGenerator.getRandomPositiveDouble();
        outboundVideoTrackReport.burstDiscardRate = this.randomGenerator.getRandomPositiveDouble();
        outboundVideoTrackReport.gapLossRate = this.randomGenerator.getRandomPositiveDouble();
        outboundVideoTrackReport.roundTripTime = this.randomGenerator.getRandomPositiveDouble();
        outboundVideoTrackReport.totalRoundTripTime = this.randomGenerator.getRandomPositiveDouble();
        outboundVideoTrackReport.fractionLost = this.randomGenerator.getRandomPositiveDouble();
        outboundVideoTrackReport.reportsReceived = this.randomGenerator.getRandomPositiveInteger();
        outboundVideoTrackReport.roundTripTimeMeasurements = this.randomGenerator.getRandomPositiveInteger();
        outboundVideoTrackReport.relayedSource = this.randomGenerator.getRandomBoolean();
        outboundVideoTrackReport.ended = this.randomGenerator.getRandomBoolean();
        outboundVideoTrackReport.payloadType = this.randomGenerator.getRandomPayloadType();
        outboundVideoTrackReport.mimeType = this.randomGenerator.getRandomString();
        outboundVideoTrackReport.clockRate = this.randomGenerator.getRandomClockRate();
        outboundVideoTrackReport.channels = this.randomGenerator.getRandomPositiveInteger();
        outboundVideoTrackReport.sdpFmtpLine = this.randomGenerator.getRandomString();
        outboundVideoTrackReport.frameWidth = this.randomGenerator.getRandomPositiveInteger();
        outboundVideoTrackReport.frameHeight = this.randomGenerator.getRandomPositiveInteger();
        outboundVideoTrackReport.frameBitDepth = this.randomGenerator.getRandomPositiveInteger();
        outboundVideoTrackReport.framesPerSecond = this.randomGenerator.getRandomPositiveDouble();
        outboundVideoTrackReport.framesSent = this.randomGenerator.getRandomPositiveInteger();
        outboundVideoTrackReport.hugeFramesSent = this.randomGenerator.getRandomPositiveInteger();
        outboundVideoTrackReport.framesEncoded = this.randomGenerator.getRandomPositiveInteger();
        outboundVideoTrackReport.keyFramesEncoded = this.randomGenerator.getRandomPositiveInteger();
        outboundVideoTrackReport.framesDiscardedOnSend = this.randomGenerator.getRandomPositiveInteger();
        outboundVideoTrackReport.qpSum = this.randomGenerator.getRandomPositiveLong();
        outboundVideoTrackReport.totalEncodeTime = this.randomGenerator.getRandomPositiveDouble();
        outboundVideoTrackReport.qualityLimitationDurationCPU = this.randomGenerator.getRandomPositiveDouble();
        outboundVideoTrackReport.qualityLimitationDurationNone = this.randomGenerator.getRandomPositiveDouble();
        outboundVideoTrackReport.qualityLimitationDurationBandwidth = this.randomGenerator.getRandomPositiveDouble();
        outboundVideoTrackReport.qualityLimitationDurationOther = this.randomGenerator.getRandomPositiveDouble();
        outboundVideoTrackReport.qualityLimitationReason = this.randomGenerator.getRandomQualityLimitationReason();
        outboundVideoTrackReport.qualityLimitationResolutionChanges = this.randomGenerator.getRandomPositiveInteger();
        outboundVideoTrackReport.firCount = this.randomGenerator.getRandomPositiveInteger();
        outboundVideoTrackReport.pliCount = this.randomGenerator.getRandomPositiveInteger();
        outboundVideoTrackReport.sliCount = this.randomGenerator.getRandomPositiveInteger();
        outboundVideoTrackReport.framesDropped = this.randomGenerator.getRandomPositiveInteger();
        outboundVideoTrackReport.partialFramesLost = this.randomGenerator.getRandomPositiveInteger();
        outboundVideoTrackReport.fullFramesLost = this.randomGenerator.getRandomPositiveInteger();
        outboundVideoTrackReport.encodedFrameWidth = this.randomGenerator.getRandomPositiveInteger();
        outboundVideoTrackReport.encodedFrameHeight = this.randomGenerator.getRandomPositiveInteger();
        outboundVideoTrackReport.encodedFrameBitDepth = this.randomGenerator.getRandomPositiveInteger();
        outboundVideoTrackReport.encodedFramesPerSecond = this.randomGenerator.getRandomPositiveDouble();
        return outboundVideoTrackReport;
    }


    public SFUTransportReport generateSfuTransportReport() {
        var sFUTransportReport = new SFUTransportReport();
        sFUTransportReport.serviceId = this.randomGenerator.getRandomServiceId();
        sFUTransportReport.mediaUnitId = this.randomGenerator.getRandomSFUSideMediaUnitId();
        sFUTransportReport.marker = this.randomGenerator.getRandomMarker();
        sFUTransportReport.timestamp = this.randomGenerator.getRandomTimestamp();
        sFUTransportReport.sfuId = UUID.randomUUID().toString();
        sFUTransportReport.callId = UUID.randomUUID().toString();
        sFUTransportReport.transportId = UUID.randomUUID().toString();
        sFUTransportReport.roomId = this.randomGenerator.getRandomTestRoomIds();
        sFUTransportReport.dtlsState = this.randomGenerator.getRandomDtlsState();
        sFUTransportReport.iceState = this.randomGenerator.getRandomIceState();
        sFUTransportReport.sctpState = this.randomGenerator.getRandomDataChannelState();
        sFUTransportReport.iceRole = this.randomGenerator.getRandomIceRole();
        sFUTransportReport.localAddress = this.randomGenerator.getRandomIPv4Address();
        sFUTransportReport.localPort = this.randomGenerator.getRandomPort();
        sFUTransportReport.protocol = this.randomGenerator.getRandomNetworkTransportProtocols();
        sFUTransportReport.remoteAddress = this.randomGenerator.getRandomIPv4Address();
        sFUTransportReport.remotePort = this.randomGenerator.getRandomPort();
        sFUTransportReport.rtpBytesReceived = this.randomGenerator.getRandomPositiveLong();
        sFUTransportReport.rtpBytesSent = this.randomGenerator.getRandomPositiveLong();
        sFUTransportReport.rtpPacketsReceived = this.randomGenerator.getRandomPositiveInteger();
        sFUTransportReport.rtpPacketsSent = this.randomGenerator.getRandomPositiveInteger();
        sFUTransportReport.rtpPacketsLost = this.randomGenerator.getRandomPositiveInteger();
        sFUTransportReport.rtxBytesReceived = this.randomGenerator.getRandomPositiveLong();
        sFUTransportReport.rtxBytesSent = this.randomGenerator.getRandomPositiveLong();
        sFUTransportReport.rtxPacketsReceived = this.randomGenerator.getRandomPositiveInteger();
        sFUTransportReport.rtxPacketsSent = this.randomGenerator.getRandomPositiveInteger();
        sFUTransportReport.rtxPacketsLost = this.randomGenerator.getRandomPositiveInteger();
        sFUTransportReport.rtxPacketsDiscarded = this.randomGenerator.getRandomPositiveInteger();
        sFUTransportReport.sctpBytesReceived = this.randomGenerator.getRandomPositiveLong();
        sFUTransportReport.sctpBytesSent = this.randomGenerator.getRandomPositiveLong();
        sFUTransportReport.sctpPacketsReceived = this.randomGenerator.getRandomPositiveInteger();
        sFUTransportReport.sctpPacketsSent = this.randomGenerator.getRandomPositiveInteger();
        return sFUTransportReport;
    }

    public SfuEventReport generateSfuEventReport() {
        var sfuEventReport = new SfuEventReport();
        sfuEventReport.serviceId = this.randomGenerator.getRandomServiceId();
        sfuEventReport.mediaUnitId = this.randomGenerator.getRandomSFUSideMediaUnitId();
        sfuEventReport.marker = this.randomGenerator.getRandomMarker();
        sfuEventReport.timestamp = this.randomGenerator.getRandomTimestamp();
        sfuEventReport.sfuId = UUID.randomUUID().toString();
        sfuEventReport.callId = UUID.randomUUID().toString();
        sfuEventReport.transportId = UUID.randomUUID().toString();
        sfuEventReport.mediaStreamId = UUID.randomUUID().toString();
        sfuEventReport.mediaSinkId = UUID.randomUUID().toString();
        sfuEventReport.sctpStreamId = UUID.randomUUID().toString();
        sfuEventReport.rtpPadId = UUID.randomUUID().toString();
        sfuEventReport.name = this.randomGenerator.getRandomSfuEventReport().name();
        sfuEventReport.message = this.randomGenerator.getRandomString();
        sfuEventReport.value = this.randomGenerator.getRandomString();
        sfuEventReport.attachments = this.randomGenerator.getRandomString();
        return sfuEventReport;
    }

    public SfuExtensionReport generateSfuExtensionReport() {
        var sfuExtensionReport =  new SfuExtensionReport();
        sfuExtensionReport.serviceId = this.randomGenerator.getRandomServiceId();
        sfuExtensionReport.mediaUnitId = this.randomGenerator.getRandomSFUSideMediaUnitId();
        sfuExtensionReport.marker = this.randomGenerator.getRandomMarker();
        sfuExtensionReport.timestamp = this.randomGenerator.getRandomTimestamp();
        sfuExtensionReport.sfuId = UUID.randomUUID().toString();
        sfuExtensionReport.extensionType = this.randomGenerator.getRandomString();
        sfuExtensionReport.payload = this.randomGenerator.getRandomString();
        return sfuExtensionReport;
    }

    public SfuInboundRtpPadReport generateSfuInboundRtpPadReport() {
        var sfuInboundRtpPadReport = new SfuInboundRtpPadReport();
        sfuInboundRtpPadReport.serviceId = this.randomGenerator.getRandomServiceId();
        sfuInboundRtpPadReport.mediaUnitId = this.randomGenerator.getRandomSFUSideMediaUnitId();
        sfuInboundRtpPadReport.sfuId = UUID.randomUUID().toString();
        sfuInboundRtpPadReport.marker = this.randomGenerator.getRandomMarker();
        sfuInboundRtpPadReport.timestamp = this.randomGenerator.getRandomTimestamp();
        sfuInboundRtpPadReport.sfuStreamId = UUID.randomUUID().toString();
        sfuInboundRtpPadReport.rtpPadId = UUID.randomUUID().toString();
        sfuInboundRtpPadReport.trackId = UUID.randomUUID().toString();
        sfuInboundRtpPadReport.clientId = UUID.randomUUID().toString();
        sfuInboundRtpPadReport.callId = UUID.randomUUID().toString();
        sfuInboundRtpPadReport.transportId = UUID.randomUUID().toString();
        sfuInboundRtpPadReport.internal = this.randomGenerator.getRandomBoolean();
        sfuInboundRtpPadReport.ssrc = this.randomGenerator.getRandomSSRC();
        sfuInboundRtpPadReport.mediaType = this.randomGenerator.getRandomMediaKind();
        sfuInboundRtpPadReport.payloadType = this.randomGenerator.getRandomPayloadType();
        sfuInboundRtpPadReport.mimeType = this.randomGenerator.getRandomString();
        sfuInboundRtpPadReport.clockRate = this.randomGenerator.getRandomClockRate();
        sfuInboundRtpPadReport.sdpFmtpLine = this.randomGenerator.getRandomString();
        sfuInboundRtpPadReport.rid = this.randomGenerator.getRandomString();
        sfuInboundRtpPadReport.rtxSsrc = this.randomGenerator.getRandomSSRC();
        sfuInboundRtpPadReport.targetBitrate = this.randomGenerator.getRandomPositiveInteger();
        sfuInboundRtpPadReport.voiceActivityFlag = this.randomGenerator.getRandomBoolean();
        sfuInboundRtpPadReport.firCount = this.randomGenerator.getRandomPositiveInteger();
        sfuInboundRtpPadReport.pliCount = this.randomGenerator.getRandomPositiveInteger();
        sfuInboundRtpPadReport.nackCount = this.randomGenerator.getRandomPositiveInteger();
        sfuInboundRtpPadReport.sliCount = this.randomGenerator.getRandomPositiveInteger();
        sfuInboundRtpPadReport.packetsLost = this.randomGenerator.getRandomPositiveInteger();
        sfuInboundRtpPadReport.packetsReceived = this.randomGenerator.getRandomPositiveInteger();
        sfuInboundRtpPadReport.packetsDiscarded = this.randomGenerator.getRandomPositiveInteger();
        sfuInboundRtpPadReport.packetsRepaired = this.randomGenerator.getRandomPositiveInteger();
        sfuInboundRtpPadReport.packetsFailedDecryption = this.randomGenerator.getRandomPositiveInteger();
        sfuInboundRtpPadReport.packetsDuplicated = this.randomGenerator.getRandomPositiveInteger();
        sfuInboundRtpPadReport.fecPacketsReceived = this.randomGenerator.getRandomPositiveInteger();
        sfuInboundRtpPadReport.fecPacketsDiscarded = this.randomGenerator.getRandomPositiveInteger();
        sfuInboundRtpPadReport.bytesReceived = this.randomGenerator.getRandomPositiveLong();
        sfuInboundRtpPadReport.rtcpSrReceived = this.randomGenerator.getRandomPositiveInteger();
        sfuInboundRtpPadReport.rtcpRrSent = this.randomGenerator.getRandomPositiveInteger();
        sfuInboundRtpPadReport.rtxPacketsReceived = this.randomGenerator.getRandomPositiveInteger();
        sfuInboundRtpPadReport.rtxPacketsDiscarded = this.randomGenerator.getRandomPositiveInteger();
        sfuInboundRtpPadReport.framesReceived = this.randomGenerator.getRandomPositiveInteger();
        sfuInboundRtpPadReport.framesDecoded = this.randomGenerator.getRandomPositiveInteger();
        sfuInboundRtpPadReport.keyFramesDecoded = this.randomGenerator.getRandomPositiveInteger();
        sfuInboundRtpPadReport.fractionLost = this.randomGenerator.getRandomPositiveDouble();
        sfuInboundRtpPadReport.jitter = this.randomGenerator.getRandomPositiveDouble();
        sfuInboundRtpPadReport.roundTripTime = this.randomGenerator.getRandomPositiveDouble();
        return sfuInboundRtpPadReport;
    }

    public SfuMetaReport generateSfuMetaReport() {
        var sfuMetaReport = new SfuMetaReport();
        sfuMetaReport.mediaStreamId = UUID.randomUUID().toString();
        sfuMetaReport.mediaSinkId = UUID.randomUUID().toString();
        sfuMetaReport.sctpStreamId = UUID.randomUUID().toString();
        sfuMetaReport.type = this.randomGenerator.getRandomString();
        sfuMetaReport.payload = this.randomGenerator.getRandomString(128);
        sfuMetaReport.serviceId = this.randomGenerator.getRandomServiceId();
        sfuMetaReport.mediaUnitId = this.randomGenerator.getRandomSFUSideMediaUnitId();
        sfuMetaReport.sfuId = UUID.randomUUID().toString();
        sfuMetaReport.marker = this.randomGenerator.getRandomMarker();
        sfuMetaReport.timestamp = this.randomGenerator.getRandomTimestamp();
        sfuMetaReport.callId = UUID.randomUUID().toString();
        sfuMetaReport.transportId = UUID.randomUUID().toString();
        sfuMetaReport.rtpPadId = UUID.randomUUID().toString();
        return sfuMetaReport;
    }

    public SfuOutboundRtpPadReport generateSfuOutboundRtpPadReport() {
        var sfuOutboundRtpPadReport = new SfuOutboundRtpPadReport();
        sfuOutboundRtpPadReport.serviceId = this.randomGenerator.getRandomServiceId();
        sfuOutboundRtpPadReport.mediaUnitId = this.randomGenerator.getRandomSFUSideMediaUnitId();
        sfuOutboundRtpPadReport.sfuId = UUID.randomUUID().toString();
        sfuOutboundRtpPadReport.marker = this.randomGenerator.getRandomMarker();
        sfuOutboundRtpPadReport.timestamp = this.randomGenerator.getRandomTimestamp();
        sfuOutboundRtpPadReport.callId = UUID.randomUUID().toString();
        sfuOutboundRtpPadReport.transportId = UUID.randomUUID().toString();
        sfuOutboundRtpPadReport.internal = this.randomGenerator.getRandomBoolean();
        sfuOutboundRtpPadReport.sfuStreamId = UUID.randomUUID().toString();
        sfuOutboundRtpPadReport.sfuSinkId = UUID.randomUUID().toString();
        sfuOutboundRtpPadReport.rtpPadId = UUID.randomUUID().toString();
        sfuOutboundRtpPadReport.ssrc = this.randomGenerator.getRandomSSRC();
        sfuOutboundRtpPadReport.clientId = UUID.randomUUID().toString();
        sfuOutboundRtpPadReport.trackId = UUID.randomUUID().toString();
        sfuOutboundRtpPadReport.mediaType = this.randomGenerator.getRandomMediaKind();
        sfuOutboundRtpPadReport.payloadType = this.randomGenerator.getRandomPayloadType();
        sfuOutboundRtpPadReport.mimeType = this.randomGenerator.getRandomString();
        sfuOutboundRtpPadReport.clockRate = this.randomGenerator.getRandomClockRate();
        sfuOutboundRtpPadReport.sdpFmtpLine = this.randomGenerator.getRandomString();
        sfuOutboundRtpPadReport.rid = this.randomGenerator.getRandomString();
        sfuOutboundRtpPadReport.rtxSsrc = this.randomGenerator.getRandomSSRC();
        sfuOutboundRtpPadReport.targetBitrate = this.randomGenerator.getRandomPositiveInteger();
        sfuOutboundRtpPadReport.voiceActivityFlag = this.randomGenerator.getRandomBoolean();
        sfuOutboundRtpPadReport.firCount = this.randomGenerator.getRandomPositiveInteger();
        sfuOutboundRtpPadReport.pliCount = this.randomGenerator.getRandomPositiveInteger();
        sfuOutboundRtpPadReport.nackCount = this.randomGenerator.getRandomPositiveInteger();
        sfuOutboundRtpPadReport.sliCount = this.randomGenerator.getRandomPositiveInteger();
        sfuOutboundRtpPadReport.packetsLost = this.randomGenerator.getRandomPositiveInteger();
        sfuOutboundRtpPadReport.packetsSent = this.randomGenerator.getRandomPositiveInteger();
        sfuOutboundRtpPadReport.packetsDiscarded = this.randomGenerator.getRandomPositiveInteger();
        sfuOutboundRtpPadReport.packetsRetransmitted = this.randomGenerator.getRandomPositiveInteger();
        sfuOutboundRtpPadReport.packetsFailedEncryption = this.randomGenerator.getRandomPositiveInteger();
        sfuOutboundRtpPadReport.packetsDuplicated = this.randomGenerator.getRandomPositiveInteger();
        sfuOutboundRtpPadReport.fecPacketsSent = this.randomGenerator.getRandomPositiveInteger();
        sfuOutboundRtpPadReport.fecPacketsDiscarded = this.randomGenerator.getRandomPositiveInteger();
        sfuOutboundRtpPadReport.bytesSent = this.randomGenerator.getRandomPositiveLong();
        sfuOutboundRtpPadReport.rtcpSrSent = this.randomGenerator.getRandomPositiveInteger();
        sfuOutboundRtpPadReport.rtcpRrReceived = this.randomGenerator.getRandomPositiveInteger();
        sfuOutboundRtpPadReport.rtxPacketsSent = this.randomGenerator.getRandomPositiveInteger();
        sfuOutboundRtpPadReport.rtxPacketsDiscarded = this.randomGenerator.getRandomPositiveInteger();
        sfuOutboundRtpPadReport.framesSent = this.randomGenerator.getRandomPositiveInteger();
        sfuOutboundRtpPadReport.framesEncoded = this.randomGenerator.getRandomPositiveInteger();
        sfuOutboundRtpPadReport.keyFramesEncoded = this.randomGenerator.getRandomPositiveInteger();
        return sfuOutboundRtpPadReport;
    }

    public SfuSctpStreamReport generateSfuSctpStreamReport() {
        var sfuSctpStreamReport = new SfuSctpStreamReport();
        sfuSctpStreamReport.serviceId = this.randomGenerator.getRandomServiceId();
        sfuSctpStreamReport.mediaUnitId = this.randomGenerator.getRandomSFUSideMediaUnitId();
        sfuSctpStreamReport.sfuId = UUID.randomUUID().toString();
        sfuSctpStreamReport.marker = this.randomGenerator.getRandomMarker();
        sfuSctpStreamReport.timestamp = this.randomGenerator.getRandomTimestamp();
        sfuSctpStreamReport.callId = UUID.randomUUID().toString();
        sfuSctpStreamReport.roomId = this.randomGenerator.getRandomTestRoomIds();
        sfuSctpStreamReport.transportId = UUID.randomUUID().toString();
        sfuSctpStreamReport.streamId = UUID.randomUUID().toString();

        sfuSctpStreamReport.label = this.randomGenerator.getRandomString();
        sfuSctpStreamReport.protocol = this.randomGenerator.getRandomNetworkTransportProtocols();
        sfuSctpStreamReport.sctpSmoothedRoundTripTime = this.randomGenerator.getRandomPositiveDouble();
        sfuSctpStreamReport.sctpCongestionWindow = this.randomGenerator.getRandomPositiveDouble();
        sfuSctpStreamReport.sctpReceiverWindow = this.randomGenerator.getRandomPositiveDouble();
        sfuSctpStreamReport.sctpMtu = this.randomGenerator.getRandomPositiveInteger();
        sfuSctpStreamReport.sctpUnackData = this.randomGenerator.getRandomPositiveInteger();
        sfuSctpStreamReport.messageReceived = this.randomGenerator.getRandomPositiveInteger();
        sfuSctpStreamReport.messageSent = this.randomGenerator.getRandomPositiveInteger();
        sfuSctpStreamReport.bytesReceived = this.randomGenerator.getRandomPositiveLong();
        sfuSctpStreamReport.bytesSent = this.randomGenerator.getRandomPositiveLong();
        return sfuSctpStreamReport;
    }

}
