package io.github.jvondoellinger.api_gerenciamento_de_protocolo.modules.protocol_module.domain.events;

import io.github.jvondoellinger.api_gerenciamento_de_protocolo.modules.protocol_module.domain.Protocolo;

public class CreatedProtocolEvent extends DomainEvent {
    private Protocolo protocolo;

    public CreatedProtocolEvent(Protocolo protocolo) {
        this.protocolo = protocolo;
    }

    public Protocolo getProtocolo() {
        return protocolo;
    }
}
