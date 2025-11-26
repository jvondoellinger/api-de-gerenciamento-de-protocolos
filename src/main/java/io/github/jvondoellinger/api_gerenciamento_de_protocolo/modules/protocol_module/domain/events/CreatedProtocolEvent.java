package io.github.jvondoellinger.api_gerenciamento_de_protocolo.modules.protocol_module.domain.events;

import io.github.jvondoellinger.api_gerenciamento_de_protocolo.modules.protocol_module.domain.Protocol;

public class CreatedProtocolEvent extends DomainEvent {
    private final Protocol protocol;

    public CreatedProtocolEvent(Protocol protocol) {
        this.protocol = protocol;
    }

    public Protocol getProtocol() {
        return protocol;
    }
}
