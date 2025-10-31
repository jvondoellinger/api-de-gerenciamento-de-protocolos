package io.github.jvondoellinger.api_gerenciamento_de_protocolo.modules.protocol_module.Infrastructure.database.serialize;

import io.github.jvondoellinger.api_gerenciamento_de_protocolo.modules.protocol_module.domain.Interaction;
import io.github.jvondoellinger.api_gerenciamento_de_protocolo.modules.protocol_module.domain.valueObjects.ProtocolDomainId;
import io.github.jvondoellinger.api_gerenciamento_de_protocolo.modules.protocol_module.domain.valueObjects.ProtocolNumber;
import org.springframework.data.annotation.PersistenceCreator;
import org.springframework.data.cassandra.core.cql.Ordering;
import org.springframework.data.cassandra.core.cql.PrimaryKeyType;
import org.springframework.data.cassandra.core.mapping.PrimaryKey;
import org.springframework.data.cassandra.core.mapping.PrimaryKeyColumn;
import org.springframework.data.cassandra.core.mapping.Table;

import java.time.LocalDateTime;
import java.util.UUID;

@Table("interaction")
public class InteractionSerializable implements ObjectSerialize<Interaction> {

    @PrimaryKeyColumn(name = "protocolNumber", type = PrimaryKeyType.PARTITIONED)
    private String protocolNumber;

    @PrimaryKeyColumn(name = "id", type = PrimaryKeyType.CLUSTERED, ordering = Ordering.DESCENDING)
    private UUID id;

    private UUID agentId;
    private String agent;
    private String text;
    private LocalDateTime interactedOn;



    @PersistenceCreator
    public InteractionSerializable(UUID id, UUID agentId, String agent, String protocolNumber, String text, LocalDateTime interactedOn) {
        this.id = id;
        this.agentId = agentId;
        this.agent = agent;
        this.protocolNumber = protocolNumber;
        this.text = text;
        this.interactedOn = interactedOn;
    }

    public Interaction parse() {
        return Interaction.initialize(
            new ProtocolDomainId(id),
            new ProtocolDomainId(agentId),
            agent,
            ProtocolNumber.parse(protocolNumber),
            text,
            interactedOn
        );
    }
    public static InteractionSerializable create(Interaction interaction) {
        return new InteractionSerializable(
                interaction.getId().getValue(),
                interaction.getAgentId().getValue(),
                interaction.getAgent(),
                interaction.getProtocolNumber().getValue(),
                interaction.getText(),
                interaction.getInteractedOn()
        );
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public UUID getAgentId() {
        return agentId;
    }

    public void setAgentId(UUID agentId) {
        this.agentId = agentId;
    }

    public String getAgent() {
        return agent;
    }

    public void setAgent(String agent) {
        this.agent = agent;
    }

    public String getProtocolNumber() {
        return protocolNumber;
    }

    public void setProtocolNumber(String protocolNumber) {
        this.protocolNumber = protocolNumber;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public LocalDateTime getInteractedOn() {
        return interactedOn;
    }

    public void setInteractedOn(LocalDateTime interactedOn) {
        this.interactedOn = interactedOn;
    }
}
