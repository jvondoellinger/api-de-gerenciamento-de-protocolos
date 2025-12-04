package io.github.jvondoellinger.api_gerenciamento_de_protocolo.modules.protocol_module.domain.events;

import io.github.jvondoellinger.api_gerenciamento_de_protocolo.modules.protocol_module.domain.Protocol;
import io.github.jvondoellinger.api_gerenciamento_de_protocolo.modules.protocol_module.domain.valueObjects.DomainId;

public class CreateProtocolEvent extends UserActivityEvent {
    private final Protocol protocol;

    public CreateProtocolEvent(Protocol protocol) {
        super(protocol.getCreatedById());
        this.protocol = protocol;
    }

    public Protocol getProtocolo() {
        return protocol;
    }
}