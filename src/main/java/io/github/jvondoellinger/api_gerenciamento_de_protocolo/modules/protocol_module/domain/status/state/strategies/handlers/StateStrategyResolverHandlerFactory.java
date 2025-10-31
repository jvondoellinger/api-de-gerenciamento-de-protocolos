package io.github.jvondoellinger.api_gerenciamento_de_protocolo.modules.protocol_module.domain.status.state.strategies.handlers;

import io.github.jvondoellinger.api_gerenciamento_de_protocolo.modules.protocol_module.domain.status.state.strategies.CanceladoStateResolver;
import io.github.jvondoellinger.api_gerenciamento_de_protocolo.modules.protocol_module.domain.status.state.strategies.PendenteStateResolver;
import io.github.jvondoellinger.api_gerenciamento_de_protocolo.modules.protocol_module.domain.status.state.strategies.SolucionadoStateResolver;
import io.github.jvondoellinger.api_gerenciamento_de_protocolo.modules.protocol_module.domain.status.state.strategies.StateStrategyResolver;

import java.util.List;

public class StateStrategyResolverHandlerFactory {
    private final static List<StateStrategyResolver> resolvers = List.of(
            new PendenteStateResolver(),
            new CanceladoStateResolver(),
            new SolucionadoStateResolver()
    );
    private StateStrategyResolverHandlerFactory() {}

    public static StateStrategyResolverHandler factory() {
        return new StateStrategyResolverHandler(resolvers);
    }
}
