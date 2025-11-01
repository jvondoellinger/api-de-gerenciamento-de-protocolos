package io.github.jvondoellinger.api_gerenciamento_de_protocolo.modules.protocol_module.domain.events;

import io.github.jvondoellinger.api_gerenciamento_de_protocolo.modules.protocol_module.domain.valueObjects.DomainId;

import java.time.LocalDateTime;

public abstract class DomainEvent {
    protected DomainEvent() {
        eventId = new DomainId();
        occurredOn = LocalDateTime.now();
    }
    private DomainId eventId;
    private LocalDateTime occurredOn;

    // ? Getters ==========================
    public DomainId getEventId() {
        return eventId;
    }

    public LocalDateTime getOccurredOn() {
        return occurredOn;
    }
}


