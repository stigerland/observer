package org.observertc.observer.sources;

import jakarta.inject.Inject;
import jakarta.inject.Singleton;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

@Singleton
public class SampleSources {
    private static final Logger logger = LoggerFactory.getLogger(SampleSources.class);

    @Inject
    SamplesWebsocketController websocketController;

    @Inject
    SamplesRestApiController restApiController;

    @PostConstruct
    void init() {
        logger.info("Initialized, supported schema versions \n{}", String.join("\n", SamplesVersionVisitor.getSupportedVersions()));
    }

    @PreDestroy
    void teardown() {
        logger.info("Deinitialized");
    }
}
