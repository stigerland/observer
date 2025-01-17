package org.observertc.observer.metrics;

import jakarta.inject.Inject;
import jakarta.inject.Singleton;
import org.observertc.observer.configs.ObserverConfig;

import javax.annotation.PostConstruct;

@Singleton
public class SourceMetrics {
    private static final String SOURCE_METRICS_PREFIX = "sources";

    private static final String OPENED_WEBSOCKET_METRIC_NAME = "opened_websockets";
    private static final String CLOSED_WEBSOCKET_METRIC_NAME = "closed_websockets";
    private static final String RECEIVED_SAMPLES_METRIC_NAME = "received_samples";

    private static final String SOURCE_TAG_NAME = "source";
    private static final String REST_SOURCE_TAG_VALUE = "rest";
    private static final String WEBSOCKET_SOURCE_TAG_VALUE = "websocket";

    @Inject
    Metrics metrics;

    @Inject
    ObserverConfig.MetricsConfig.SourceMetricsConfig config;

    private String openedWebsocketMetricName;
    private String closedWebsocketMetricName;
    private String receivedSamplesMetricName;

    @PostConstruct
    void setup() {
        this.openedWebsocketMetricName = metrics.getMetricName(SOURCE_METRICS_PREFIX, OPENED_WEBSOCKET_METRIC_NAME);
        this.closedWebsocketMetricName = metrics.getMetricName(SOURCE_METRICS_PREFIX, CLOSED_WEBSOCKET_METRIC_NAME);
        this.receivedSamplesMetricName = metrics.getMetricName(SOURCE_METRICS_PREFIX, RECEIVED_SAMPLES_METRIC_NAME);
    }

    public SourceMetrics incrementOpenedWebsockets(String serviceId, String mediaUnitId) {
        this.metrics.registry.counter(
                this.openedWebsocketMetricName,
                metrics.getServiceIdTagName(), metrics.getTagValue(serviceId),
                metrics.getMediaUnitIdTagName(), metrics.getTagValue(mediaUnitId)
        ).increment();
        return this;
    }

    public SourceMetrics incrementClosedWebsockets(String serviceId, String mediaUnitId) {
        this.metrics.registry.counter(
                this.closedWebsocketMetricName,
                metrics.getServiceIdTagName(), metrics.getTagValue(serviceId),
                metrics.getMediaUnitIdTagName(), metrics.getTagValue(mediaUnitId)
        ).increment();
        return this;
    }

    public SourceMetrics incrementRESTReceivedSamples(String serviceId, String mediaUnitId) {
        this.metrics.registry.counter(
                this.receivedSamplesMetricName,
                metrics.getServiceIdTagName(), metrics.getTagValue(serviceId),
                metrics.getMediaUnitIdTagName(), metrics.getTagValue(mediaUnitId),
                SOURCE_TAG_NAME, REST_SOURCE_TAG_VALUE
        ).increment();
        return this;
    }

    public SourceMetrics incrementWebsocketReceivedSamples(String serviceId, String mediaUnitId) {
        this.metrics.registry.counter(
                this.receivedSamplesMetricName,
                metrics.getServiceIdTagName(), metrics.getTagValue(serviceId),
                metrics.getMediaUnitIdTagName(), metrics.getTagValue(mediaUnitId),
                SOURCE_TAG_NAME, WEBSOCKET_SOURCE_TAG_VALUE
        ).increment();
        return this;
    }
}
