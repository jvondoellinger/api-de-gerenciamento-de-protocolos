package io.github.jvondoellinger.api_gerenciamento_de_protocolo.modules.protocol_module.domain.status.state;

import io.github.jvondoellinger.api_gerenciamento_de_protocolo.modules.protocol_module.domain.Protocol;
import io.github.jvondoellinger.api_gerenciamento_de_protocolo.modules.protocol_module.domain.exception.DomainException;
import io.github.jvondoellinger.api_gerenciamento_de_protocolo.modules.protocol_module.domain.status.CanceladoProtocoloStatus;
import io.github.jvondoellinger.api_gerenciamento_de_protocolo.modules.protocol_module.domain.status.PendenteProtocoloStatus;
import io.github.jvondoellinger.api_gerenciamento_de_protocolo.modules.protocol_module.domain.status.SolucionadoProtocoloStatus;

public class PendenteProtocoloState extends ProtocoloState {

    public PendenteProtocoloState(PendenteProtocoloStatus status) {
        setStatus(status);
    }

    @Override
    void setPendenteState(Protocol protocol) {
        throw new DomainException("Protocol já está com status: 'pendente'.");
    }

    @Override
    void setCanceladoState(Protocol protocol) {
        var status = new CanceladoProtocoloStatus();
        var state = new CanceladoProtocoloState(status);
        super.setStatus(status);
        protocol.updateState(state);
    }

    @Override
    void setSolucionadoState(Protocol protocol) {
        var status = new SolucionadoProtocoloStatus();
        var state = new SolucionadoProtocoloState(status);
        protocol.updateState(state);
        super.setStatus(status);
    }
}
