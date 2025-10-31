package io.github.jvondoellinger.api_gerenciamento_de_protocolo.modules.protocol_module.domain.status.state;

import io.github.jvondoellinger.api_gerenciamento_de_protocolo.modules.protocol_module.domain.Protocolo;
import io.github.jvondoellinger.api_gerenciamento_de_protocolo.modules.protocol_module.domain.status.ProtocoloStatus;

public abstract class ProtocoloState {
    private ProtocoloStatus status;

    abstract void setPendenteState(Protocolo protocolo);
    abstract void setCanceladoState(Protocolo protocolo);
    abstract void setSolucionadoState(Protocolo protocolo);

    public ProtocoloStatus getStatus() {
        return status;
    }
    protected void setStatus(ProtocoloStatus status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "ProtocoloState{" +
                "status=" + status.getValue() +
                '}';
    }
}
