package io.github.jvondoellinger.api_gerenciamento_de_protocolo.modules.protocol_module.domain;

import io.github.jvondoellinger.api_gerenciamento_de_protocolo.modules.protocol_module.domain.valueObjects.DomainId;
import io.github.jvondoellinger.api_gerenciamento_de_protocolo.modules.protocol_module.domain.valueObjects.ProtocolNumber;

import java.time.LocalDateTime;

public class Interaction {
    // ? constructors -------------------------------------
    public Interaction(DomainId userId, String user, ProtocolNumber protocolNumber, String description) {
        this.id = new DomainId();
        this.userId = userId;
        this.user = user;
        this.protocolNumber = protocolNumber;
        this.description = description;
        this.interactedOn = LocalDateTime.now();
    }

    public Interaction(DomainId id, DomainId userId, String user, ProtocolNumber protocolNumber, String description, LocalDateTime interactedOn) {
        this.id = id;
        this.userId = userId;
        this.user = user;
        this.protocolNumber = protocolNumber;
        this.description = description;
        this.interactedOn = interactedOn;
    }

    // ? static methods -------------------------------------
    public static Interaction create(DomainId agentId, String agent, ProtocolNumber protocolNumber, String text) {
        return new Interaction(agentId, agent, protocolNumber, text);
    }
    public static Interaction initialize(DomainId id, DomainId agentId, String agent, ProtocolNumber protocolNumber, String text, LocalDateTime interactedOn) {
        return new Interaction(id, agentId, agent, protocolNumber, text, interactedOn);
    }

    // ? properties -------------------------------------
    private DomainId id;

    private DomainId userId;
    private String user;

    private ProtocolNumber protocolNumber;

    private String description;

    private LocalDateTime interactedOn;

    // ? getters -------------------------------------
    public DomainId getId() {
        return id;
    }

    public DomainId getUserId() {
        return userId;
    }

    public String getUser() {
        return user;
    }

    public ProtocolNumber getProtocolNumber() {
        return protocolNumber;
    }

    public String getDescription() {
        return description;
    }

    public LocalDateTime getInteractedOn() {
        return interactedOn;
    }
}
