package io.github.jvondoellinger.api_gerenciamento_de_protocolo.modules.protocol_module.domain.status.state;

import io.github.jvondoellinger.api_gerenciamento_de_protocolo.modules.protocol_module.domain.exception.DomainException;
import io.github.jvondoellinger.api_gerenciamento_de_protocolo.modules.protocol_module.domain.Protocolo;
import io.github.jvondoellinger.api_gerenciamento_de_protocolo.modules.protocol_module.domain.status.CanceladoProtocoloStatus;

public class CanceladoProtocoloState extends ProtocoloState {
    public CanceladoProtocoloState(CanceladoProtocoloStatus status) {
        setStatus(status);
    }

    @Override
    public void setPendenteState(Protocolo protocolo) {
        throw new DomainException("Não é possivel reabrir um protocolo cancelado!");
    }

    @Override
    public void setCanceladoState(Protocolo protocolo) {
        throw new DomainException("Não é possivel cancelar um protocolo já cancelado!");
    }

    @Override
    public void setSolucionadoState(Protocolo protocolo) {
        throw new DomainException("Não é possivel marcar como solucionado um protocolo cancelado!");
    }
}
