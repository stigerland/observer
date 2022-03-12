package org.observertc.observer.samples;

import org.observertc.schemas.samples.Samples.ClientSample;

import java.util.UUID;

public interface ObservedClientSample {

    String getServiceId();

    String getMediaUnitId();

    String getTimeZoneId();

    Long getTimestamp();

    String getRoomId();

    UUID getCallId();

    UUID getClientId();

    ClientSample getClientSample();

    String getMarker();

    String getUserId();

    int getSampleSeq();
}