package io.github.jvondoellinger.api_gerenciamento_de_protocolo.modules.protocol_module.infrastructure.database.interactions;

import io.github.jvondoellinger.api_gerenciamento_de_protocolo.modules.protocol_module.domain.Interaction;
import io.github.jvondoellinger.api_gerenciamento_de_protocolo.modules.protocol_module.domain.valueObjects.DomainId;
import io.github.jvondoellinger.api_gerenciamento_de_protocolo.modules.protocol_module.domain.valueObjects.ProtocolNumber;
import io.github.jvondoellinger.api_gerenciamento_de_protocolo.modules.protocol_module.infrastructure.database.ObjectEntity;
import org.springframework.data.annotation.PersistenceCreator;
import org.springframework.data.cassandra.core.cql.Ordering;
import org.springframework.data.cassandra.core.cql.PrimaryKeyType;
import org.springframework.data.cassandra.core.mapping.PrimaryKeyColumn;
import org.springframework.data.cassandra.core.mapping.Table;

import java.time.LocalDateTime;
import java.util.UUID;

@Table("interaction")
public class InteractionEntity implements ObjectEntity<Interaction> {

    @PrimaryKeyColumn(name = "protocolNumber", type = PrimaryKeyType.PARTITIONED)
    private String protocolNumber;

    @PrimaryKeyColumn(name = "id", type = PrimaryKeyType.CLUSTERED, ordering = Ordering.DESCENDING)
    private UUID id;

    private UUID agentId;
    private String agent;
    private String text;
    private LocalDateTime interactedOn;



    @PersistenceCreator
    public InteractionEntity(UUID id, UUID agentId, String agent, String protocolNumber, String text, LocalDateTime interactedOn) {
        this.id = id;
        this.agentId = agentId;
        this.agent = agent;
        this.protocolNumber = protocolNumber;
        this.text = text;
        this.interactedOn = interactedOn;
    }

    public Interaction parse() {
        return Interaction.initialize(
            new DomainId(id),
            new DomainId(agentId),
            agent,
            ProtocolNumber.parse(protocolNumber),
            text,
            interactedOn
        );
    }
    public static InteractionEntity create(Interaction interaction) {
        return new InteractionEntity(
                interaction.getId().getValue(),
                interaction.getUserId().getValue(),
                interaction.getUser(),
                interaction.getProtocolNumber().getValue(),
                interaction.getDescription(),
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
