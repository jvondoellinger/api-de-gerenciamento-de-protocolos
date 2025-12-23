package io.github.jvondoellinger.api_gerenciamento_de_protocolo.modules.protocol_module.infrastructure.events.validators;

import io.github.jvondoellinger.api_gerenciamento_de_protocolo.modules.protocol_module.domain.contracts.persistence.UserProfileReadRepository;
import io.github.jvondoellinger.api_gerenciamento_de_protocolo.modules.protocol_module.domain.events.CreateUserProfileEvent;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.Objects;

@Service
public class CreateUserProfileEventValidator implements EventValidator<CreateUserProfileEvent> {
	private final UserProfileReadRepository readRepository;

	public CreateUserProfileEventValidator(UserProfileReadRepository readRepository) {
		this.readRepository = readRepository;
	}

	@Override
	public Mono<CreateUserProfileEvent> validate(CreateUserProfileEvent event) {
		return readRepository
			   .queryByUserId(event.getUserId())
			   .filter(this::isNotNull)
			   .map(x -> {
				   throw new RuntimeException("Already exists a profile to this user.");
			   })
			   .thenReturn(event);
	}

	@Override
	public Class<CreateUserProfileEvent> eventType() {
		return CreateUserProfileEvent.class;
	}

	private <T> boolean isNotNull(T obj) {
		return !Objects.isNull(obj);
	}
}
