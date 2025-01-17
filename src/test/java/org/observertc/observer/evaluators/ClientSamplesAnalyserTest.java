package org.observertc.observer.evaluators;

import io.micronaut.test.extensions.junit5.annotation.MicronautTest;
import jakarta.inject.Inject;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.observertc.observer.reports.Report;
import org.observertc.observer.samples.ClientSampleVisitor;
import org.observertc.observer.samples.ObservedClientSamples;
import org.observertc.observer.utils.ObservedSamplesGenerator;

import java.util.List;
import java.util.Objects;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

@MicronautTest
class ClientSamplesAnalyserTest {

    @Inject
    ClientSamplesAnalyser clientSamplesAnalyser;

    ObservedSamplesGenerator observedSamplesGenerator = new ObservedSamplesGenerator();

    @Test
    void shouldCreateReports() throws ExecutionException, InterruptedException, TimeoutException {
        this.clientSamplesAnalyser.config.dropUnmatchedReports = false;
        var observedClientSample = observedSamplesGenerator.generateObservedClientSample();
        var observedClientSamples = ObservedClientSamples.builder().addObservedClientSample(observedClientSample).build();
        var reportsPromise = new CompletableFuture<List<Report>>();
        var clientSample = observedClientSample.getClientSample();
        int expectedNumberOfReports = getOneIfNotNull(clientSample.engine) +
                getOneIfNotNull(clientSample.platform) +
                getOneIfNotNull(clientSample.browser) +
                getOneIfNotNull(clientSample.os) +
                getArrayLength(clientSample.mediaConstraints) +
                getArrayLength(clientSample.mediaDevices) +
                getArrayLength(clientSample.userMediaErrors) +
                getArrayLength(clientSample.localSDPs) +
                getArrayLength(clientSample.extensionStats) +
                getArrayLength(clientSample.iceServers) +
                getArrayLength(clientSample.pcTransports) +
                getArrayLength(clientSample.mediaSources) +
                getArrayLength(clientSample.codecs) +
                getArrayLength(clientSample.certificates) +
                getArrayLength(clientSample.inboundAudioTracks) +
                getArrayLength(clientSample.inboundVideoTracks) +
                getArrayLength(clientSample.outboundAudioTracks) +
                getArrayLength(clientSample.outboundVideoTracks) +
                getArrayLength(clientSample.iceLocalCandidates) +
                getArrayLength(clientSample.iceRemoteCandidates) +
                getArrayLength(clientSample.dataChannels) +
                0
                ;

        this.clientSamplesAnalyser.observableReports().subscribe(reportsPromise::complete);
        this.clientSamplesAnalyser.accept(observedClientSamples);
        var reports = reportsPromise.get(5, TimeUnit.SECONDS);

        Assertions.assertEquals(expectedNumberOfReports, reports.size(), "Number of reports");
    }

    @Test
    void shouldNotCreateUnMatchedReports() throws ExecutionException, InterruptedException, TimeoutException {
        this.clientSamplesAnalyser.config.dropUnmatchedReports = true;
        var observedClientSample = observedSamplesGenerator.generateObservedClientSample();
        var observedClientSamples = ObservedClientSamples.builder().addObservedClientSample(observedClientSample).build();
        var reportsPromise = new CompletableFuture<List<Report>>();
        var clientSample = observedClientSample.getClientSample();

        ClientSampleVisitor.streamInboundAudioTracks(observedClientSample.getClientSample()).forEach(track -> track.sfuSinkId = UUID.randomUUID());
        ClientSampleVisitor.streamInboundVideoTracks(observedClientSample.getClientSample()).forEach(track -> track.sfuSinkId = UUID.randomUUID());
        int expectedNumberOfReports = getOneIfNotNull(clientSample.engine) +
                getOneIfNotNull(clientSample.platform) +
                getOneIfNotNull(clientSample.browser) +
                getOneIfNotNull(clientSample.os) +
                getArrayLength(clientSample.mediaConstraints) +
                getArrayLength(clientSample.mediaDevices) +
                getArrayLength(clientSample.userMediaErrors) +
                getArrayLength(clientSample.localSDPs) +
                getArrayLength(clientSample.extensionStats) +
                getArrayLength(clientSample.iceServers) +
                getArrayLength(clientSample.pcTransports) +
                getArrayLength(clientSample.mediaSources) +
                getArrayLength(clientSample.codecs) +
                getArrayLength(clientSample.certificates) +
//                getArrayLength(clientSample.inboundAudioTracks) +
//                getArrayLength(clientSample.inboundVideoTracks) +
                getArrayLength(clientSample.outboundAudioTracks) +
                getArrayLength(clientSample.outboundVideoTracks) +
                getArrayLength(clientSample.iceLocalCandidates) +
                getArrayLength(clientSample.iceRemoteCandidates) +
                getArrayLength(clientSample.dataChannels) +
                0
                ;

        this.clientSamplesAnalyser.observableReports().subscribe(reportsPromise::complete);
        this.clientSamplesAnalyser.accept(observedClientSamples);
        var reports = reportsPromise.get(5, TimeUnit.SECONDS);

        Assertions.assertEquals(expectedNumberOfReports, reports.size(), "Number of reports");
    }

    static <T> int getArrayLength(T[] array) {
        if (Objects.isNull(array)) return 0;
        return array.length;
    }

    static<T> int getOneIfNotNull(T obj) {
        return Objects.isNull(obj) ? 0 : 1;
    }
}