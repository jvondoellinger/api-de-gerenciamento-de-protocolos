package io.github.jvondoellinger.api_gerenciamento_de_protocolo.modules.protocol_module.domain.status;

import io.github.jvondoellinger.api_gerenciamento_de_protocolo.modules.protocol_module.domain.status.strategies.handlers.StatusResolverStrategyHandler;
import io.github.jvondoellinger.api_gerenciamento_de_protocolo.modules.protocol_module.domain.status.strategies.handlers.StatusResolverStrategyHandlerFactory;

public class ProtocoloStatusResolver {
    private static StatusResolverStrategyHandler handler = StatusResolverStrategyHandlerFactory.factory();
    private ProtocoloStatusResolver() {}

    public static ProtocoloStatus resolve(String pattern) {
        return handler.handle(pattern);
    }

}
