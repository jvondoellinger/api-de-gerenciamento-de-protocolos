package io.github.jvondoellinger.api_gerenciamento_de_protocolo.modules.protocol_module.domain.events;

import io.github.jvondoellinger.api_gerenciamento_de_protocolo.modules.protocol_module.domain.Protocolo;
import io.github.jvondoellinger.api_gerenciamento_de_protocolo.modules.protocol_module.domain.valueObjects.DomainId;

public class CreateProtocolEvent extends UserActivityEvent {
    private final Protocolo protocolo;
    private final DomainId queueId;

    public CreateProtocolEvent(Protocolo protocolo, DomainId queueId, DomainId userId) {
        super(userId);
        this.protocolo = protocolo;
        this.queueId = queueId;
    }

    public Protocolo getProtocolo() {
        return protocolo;
    }

    public DomainId getQueueId() {
        return queueId;
    }
}