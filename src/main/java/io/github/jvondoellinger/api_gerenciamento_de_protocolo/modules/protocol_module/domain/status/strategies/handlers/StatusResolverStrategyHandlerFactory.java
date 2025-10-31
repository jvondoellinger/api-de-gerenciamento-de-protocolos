package io.github.jvondoellinger.api_gerenciamento_de_protocolo.modules.protocol_module.domain.status.strategies.handlers;

import io.github.jvondoellinger.api_gerenciamento_de_protocolo.modules.protocol_module.domain.status.strategies.CanceladoResolverStrategy;
import io.github.jvondoellinger.api_gerenciamento_de_protocolo.modules.protocol_module.domain.status.strategies.PendenteResolverStrategy;
import io.github.jvondoellinger.api_gerenciamento_de_protocolo.modules.protocol_module.domain.status.strategies.StatusResolverStrategy;
import io.github.jvondoellinger.api_gerenciamento_de_protocolo.modules.protocol_module.domain.status.strategies.SolucionadoResolverStrategy;

import java.util.List;

public class StatusResolverStrategyHandlerFactory {
    private StatusResolverStrategyHandlerFactory() {}

    private final static List<StatusResolverStrategy> resolvers = List.of(
            new CanceladoResolverStrategy(),
            new PendenteResolverStrategy(),
            new SolucionadoResolverStrategy()
    );

    public static StatusResolverStrategyHandler factory() {
        return new StatusResolverStrategyHandler(resolvers);
    }
}
