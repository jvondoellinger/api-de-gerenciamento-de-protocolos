package io.github.jvondoellinger.api_gerenciamento_de_protocolo.modules.protocol_module.domain.events;

import io.github.jvondoellinger.api_gerenciamento_de_protocolo.modules.protocol_module.domain.Protocol;
import io.github.jvondoellinger.api_gerenciamento_de_protocolo.modules.protocol_module.domain.valueObjects.DomainId;

public class CreateProtocolEvent extends UserActivityEvent {
    private final Protocol protocol;
    private final DomainId queueId;

    public CreateProtocolEvent(Protocol protocol, DomainId queueId, DomainId userId) {
        super(userId);
        this.protocol = protocol;
        this.queueId = queueId;
    }

    public Protocol getProtocolo() {
        return protocol;
    }

    public DomainId getQueueId() {
        return queueId;
    }
}