package io.github.jvondoellinger.api_gerenciamento_de_protocolo.modules.protocol_module.infrastructure.events.subs;

import io.github.jvondoellinger.api_gerenciamento_de_protocolo.modules.protocol_module.domain.contracts.persistence.AttachmentStoragePort;
import io.github.jvondoellinger.api_gerenciamento_de_protocolo.modules.protocol_module.domain.events.AddAttachmentProtocolEvent;
import io.github.jvondoellinger.api_gerenciamento_de_protocolo.modules.protocol_module.domain.events.sub.EventHandler;
import io.github.jvondoellinger.api_gerenciamento_de_protocolo.modules.protocol_module.infrastructure.events.validators.proxy.EventValidatorProxy;
import io.github.jvondoellinger.api_gerenciamento_de_protocolo.modules.protocol_module.infrastructure.permission.annotation.HasPermission;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public class AddAttachmentEventHandler implements EventHandler<AddAttachmentProtocolEvent> {
	private final AttachmentStoragePort storagePort;

	public AddAttachmentEventHandler(AttachmentStoragePort storagePort) {
		this.storagePort = storagePort;
	}

	@Override
	public Mono<Void> handle(AddAttachmentProtocolEvent event) {
		return storagePort.upload(event.getPath(), event.getBuffer()).then();
	}
}

