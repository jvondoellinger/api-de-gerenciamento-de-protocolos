package io.github.jvondoellinger.api_gerenciamento_de_protocolo.modules.protocol_module.domain.status.state;

import io.github.jvondoellinger.api_gerenciamento_de_protocolo.modules.protocol_module.domain.Protocol;
import io.github.jvondoellinger.api_gerenciamento_de_protocolo.modules.protocol_module.domain.exception.DomainException;
import io.github.jvondoellinger.api_gerenciamento_de_protocolo.modules.protocol_module.domain.status.CanceladoProtocoloStatus;

public class CanceladoProtocoloState extends ProtocoloState {
    public CanceladoProtocoloState(CanceladoProtocoloStatus status) {
        setStatus(status);
    }

    @Override
    public void setPendenteState(Protocol protocol) {
        throw new DomainException("Não é possivel reabrir um protocol cancelado!");
    }

    @Override
    public void setCanceladoState(Protocol protocol) {
        throw new DomainException("Não é possivel cancelar um protocol já cancelado!");
    }

    @Override
    public void setSolucionadoState(Protocol protocol) {
        throw new DomainException("Não é possivel marcar como solucionado um protocol cancelado!");
    }
}
