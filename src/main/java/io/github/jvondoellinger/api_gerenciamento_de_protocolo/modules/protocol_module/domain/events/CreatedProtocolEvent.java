package io.github.jvondoellinger.api_gerenciamento_de_protocolo.modules.protocol_module.domain.events;

import io.github.jvondoellinger.api_gerenciamento_de_protocolo.modules.protocol_module.domain.Protocol;

public class CreatedProtocolEvent extends DomainEvent {
    private Protocol protocol;

    public CreatedProtocolEvent(Protocol protocol) {
        this.protocol = protocol;
    }

    public Protocol getProtocolo() {
        return protocol;
    }
}
