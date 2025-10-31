package io.github.jvondoellinger.api_gerenciamento_de_protocolo.modules.protocol_module.domain.status.state.strategies;

import io.github.jvondoellinger.api_gerenciamento_de_protocolo.modules.protocol_module.domain.status.CanceladoProtocoloStatus;
import io.github.jvondoellinger.api_gerenciamento_de_protocolo.modules.protocol_module.domain.status.ProtocoloStatus;
import io.github.jvondoellinger.api_gerenciamento_de_protocolo.modules.protocol_module.domain.status.state.CanceladoProtocoloState;
import io.github.jvondoellinger.api_gerenciamento_de_protocolo.modules.protocol_module.domain.status.state.ProtocoloState;

import java.util.Optional;

public class CanceladoStateResolver implements StateStrategyResolver {
    @Override
    public Optional<ProtocoloState> resolve(ProtocoloStatus status) {
        if (status instanceof CanceladoProtocoloStatus resolvedStatus) {
            return Optional.of(new CanceladoProtocoloState(resolvedStatus));
        }
        return Optional.empty();
    }
}
