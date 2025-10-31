package io.github.jvondoellinger.api_gerenciamento_de_protocolo.modules.protocol_module.domain.status.state.strategies.handlers;

import io.github.jvondoellinger.api_gerenciamento_de_protocolo.modules.protocol_module.domain.status.ProtocoloStatus;
import io.github.jvondoellinger.api_gerenciamento_de_protocolo.modules.protocol_module.domain.status.state.ProtocoloState;
import io.github.jvondoellinger.api_gerenciamento_de_protocolo.modules.protocol_module.domain.status.state.strategies.StateStrategyResolver;

import java.util.List;

public class StateStrategyResolverHandler {
    private final List<StateStrategyResolver> resolvers;

    public StateStrategyResolverHandler(List<StateStrategyResolver> resolvers) {
        this.resolvers = resolvers;
    }

    public ProtocoloState handle(ProtocoloStatus status) {
        for (var resolver : resolvers) {
            var value = resolver.resolve(status);
            if (value.isPresent()) {
                return value.orElseThrow();
            }
        }
        throw new RuntimeException("Don't have any state on correspond to this status: %s".formatted(status.getValue()));
    }
}