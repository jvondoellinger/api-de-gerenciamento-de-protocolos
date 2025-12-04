package io.github.jvondoellinger.api_gerenciamento_de_protocolo.modules.protocol_module.domain;

import io.github.jvondoellinger.api_gerenciamento_de_protocolo.modules.protocol_module.domain.valueObjects.DomainId;
import io.github.jvondoellinger.api_gerenciamento_de_protocolo.modules.protocol_module.domain.valueObjects.ProtocolNumber;

import java.time.LocalDateTime;

public class Interaction {
    // ? constructors -------------------------------------
    public Interaction(DomainId userId, String user, String description) {
        this.userId = userId;
        this.user = user;
        this.description = description;
        this.interactedOn = LocalDateTime.now();
    }

    public Interaction(DomainId userId,
				   String user,
				   String description,
				   LocalDateTime interactedOn) {
        this.userId = userId;
        this.user = user;
        this.description = description;
        this.interactedOn = interactedOn;
    }

    // ? static methods -------------------------------------
    public static Interaction create(DomainId agentId, String agent, String text) {
        return new Interaction(agentId, agent, text);
    }
    public static Interaction initialize(DomainId agentId, String agent, String text, LocalDateTime interactedOn) {
        return new Interaction(agentId, agent, text, interactedOn);
    }

    // ? properties -------------------------------------
    private final DomainId userId;
    private final String user;
    private final String description;
    private final LocalDateTime interactedOn;

    // ? getters -------------------------------------
    public DomainId getUserId() {
        return userId;
    }
    public String getUser() {
        return user;
    }
    public String getDescription() {
        return description;
    }
    public LocalDateTime getInteractedOn() {
        return interactedOn;
    }
}
