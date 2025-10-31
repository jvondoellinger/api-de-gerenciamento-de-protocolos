package io.github.jvondoellinger.api_gerenciamento_de_protocolo.modules.protocol_module.domain.status.state;

import io.github.jvondoellinger.api_gerenciamento_de_protocolo.modules.protocol_module.domain.exception.DomainException;
import io.github.jvondoellinger.api_gerenciamento_de_protocolo.modules.protocol_module.domain.Protocolo;
import io.github.jvondoellinger.api_gerenciamento_de_protocolo.modules.protocol_module.domain.status.CanceladoProtocoloStatus;
import io.github.jvondoellinger.api_gerenciamento_de_protocolo.modules.protocol_module.domain.status.PendenteProtocoloStatus;
import io.github.jvondoellinger.api_gerenciamento_de_protocolo.modules.protocol_module.domain.status.SolucionadoProtocoloStatus;

public class PendenteProtocoloState extends ProtocoloState {

    public PendenteProtocoloState(PendenteProtocoloStatus status) {
        setStatus(status);
    }

    @Override
    void setPendenteState(Protocolo protocolo) {
        throw new DomainException("Protocolo já está com status: 'pendente'.");
    }

    @Override
    void setCanceladoState(Protocolo protocolo) {
        var status = new CanceladoProtocoloStatus();
        var state = new CanceladoProtocoloState(status);
        super.setStatus(status);
        protocolo.updateState(state);
    }

    @Override
    void setSolucionadoState(Protocolo protocolo) {
        var status = new SolucionadoProtocoloStatus();
        var state = new SolucionadoProtocoloState(status);
        protocolo.updateState(state);
        super.setStatus(status);
    }
}
