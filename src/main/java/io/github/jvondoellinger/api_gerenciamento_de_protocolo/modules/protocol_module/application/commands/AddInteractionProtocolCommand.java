package io.github.jvondoellinger.api_gerenciamento_de_protocolo.modules.protocol_module.application.commands;

import io.github.jvondoellinger.api_gerenciamento_de_protocolo.modules.protocol_module.domain.valueObjects.DomainId;
import io.github.jvondoellinger.api_gerenciamento_de_protocolo.modules.protocol_module.domain.valueObjects.ProtocolNumber;

public class AddInteractionProtocolCommand {
    private DomainId agentId;
    private String agent;

    private ProtocolNumber protocolNumber;
    private String text;

    public AddInteractionProtocolCommand(DomainId agentId, String agent, ProtocolNumber protocolNumber, String text) {
        this.agentId = agentId;
        this.agent = agent;
        this.protocolNumber = protocolNumber;
        this.text = text;
    }

    public DomainId getAgentId() {
        return agentId;
    }

    public void setAgentId(DomainId agentId) {
        this.agentId = agentId;
    }

    public String getAgent() {
        return agent;
    }

    public void setAgent(String agent) {
        this.agent = agent;
    }

    public ProtocolNumber getProtocolNumber() {
        return protocolNumber;
    }

    public void setProtocolNumber(ProtocolNumber protocolNumber) {
        this.protocolNumber = protocolNumber;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
