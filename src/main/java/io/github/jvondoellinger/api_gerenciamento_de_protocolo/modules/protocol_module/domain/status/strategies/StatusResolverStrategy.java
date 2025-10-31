package io.github.jvondoellinger.api_gerenciamento_de_protocolo.modules.protocol_module.domain.status.strategies;

import io.github.jvondoellinger.api_gerenciamento_de_protocolo.modules.protocol_module.domain.status.ProtocoloStatus;

import java.util.Optional;

public interface StatusResolverStrategy {
    Optional<ProtocoloStatus> resolve(String pattern);
}
