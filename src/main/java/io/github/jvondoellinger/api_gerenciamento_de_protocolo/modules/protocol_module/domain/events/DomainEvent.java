package io.github.jvondoellinger.api_gerenciamento_de_protocolo.modules.protocol_module.domain.events;

import io.github.jvondoellinger.api_gerenciamento_de_protocolo.modules.protocol_module.domain.valueObjects.ProtocolDomainId;

import java.time.LocalDateTime;

public abstract class DomainEvent {
    protected DomainEvent() {
        eventId = new ProtocolDomainId();
        occurredOn = LocalDateTime.now();
    }
    private ProtocolDomainId eventId;
    private LocalDateTime occurredOn;

    // ? Getters ==========================
    public ProtocolDomainId getEventId() {
        return eventId;
    }

    public LocalDateTime getOccurredOn() {
        return occurredOn;
    }
}


