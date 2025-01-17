package org.observertc.observer.sinks.socket;

import io.micronaut.context.annotation.Prototype;
import org.observertc.observer.configbuilders.AbstractBuilder;
import org.observertc.observer.configbuilders.Builder;
import org.observertc.observer.sinks.Sink;

import javax.validation.constraints.NotNull;

@Prototype
public class SocketSinkBuilder extends AbstractBuilder implements Builder<Sink> {

    public Sink build() {
        Config config = this.convertAndValidate(Config.class);
        SocketSink result = new SocketSink(
                config.host,
                config.port,
                config.maxRetry
        );
        return result;
    }

    public static class Config {

        @NotNull
        public String host = null;

        @NotNull
        public int port = 0;

        public int maxRetry = 3;
    }
}
