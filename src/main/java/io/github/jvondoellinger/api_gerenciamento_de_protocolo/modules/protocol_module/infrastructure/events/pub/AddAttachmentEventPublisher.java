package io.github.jvondoellinger.api_gerenciamento_de_protocolo.modules.protocol_module.infrastructure.events.pub;

import io.github.jvondoellinger.api_gerenciamento_de_protocolo.modules.protocol_module.domain.events.AddAttachmentProtocolEvent;
import io.github.jvondoellinger.api_gerenciamento_de_protocolo.modules.protocol_module.domain.events.pub.EventPublisher;
import io.github.jvondoellinger.api_gerenciamento_de_protocolo.modules.protocol_module.domain.events.sub.EventHandler;
import io.github.jvondoellinger.api_gerenciamento_de_protocolo.modules.protocol_module.infrastructure.events.validators.proxy.EventValidatorProxy;
import io.github.jvondoellinger.api_gerenciamento_de_protocolo.modules.protocol_module.infrastructure.permission.annotation.HasPermission;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public class AddAttachmentEventPublisher implements EventPublisher<AddAttachmentProtocolEvent> {
	private final EventHandler<AddAttachmentProtocolEvent> handler;

	public AddAttachmentEventPublisher(EventHandler<AddAttachmentProtocolEvent> handler) {
		this.handler = handler;
	}

	@Override
	@EventValidatorProxy
	@HasPermission
	public Mono<Void> publish(AddAttachmentProtocolEvent event) {
		return handler.handle(event);
	}

	@Override
	public Class<AddAttachmentProtocolEvent> eventType() {
		return AddAttachmentProtocolEvent.class;
	}
}
