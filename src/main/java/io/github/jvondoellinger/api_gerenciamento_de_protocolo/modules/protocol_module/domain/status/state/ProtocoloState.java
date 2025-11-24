package io.github.jvondoellinger.api_gerenciamento_de_protocolo.modules.protocol_module.domain.status.state;

import io.github.jvondoellinger.api_gerenciamento_de_protocolo.modules.protocol_module.domain.Protocol;
import io.github.jvondoellinger.api_gerenciamento_de_protocolo.modules.protocol_module.domain.status.ProtocoloStatus;

public abstract class ProtocoloState {
    private ProtocoloStatus status;

    abstract void setPendenteState(Protocol protocol);
    abstract void setCanceladoState(Protocol protocol);
    abstract void setSolucionadoState(Protocol protocol);

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
