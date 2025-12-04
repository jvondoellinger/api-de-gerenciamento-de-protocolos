package io.github.jvondoellinger.api_gerenciamento_de_protocolo.modules.protocol_module.application.mappers;

import io.github.jvondoellinger.api_gerenciamento_de_protocolo.modules.protocol_module.application.commands.CreateProtocolCommand;
import io.github.jvondoellinger.api_gerenciamento_de_protocolo.modules.protocol_module.domain.Protocol;
import io.github.jvondoellinger.api_gerenciamento_de_protocolo.modules.protocol_module.domain.Queue;
import io.github.jvondoellinger.api_gerenciamento_de_protocolo.modules.protocol_module.domain.events.CreateProtocolEvent;
import io.github.jvondoellinger.api_gerenciamento_de_protocolo.modules.protocol_module.domain.valueObjects.DomainId;

public class CreateProtocolEventMapper {
	private CreateProtocolEventMapper() {
	}

	public static CreateProtocolEvent map(CreateProtocolCommand command) {
		var description = command.description();
		var queueId = DomainId.from(command.queueId());
		var queue = Queue.createWithIdOnly(queueId);
		var createdBy = command.createdBy();
		var userId = DomainId.from(command.userId());

		var protocolo = new Protocol(
			   queue,
			   description,
			   createdBy,
			   userId
		);
		return new CreateProtocolEvent(protocolo);
	}
}
