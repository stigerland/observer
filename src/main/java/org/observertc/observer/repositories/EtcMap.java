package org.observertc.observer.repositories;

import jakarta.inject.Inject;
import jakarta.inject.Singleton;

import java.util.concurrent.TimeUnit;

@Singleton
public class EtcMap {

    private static final String EXPIRING_KEY_NAME = "EXPIRING-KEY";

    @Inject
    HazelcastMaps hazelcastMaps;

    public void addExpiringKey(String key, int expiringTimeInMs) {
        this.hazelcastMaps.getEtcMap().put(key, EXPIRING_KEY_NAME, expiringTimeInMs, TimeUnit.MILLISECONDS);
    }

    public boolean hasExpiringKey(String key) {
        return this.hazelcastMaps.getEtcMap().containsKey(key);
    }

}
