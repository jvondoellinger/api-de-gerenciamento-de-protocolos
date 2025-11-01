package io.github.jvondoellinger.api_gerenciamento_de_protocolo.modules.protocol_module.domain;

import io.github.jvondoellinger.api_gerenciamento_de_protocolo.modules.protocol_module.domain.valueObjects.DomainId;
import io.github.jvondoellinger.api_gerenciamento_de_protocolo.modules.protocol_module.domain.valueObjects.ProtocolNumber;

import java.time.LocalDateTime;

public class Interaction {
    // ? constructors -------------------------------------
    public Interaction(DomainId agentId, String agent, ProtocolNumber protocolNumber, String text) {
        this.id = new DomainId();
        this.agentId = agentId;
        this.agent = agent;
        this.protocolNumber = protocolNumber;
        this.text = text;
        this.interactedOn = LocalDateTime.now();
    }

    public Interaction(DomainId id, DomainId agentId, String agent, ProtocolNumber protocolNumber, String text, LocalDateTime interactedOn) {
        this.id = id;
        this.agentId = agentId;
        this.agent = agent;
        this.protocolNumber = protocolNumber;
        this.text = text;
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

    private DomainId agentId;
    private String agent;

    private ProtocolNumber protocolNumber;

    private String text;

    private LocalDateTime interactedOn;

    // ? getters -------------------------------------
    public DomainId getId() {
        return id;
    }

    public DomainId getAgentId() {
        return agentId;
    }

    public String getAgent() {
        return agent;
    }

    public ProtocolNumber getProtocolNumber() {
        return protocolNumber;
    }

    public String getText() {
        return text;
    }

    public LocalDateTime getInteractedOn() {
        return interactedOn;
    }
}
