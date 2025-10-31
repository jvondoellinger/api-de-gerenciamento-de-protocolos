package io.github.jvondoellinger.api_gerenciamento_de_protocolo.modules.protocol_module.domain.status.state.strategies;

import io.github.jvondoellinger.api_gerenciamento_de_protocolo.modules.protocol_module.domain.status.ProtocoloStatus;
import io.github.jvondoellinger.api_gerenciamento_de_protocolo.modules.protocol_module.domain.status.SolucionadoProtocoloStatus;
import io.github.jvondoellinger.api_gerenciamento_de_protocolo.modules.protocol_module.domain.status.state.ProtocoloState;
import io.github.jvondoellinger.api_gerenciamento_de_protocolo.modules.protocol_module.domain.status.state.SolucionadoProtocoloState;

import java.util.Optional;

public class SolucionadoStateResolver implements StateStrategyResolver {
    @Override
    public Optional<ProtocoloState> resolve(ProtocoloStatus status) {
        if (status instanceof SolucionadoProtocoloStatus resolvedStatus) {
            return Optional.of(new SolucionadoProtocoloState(resolvedStatus));
        }
        return Optional.empty();
    }
}
