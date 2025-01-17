package org.observertc.observer.repositories.tasks;

import io.micronaut.context.annotation.Prototype;
import jakarta.inject.Inject;
import org.observertc.observer.common.ChainedTask;
import org.observertc.observer.common.Utils;
import org.observertc.observer.dto.SfuRtpPadDTO;
import org.observertc.observer.metrics.RepositoryMetrics;
import org.observertc.observer.repositories.HazelcastMaps;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.PostConstruct;
import java.util.*;
import java.util.stream.Collectors;

@Prototype
public class RemoveSfuRtpPadsTask extends ChainedTask<List<SfuRtpPadDTO>> {

    private static final Logger logger = LoggerFactory.getLogger(RemoveSfuRtpPadsTask.class);

    private Set<UUID> padIds = new HashSet<>();
    private Map<UUID, SfuRtpPadDTO> removedRtpPads = new HashMap<>();

    @Inject
    HazelcastMaps hazelcastMaps;

    @Inject
    RepositoryMetrics exposedMetrics;

    @PostConstruct
    void setup() {
        this.withStatsConsumer(this.exposedMetrics::processTaskStats);
        new Builder<List<SfuRtpPadDTO>>(this)
                .<Set<UUID>>addConsumerEntry("Fetch SfuTransport Ids",
                        () -> {},
                        receivedIds -> {
                            this.padIds.addAll(receivedIds);
                        }
                )
                .addBreakCondition(resultHolder -> {
                    if (this.padIds.size() < 1 && this.removedRtpPads.size() < 1) {
                        this.getLogger().warn("No Entities have been passed");
                        resultHolder.set(Collections.EMPTY_LIST);
                        return true;
                    }
                    return false;
                })
                .addActionStage("Remove Sfu Rtp Stream Pad DTOs",
                        // action
                        () -> {
                            this.padIds.forEach(id -> {
                                if (this.removedRtpPads.containsKey(id)) {
                                    return;
                                }
                                SfuRtpPadDTO sfuPadDTO = this.hazelcastMaps.getSFURtpPads().remove(id);
                                if (Objects.isNull(sfuPadDTO)) {
                                    logger.debug("Not found SfuRtpPadDTO for padId {}. Perhaps it was ejected before it was ordered to be removed.", id);
                                    return;
                                }
                                this.removedRtpPads.put(sfuPadDTO.rtpPadId, sfuPadDTO);
                            });
                            return;
                        },
                        // rollback
                        (callEntitiesHolder, thrownException) -> {
                            if (Objects.isNull(callEntitiesHolder) || Objects.isNull(callEntitiesHolder.get())) {
                                this.getLogger().warn("Unexpected condition at rollback.");
                                return;
                            }
                            this.hazelcastMaps.getSFURtpPads().putAll(this.removedRtpPads);
                        })
                .addActionStage("Remove SfuTransport to SfuRtpPad bindings", () -> {
                        this.removedRtpPads.values().forEach(sfuRtpPadDTO -> {
                            if (sfuRtpPadDTO.rtpPadId == null || sfuRtpPadDTO.transportId == null) return;
                            this.hazelcastMaps.getSfuTransportToSfuRtpPadIds().remove(sfuRtpPadDTO.transportId, sfuRtpPadDTO.rtpPadId);
                        });
                    },
                    // rollback
                    (something, thrownException) -> {
                        this.removedRtpPads.values().forEach(sfuRtpPadDTO -> {
                            if (sfuRtpPadDTO.rtpPadId == null || sfuRtpPadDTO.transportId == null) return;
                            this.hazelcastMaps.getSfuTransportToSfuRtpPadIds().put(sfuRtpPadDTO.transportId, sfuRtpPadDTO.rtpPadId);
                        });
                    })
                .addActionStage("Remove Bindings RtpPads to rtp streamIds",
                        // action
                        () -> {
                            this.removedRtpPads.forEach((padId, sfuRtpPadDTO) -> {
                                if (Objects.nonNull(sfuRtpPadDTO.streamId)) {
                                    this.hazelcastMaps.getSfuStreamIdToRtpPadIds().remove(sfuRtpPadDTO.streamId, padId);
                                }
                                if (Objects.nonNull(sfuRtpPadDTO.sinkId)) {
                                    this.hazelcastMaps.getSfuSinkIdToRtpPadIds().remove(sfuRtpPadDTO.sinkId, padId);
                                }
                            });
                        },
                        // rollback
                        (inputHolder, thrownException) -> {
                            this.removedRtpPads.forEach((padId, sfuRtpPadDTO) -> {
                                if (Objects.nonNull(sfuRtpPadDTO.streamId)) {
                                    this.hazelcastMaps.getSfuStreamIdToRtpPadIds().put(sfuRtpPadDTO.streamId, padId);
                                }
                                if (Objects.nonNull(sfuRtpPadDTO.sinkId)) {
                                    this.hazelcastMaps.getSfuSinkIdToRtpPadIds().put(sfuRtpPadDTO.sinkId, padId);
                                }
                            });
                        })
                .addActionStage("Remove bindings for internal outbound rtp pad ids", () -> {
                    this.removedRtpPads.forEach((padId, sfuRtpPadDTO) -> {
                        if (Boolean.TRUE.equals(sfuRtpPadDTO.internal) == false) return;
                        if (sfuRtpPadDTO.streamId == null) return;
                        switch (sfuRtpPadDTO.streamDirection) {
                            case INBOUND -> {
                                this.hazelcastMaps.getSfuInternalInboundRtpPadIdToOutboundRtpPadId().remove(sfuRtpPadDTO.rtpPadId);
                            }
                            case OUTBOUND -> {
                                this.hazelcastMaps.getSfuStreamIdToInternalOutboundRtpPadIds().remove(sfuRtpPadDTO.streamId, sfuRtpPadDTO.rtpPadId);
                            }
                        }
                    });
                })
                .addTerminalSupplier("Completed", () -> {
                    return this.removedRtpPads.values().stream().collect(Collectors.toList());
                })
                .build();
    }

    public RemoveSfuRtpPadsTask whereSfuRtpStreamPadIds(Set<UUID> podIds) {
        if (Objects.isNull(podIds) || podIds.size() < 1) {
            return this;
        }
        podIds.stream().filter(Utils::expensiveNonNullCheck).forEach(this.padIds::add);
        return this;
    }

    public RemoveSfuRtpPadsTask addRemovedSfuRtpStreamPadDTO(SfuRtpPadDTO DTO) {
        if (Objects.isNull(DTO)) {
            return this;
        }
        this.padIds.add(DTO.rtpPadId);
        this.removedRtpPads.put(DTO.rtpPadId, DTO);
        return this;
    }
}
