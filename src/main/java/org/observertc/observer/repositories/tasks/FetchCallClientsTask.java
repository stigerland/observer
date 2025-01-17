package org.observertc.observer.repositories.tasks;

import io.micronaut.context.annotation.Prototype;
import jakarta.inject.Inject;
import org.observertc.observer.common.ChainedTask;
import org.observertc.observer.common.Utils;
import org.observertc.observer.repositories.HazelcastMaps;

import javax.annotation.PostConstruct;
import java.util.*;

@Prototype
public class FetchCallClientsTask extends ChainedTask<Map<UUID, Set<UUID>>> {

    private Set<UUID> callIds = new HashSet<>();

    @Inject
    HazelcastMaps hazelcastMaps;

    @PostConstruct
    void setup() {
        new Builder<Map<UUID, Set<UUID>>>(this)
                .addTerminalSupplier("Fetch Call Clients", () -> {
                    Map<UUID, Set<UUID>> result = new HashMap<>();
                    this.callIds.forEach(callId -> {
                        var clientIds = this.hazelcastMaps.getCallToClientIds().get(callId);
                        result.put(callId, new HashSet<>(clientIds));
                    });
                    return result;
                })
                .build();
    }

    public FetchCallClientsTask whereCallIds(UUID... values) {
        if (Objects.isNull(values) || values.length < 1) {
            return this;
        }
        Arrays.asList(values).stream().filter(Utils::expensiveNonNullCheck).forEach(this.callIds::add);
        return this;
    }

    public FetchCallClientsTask whereCallIds(Set<UUID> callIds) {
        if (Objects.isNull(callIds) || callIds.size() < 1) {
            return this;
        }
        callIds.stream().filter(Utils::expensiveNonNullCheck).forEach(this.callIds::add);
        return this;
    }


    @Override
    protected void validate() {

    }

}
