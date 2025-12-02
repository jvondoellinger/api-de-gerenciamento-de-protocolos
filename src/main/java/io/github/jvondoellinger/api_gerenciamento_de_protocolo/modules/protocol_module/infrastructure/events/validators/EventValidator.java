package io.github.jvondoellinger.api_gerenciamento_de_protocolo.modules.protocol_module.infrastructure.events.validators;

import io.github.jvondoellinger.api_gerenciamento_de_protocolo.modules.protocol_module.domain.events.DomainEvent;
import reactor.core.publisher.Mono;

public interface EventValidator<T extends DomainEvent> {
	Mono<T> validate(T event);
	Class<T> eventType();
}
