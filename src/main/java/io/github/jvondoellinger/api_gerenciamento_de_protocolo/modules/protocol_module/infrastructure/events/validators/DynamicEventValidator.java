package io.github.jvondoellinger.api_gerenciamento_de_protocolo.modules.protocol_module.infrastructure.events.validators;

import io.github.jvondoellinger.api_gerenciamento_de_protocolo.modules.protocol_module.domain.events.DomainEvent;
import io.github.jvondoellinger.api_gerenciamento_de_protocolo.modules.protocol_module.infrastructure.exceptions.UnresolvedServiceException;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class DynamicEventValidator {
	private final Map<Class<?>, EventValidator<?>> validators;

	public DynamicEventValidator(List<EventValidator<?>> validators) {
		this.validators = validators
			   .stream()
			   .collect(
					 Collectors.toMap(EventValidator::eventType, v -> v)
			   );
	}

	@SuppressWarnings("unchecked")
	public <T extends DomainEvent> Mono<T> validate(T event) {

		var validator = validators.get(event.getClass());

		if (validator == null) {
			throw new UnresolvedServiceException("Have any validator to this type: %s.".formatted(event.getClass().toGenericString()));
		}

		var castingValidator = (EventValidator<T>) validator;

		return castingValidator.validate(event);
	}
}
