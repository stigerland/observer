package org.observertc.observer.utils;

import org.observertc.observer.dto.StreamDirection;
import org.observertc.observer.events.CallEventType;
import org.observertc.observer.events.SfuEventType;

import java.time.Instant;
import java.time.ZoneId;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Random;
import java.util.function.Supplier;
import java.util.stream.Collectors;

public class RandomGenerators {
    private final Random rand = new Random();
    private Long maxTime = Instant.now().toEpochMilli();
    private Long minTime = Instant.now().minusSeconds(100000).toEpochMilli();

    public<T> T getRandomFromList(List<T> givenList) {
        int randomElement = this.rand.nextInt(givenList.size());
        return givenList.get(randomElement);
    }

    public<T> T getRandomFromCollection(Collection<T> givenCollection) {
        int randomElement = Math.abs(this.rand.nextInt(givenCollection.size()));
        var it = givenCollection.iterator();
        for (int index = 0; index < randomElement; ++index) {
            it.next();
        }
        return it.next();
    }

    public <T> Supplier<T> randomProviderFrom(List<T> givenList) {
        return () -> getRandomFromList(givenList);
    }

    public Long getRandomTimestamp() {
        Integer diff = this.maxTime.intValue() - this.minTime.intValue();
        Long randomElapsed = Long.valueOf(this.rand.nextInt(diff));
        return this.minTime + randomElapsed;
    }

    public RandomGenerators setMinTime(Long epochTImeInMs) {
        this.minTime = epochTImeInMs;
        return this;
    }

    public RandomGenerators sethMaxTime(Long epochTImeInMs) {
        this.maxTime = epochTImeInMs;
        return this;
    }

    public Integer getRandomPositiveInteger() {
        return this.rand.nextInt();
    }

    public Double getRandomPositiveDouble() {
        Integer num = this.rand.nextInt();
        return this.rand.nextDouble() * num.doubleValue();

    }

    public String getRandomString() {
        return getRandomString(32);
    }

    public String getRandomString(int targetStringLength) {
        int leftLimit = 48; // numeral '0'
        int rightLimit = 122; // letter 'z'
        Random random = new Random();

        String result = random.ints(leftLimit, rightLimit + 1)
                .filter(i -> (i <= 57 || i >= 65) && (i <= 90 || i >= 97))
                .limit(targetStringLength)
                .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                .toString();
        return result;
    }

    public Long getRandomPositiveLong() {
        Long part1 = Integer.valueOf(this.rand.nextInt()).longValue();
        Long part2 = Integer.valueOf(this.rand.nextInt()).longValue();
        return part1 * part2;
    }

    public int getRandomPort() {
        int maxPort = 65535;
        int minPort = 1024;
        return this.rand.nextInt(maxPort - minPort) + minPort;
    }

    public String getRandomIPv4Address() {
        String result = this.rand.nextInt(256) + "." +
                this.rand.nextInt(256) + "." +
                this.rand.nextInt(256) + "." +
                this.rand.nextInt(256);
        return result;
    }

    private List<String> userIds = TestUtils.getTestUserIds();
    public String getRandomTestUserIds() {
        return this.getRandomFromList(this.userIds);
    }

    private List<String> browserNames = TestUtils.getBrowserName();
    public String getRandomBrowserNames() {
        return this.getRandomFromList(this.browserNames);
    }

    private List<String> operationSystemNames = TestUtils.getOperationSystemName();
    public String getOperationSystemName() {
        return this.getRandomFromList(this.operationSystemNames);
    }

    private List<String> osVersionNumbers = TestUtils.getVersionNumber();
    public String getRandomVersionNumber() {
        return this.getRandomFromList(this.osVersionNumbers);
    }

    private List<String> zoneIds = ZoneId.SHORT_IDS.keySet().stream().collect(Collectors.toList());
    public String getRandomTimeZoneId() {
        return this.getRandomFromList(this.zoneIds);
    }

    private List<String> serviceIds = TestUtils.getServiceIds();
    public String getRandomServiceId() {
        return this.getRandomFromList(this.serviceIds);
    }

    private List<String> roomIds = TestUtils.getTestRoomIds();
    public String getRandomTestRoomIds() {
        return this.getRandomFromList(roomIds);
    }

