package io.github.jvondoellinger.api_gerenciamento_de_protocolo.modules.protocol_module.domain.status.state;

import io.github.jvondoellinger.api_gerenciamento_de_protocolo.modules.protocol_module.domain.exception.DomainException;
import io.github.jvondoellinger.api_gerenciamento_de_protocolo.modules.protocol_module.domain.Protocolo;
import io.github.jvondoellinger.api_gerenciamento_de_protocolo.modules.protocol_module.domain.status.SolucionadoProtocoloStatus;

public class SolucionadoProtocoloState extends ProtocoloState {
    public SolucionadoProtocoloState(SolucionadoProtocoloStatus status) {
        setStatus(status);
    }

    @Override
    void setPendenteState(Protocolo protocolo) {
        throw new DomainException("Não é possivel marcar o chamado como 'pendente' após solucionado.");
    }

    @Override
    void setCanceladoState(Protocolo protocolo) {
        throw new DomainException("Não é possivel cancelar um protocolo solucionado.");
    }

    @Override
    void setSolucionadoState(Protocolo protocolo) {
        throw new DomainException("O protocolo já está solucionado.");
    }
}
