package io.github.jvondoellinger.api_gerenciamento_de_protocolo.modules.protocol_module.domain.status.state;

import io.github.jvondoellinger.api_gerenciamento_de_protocolo.modules.protocol_module.domain.status.ProtocoloStatus;
import io.github.jvondoellinger.api_gerenciamento_de_protocolo.modules.protocol_module.domain.status.state.strategies.handlers.StateStrategyResolverHandler;
import io.github.jvondoellinger.api_gerenciamento_de_protocolo.modules.protocol_module.domain.status.state.strategies.handlers.StateStrategyResolverHandlerFactory;

public class ProtocoloStateResolver {
    private static StateStrategyResolverHandler handler = StateStrategyResolverHandlerFactory.factory();

    private ProtocoloStateResolver() {
    }

    public static ProtocoloState resolve(ProtocoloStatus status) {
        return handler.handle(status);
    }
}