    private List<String> clientSideMediaUnitIds = TestUtils.getClientSideMediaUintIds();
    public String getRandomClientSideMediaUnitId() {
        return this.getRandomFromList(this.clientSideMediaUnitIds);
    }

    private List<String> sfuSideMediaUnitIds = TestUtils.getSFUSideMediaUintIds();
    public String getRandomSFUSideMediaUnitId() {
        return this.getRandomFromList(this.sfuSideMediaUnitIds);
    }

    private List<String> pcLabels = TestUtils.getPeerConnectionLabels();
    public String getRandomPeerConnectionLabels() {
        return this.getRandomFromList(pcLabels);
    }

    private List<String> iceRoles = TestUtils.getIceRole();
    public String getRandomIceRole() {
        return this.getRandomFromList(iceRoles);
    }

    private List<String> dtlsStates = TestUtils.getDtlsState();
    public String getRandomDtlsState() {
        return this.getRandomFromList(dtlsStates);
    }

    private List<String> iceStates = TestUtils.getIceState();
    public String getRandomIceState() {
        return this.getRandomFromList(iceStates);
    }

    private List<String> dtlsCiphers = TestUtils.getDtlsCipher();
    public String getRandomDtlsCipher() {
        return this.getRandomFromList(dtlsCiphers);
    }

    private List<String> srtpCiphers = TestUtils.getSrtpCipher();
    public String getRandomSrtpCipher() {
        return this.getRandomFromList(srtpCiphers);
    }

    private List<String> candidatePairStates = TestUtils.getCandidatePairState();
    public String getRandomCandidatePairState() {
        return this.getRandomFromList(candidatePairStates);
    }

    List<String> transportProtocols = TestUtils.getNetworkTransportProtocols();
    public String getRandomNetworkTransportProtocols() {
        return this.getRandomFromList(transportProtocols);
    }

    List<String> iceCandidateTypes = TestUtils.getICECandidateTypes();
    public String getRandomICECandidateTypes() {
        return this.getRandomFromList(iceCandidateTypes);
    }

    List<String> relayProtocols = TestUtils.getRelayProtocols();
    public String getRandomRelayProtocols() {
        return this.getRandomFromList(relayProtocols);
    }

    List<String> mediaKinds = TestUtils.getMediaKinds();
    public String getRandomMediaKind() {
        return this.getRandomFromList(mediaKinds);
    }

    public Long getRandomSSRC() {
        var result = this.rand.nextLong() % 4_294_967_296L;
        return Math.abs(result);
    }

    private List<StreamDirection> streamDirections = Arrays.asList(StreamDirection.values());
    public StreamDirection getRandomStreamDirection() {
        return this.getRandomFromList(streamDirections);
    }

    private List<Integer> clockRates = TestUtils.getClockRates();
    public Integer getRandomClockRate() {
        return this.getRandomFromList(clockRates);
    }

    private List<String> dataChannelStates = TestUtils.getDataChannelState();
    public String getRandomDataChannelState() {
        return this.getRandomFromList(dataChannelStates);
    }

    private List<String> qualityLimitationReasons = TestUtils.getQualityLimitationReason();
    public String getRandomQualityLimitationReason() {
        return this.getRandomFromList(qualityLimitationReasons);
    }

    public Integer getRandomPayloadType() {
        var result = this.rand.nextInt() % 256;
        return Math.abs(result);
    }

    public boolean getRandomBoolean() {
        var result = this.rand.nextInt();
        return result % 2 == 0;
    }

    private List<String> codecTypes = TestUtils.getCodecTypes();
    public String getRandomCodecType() {
        return this.getRandomFromList(codecTypes);
    }

    private List<String> iceUrls = TestUtils.getIceUrls();
    public String getRandomIceUrl() {
        return this.getRandomFromList(iceUrls);
    }

    private List<String> markers = TestUtils.getMarkers();
    public String getRandomMarker() {
        return this.getRandomFromList(markers);
    }

    private List<SfuEventType> sfuEventTypes = List.of(SfuEventType.values());
    public SfuEventType getRandomSfuEventReport() {
        return this.getRandomFromList(sfuEventTypes);
    }
    private List<CallEventType> callEventTypes = List.of(CallEventType.values());
    public CallEventType getRandomCallEventType() {
        return this.getRandomFromList(callEventTypes);
    }
}
