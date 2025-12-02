package io.github.jvondoellinger.api_gerenciamento_de_protocolo.modules.protocol_module.infrastructure.events.validators;

import io.github.jvondoellinger.api_gerenciamento_de_protocolo.modules.protocol_module.domain.annotiation.ImplementsAfter;
import io.github.jvondoellinger.api_gerenciamento_de_protocolo.modules.protocol_module.domain.contracts.persistence.QueueReadRepository;
import io.github.jvondoellinger.api_gerenciamento_de_protocolo.modules.protocol_module.domain.events.CreateQueueEvent;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public class CreateQueueEventValidator implements EventValidator<CreateQueueEvent> {
	private final QueueReadRepository readRepository;

	public CreateQueueEventValidator(QueueReadRepository readRepository) {
		this.readRepository = readRepository;
	}

	@Override
	@ImplementsAfter
	public Mono<CreateQueueEvent> validate(CreateQueueEvent event) {
		return Mono.just(event);
	}

	@Override
	public Class<CreateQueueEvent> eventType() {
		return CreateQueueEvent.class;
	}
}
