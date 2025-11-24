package io.github.jvondoellinger.api_gerenciamento_de_protocolo.modules.protocol_module.domain.status.state;

import io.github.jvondoellinger.api_gerenciamento_de_protocolo.modules.protocol_module.domain.exception.DomainException;
import io.github.jvondoellinger.api_gerenciamento_de_protocolo.modules.protocol_module.domain.Protocol;
import io.github.jvondoellinger.api_gerenciamento_de_protocolo.modules.protocol_module.domain.status.SolucionadoProtocoloStatus;

public class SolucionadoProtocoloState extends ProtocoloState {
    public SolucionadoProtocoloState(SolucionadoProtocoloStatus status) {
        setStatus(status);
    }

    @Override
    void setPendenteState(Protocol protocol) {
        throw new DomainException("Não é possivel marcar o chamado como 'pendente' após solucionado.");
    }

    @Override
    void setCanceladoState(Protocol protocol) {
        throw new DomainException("Não é possivel cancelar um protocol solucionado.");
    }

    @Override
    void setSolucionadoState(Protocol protocol) {
        throw new DomainException("O protocol já está solucionado.");
    }
}
