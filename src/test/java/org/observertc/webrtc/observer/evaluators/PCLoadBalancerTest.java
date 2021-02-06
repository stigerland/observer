package org.observertc.webrtc.observer.evaluators;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hazelcast.collection.IQueue;
import com.hazelcast.core.Hazelcast;
import com.hazelcast.map.IMap;
import io.micronaut.test.extensions.junit5.annotation.MicronautTest;
import io.reactivex.rxjava3.subjects.PublishSubject;
import io.reactivex.rxjava3.subjects.Subject;
import org.jeasy.random.EasyRandom;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.observertc.webrtc.observer.samples.ObservedPCS;

import javax.inject.Inject;
import java.util.UUID;

@MicronautTest
class PCLoadBalancerTest {
    static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();
    static final EasyRandom generator = new EasyRandom();

    @Inject
    PCLoadBalancer loadBalancer;

    @Test
    public void receiveOn() throws Throwable {
        // Arrange
        var instance2 = Hazelcast.newHazelcastInstance();
        UUID localEndpointUUID = instance2.getLocalEndpoint().getUuid();
        IMap<UUID, UUID> map = instance2.getMap(PCLoadBalancer.class.getSimpleName());
        String queueName = PCLoadBalancer.getQueueName(localEndpointUUID);
        IQueue<byte[]> queue = instance2.getQueue(queueName);
        UUID uuid = UUID.randomUUID();

        map.put(uuid, localEndpointUUID);
        ObservedPCS observedPCS = generator.nextObject(ObservedPCS.class);
        observedPCS.peerConnectionUUID = uuid;

        // Act
        Subject<ObservedPCS> subject = PublishSubject.create();
        var input = loadBalancer.apply(subject);
        input.onNext(observedPCS);
        byte[] observed = queue.poll();
        ObservedPCS read = OBJECT_MAPPER.readValue(observed, ObservedPCS.class);

        // Assert
        Assertions.assertNotNull(read);
    }
}