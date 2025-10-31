package io.github.jvondoellinger.api_gerenciamento_de_protocolo.modules.protocol_module.domain.status.strategies;

import io.github.jvondoellinger.api_gerenciamento_de_protocolo.modules.protocol_module.domain.status.ProtocoloStatus;
import io.github.jvondoellinger.api_gerenciamento_de_protocolo.modules.protocol_module.domain.status.SolucionadoProtocoloStatus;

import java.util.Optional;

public class SolucionadoResolverStrategy implements StatusResolverStrategy {
    @Override
    public Optional<ProtocoloStatus> resolve(String pattern) {
        if (pattern.equals(SolucionadoProtocoloStatus.getPattern())) {
            return Optional.of(new SolucionadoProtocoloStatus());
        }
        return Optional.empty();
    }
}
