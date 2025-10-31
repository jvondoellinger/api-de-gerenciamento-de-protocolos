package io.github.jvondoellinger.api_gerenciamento_de_protocolo.modules.protocol_module.domain.status.state.strategies;

import io.github.jvondoellinger.api_gerenciamento_de_protocolo.modules.protocol_module.domain.status.PendenteProtocoloStatus;
import io.github.jvondoellinger.api_gerenciamento_de_protocolo.modules.protocol_module.domain.status.ProtocoloStatus;
import io.github.jvondoellinger.api_gerenciamento_de_protocolo.modules.protocol_module.domain.status.state.PendenteProtocoloState;
import io.github.jvondoellinger.api_gerenciamento_de_protocolo.modules.protocol_module.domain.status.state.ProtocoloState;

import java.util.Optional;

public class PendenteStateResolver implements StateStrategyResolver {
    @Override
    public Optional<ProtocoloState> resolve(ProtocoloStatus status) {
        if (status instanceof PendenteProtocoloStatus resolvedStatus) {
            return Optional.of(new PendenteProtocoloState(resolvedStatus));
        }
        return Optional.empty();
    }
}
