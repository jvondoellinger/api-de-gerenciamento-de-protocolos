package io.github.jvondoellinger.api_gerenciamento_de_protocolo.modules.protocol_module.domain.status.strategies;

import io.github.jvondoellinger.api_gerenciamento_de_protocolo.modules.protocol_module.domain.status.CanceladoProtocoloStatus;
import io.github.jvondoellinger.api_gerenciamento_de_protocolo.modules.protocol_module.domain.status.ProtocoloStatus;

import java.util.Optional;

public class CanceladoResolverStrategy implements StatusResolverStrategy {
    @Override
    public Optional<ProtocoloStatus> resolve(String pattern) {
        if (pattern.equals(CanceladoProtocoloStatus.getPattern())) {
            return Optional.of(new CanceladoProtocoloStatus());
        }
        return Optional.empty();
    }
}
