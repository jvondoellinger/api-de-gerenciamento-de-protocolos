package io.github.jvondoellinger.api_gerenciamento_de_protocolo.modules.protocol_module.domain.events;

import io.github.jvondoellinger.api_gerenciamento_de_protocolo.modules.protocol_module.domain.Interaction;
import io.github.jvondoellinger.api_gerenciamento_de_protocolo.modules.protocol_module.domain.valueObjects.DomainId;
import io.github.jvondoellinger.api_gerenciamento_de_protocolo.modules.protocol_module.domain.valueObjects.ProtocolNumber;

public class AddInteractionProtocolEvent extends UserActivityEvent {
	private final Interaction interaction;
	private final ProtocolNumber protocolNumber;

	public AddInteractionProtocolEvent(Interaction interaction, ProtocolNumber protocolNumber) {
		super(interaction.getUserId());
		this.interaction = interaction;
		this.protocolNumber = protocolNumber;
	}

	public Interaction getInteraction() {
		return interaction;
	}
	public ProtocolNumber getProtocolNumber() {
		return protocolNumber;
	}
}
