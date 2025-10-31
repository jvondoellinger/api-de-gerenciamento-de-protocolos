package io.github.jvondoellinger.api_gerenciamento_de_protocolo.modules.protocol_module.domain.status.strategies;

import io.github.jvondoellinger.api_gerenciamento_de_protocolo.modules.protocol_module.domain.status.PendenteProtocoloStatus;
import io.github.jvondoellinger.api_gerenciamento_de_protocolo.modules.protocol_module.domain.status.ProtocoloStatus;

import java.util.Optional;

public class PendenteResolverStrategy implements StatusResolverStrategy {
    @Override
    public Optional<ProtocoloStatus> resolve(String pattern) {
        if (pattern.equals(PendenteProtocoloStatus.getPattern())) {
            return Optional.of(new PendenteProtocoloStatus());
        }
        return Optional.empty();
    }
}
