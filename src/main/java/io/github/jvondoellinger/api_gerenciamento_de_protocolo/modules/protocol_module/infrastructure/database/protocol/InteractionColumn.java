package io.github.jvondoellinger.api_gerenciamento_de_protocolo.modules.protocol_module.infrastructure.database.protocol;

import io.github.jvondoellinger.api_gerenciamento_de_protocolo.modules.protocol_module.domain.Interaction;
import io.github.jvondoellinger.api_gerenciamento_de_protocolo.modules.protocol_module.domain.valueObjects.DomainId;
import io.github.jvondoellinger.api_gerenciamento_de_protocolo.modules.protocol_module.infrastructure.database.ObjectEntity;
import org.springframework.data.annotation.PersistenceCreator;
import org.springframework.data.cassandra.core.mapping.UserDefinedType;

import java.time.LocalDateTime;
import java.util.UUID;

@UserDefinedType("interaction")
public class InteractionColumn implements ObjectEntity<Interaction> {
	private UUID agentId;
	private String agent;
	private String text;
	private LocalDateTime interactedOn;

	@PersistenceCreator
	public InteractionColumn(UUID agentId,
						String agent,
						String text,
						LocalDateTime interactedOn) {
		this.agentId = agentId;
		this.agent = agent;
		this.text = text;
		this.interactedOn = interactedOn;
	}

	public Interaction parse() {
		return Interaction.initialize(
			   DomainId.from(agentId),
			   agent,
			   text,
			   interactedOn
		);
	}

	public static InteractionColumn create(Interaction interaction) {
		return new InteractionColumn(
			   interaction.getUserId().getValue(),
			   interaction.getUser(),
			   interaction.getDescription(),
			   interaction.getInteractedOn()
		);
	}

	public UUID getAgentId() {
		return agentId;
	}
	public String getAgent() {
		return agent;
	}
	public String getText() {
		return text;
	}
	public LocalDateTime getInteractedOn() {
		return interactedOn;
	}

	public void setAgentId(UUID agentId) {
		this.agentId = agentId;
	}
	public void setAgent(String agent) {
		this.agent = agent;
	}
	public void setText(String text) {
		this.text = text;
	}
	public void setInteractedOn(LocalDateTime interactedOn) {
		this.interactedOn = interactedOn;
	}
}
