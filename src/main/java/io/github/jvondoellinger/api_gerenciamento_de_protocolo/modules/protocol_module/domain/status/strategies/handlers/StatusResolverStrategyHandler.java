package io.github.jvondoellinger.api_gerenciamento_de_protocolo.modules.protocol_module.domain.status.strategies.handlers;

import io.github.jvondoellinger.api_gerenciamento_de_protocolo.modules.protocol_module.domain.status.ProtocoloStatus;
import io.github.jvondoellinger.api_gerenciamento_de_protocolo.modules.protocol_module.domain.status.strategies.StatusResolverStrategy;

import java.util.List;

public class StatusResolverStrategyHandler {
    private final List<StatusResolverStrategy> strategies;

    public StatusResolverStrategyHandler(List<StatusResolverStrategy> strategies) {
        this.strategies = strategies;
    }

    public ProtocoloStatus handle(String pattern) {
        for (var strategy : strategies) {
            var value = strategy.resolve(pattern);
            if (value.isPresent()) {
                return value.orElseThrow();
            }
        }
        throw new RuntimeException("Any status equals the provided pattern... Pattern: %s".formatted(pattern));
    }
}
