package io.github.jvondoellinger.api_gerenciamento_de_protocolo.modules.protocol_module.infrastructure.events.validators;

import io.github.jvondoellinger.api_gerenciamento_de_protocolo.modules.protocol_module.domain.events.CreateProtocolEvent;
import io.github.jvondoellinger.api_gerenciamento_de_protocolo.modules.protocol_module.infrastructure.composite.ProtocolValidationComposite;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public class CreateProtocolEventValidator implements EventValidator<CreateProtocolEvent> {
	private final ProtocolValidationComposite composite;

	public CreateProtocolEventValidator(ProtocolValidationComposite composite) {
		this.composite = composite;
	}

	@Override
	public Mono<CreateProtocolEvent> validate(CreateProtocolEvent event) {
		var protocol = event.getProtocolo();

		return composite
			   .validate(protocol)
			   .thenReturn(event);
	}

	@Override
	public Class<CreateProtocolEvent> eventType() {
		return CreateProtocolEvent.class;
	}
}
