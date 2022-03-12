package org.observertc.observer.sources.inboundSamples;

public interface InboundSamplesAcceptor {
    void accept(String serviceId, String mediaUnitId, byte[] message) throws Throwable;
    void close() throws Throwable;
}