package io.github.jvondoellinger.api_gerenciamento_de_protocolo.modules.protocol_module.infrastructure.events.validators;

import io.github.jvondoellinger.api_gerenciamento_de_protocolo.modules.protocol_module.domain.contracts.persistence.ProtocolReadRepository;
import io.github.jvondoellinger.api_gerenciamento_de_protocolo.modules.protocol_module.domain.contracts.persistence.filters.ProtocolNumberFilter;
import io.github.jvondoellinger.api_gerenciamento_de_protocolo.modules.protocol_module.domain.events.AddInteractionProtocolEvent;
import io.github.jvondoellinger.api_gerenciamento_de_protocolo.modules.protocol_module.domain.events.DomainEvent;
import io.github.jvondoellinger.api_gerenciamento_de_protocolo.modules.protocol_module.domain.exception.DomainException;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public class AddInteractionProtocolEventValidator implements EventValidator<AddInteractionProtocolEvent>{
	private final ProtocolReadRepository readRepository;

	public AddInteractionProtocolEventValidator(ProtocolReadRepository readRepository) {
		this.readRepository = readRepository;
	}

	// Metodo abaixo tem a seguinte função:
	// - Verifica se o protocolo existe ou não
	// - Caso não, retorna uma exception
	@Override
	public Mono<AddInteractionProtocolEvent> validate(AddInteractionProtocolEvent event) {

		var protocolNumber = event.getInteraction().getProtocolNumber();
		var filter = ProtocolNumberFilter.create(protocolNumber);

		return readRepository
			   .exists(filter)
			   .filter(Boolean::booleanValue)
			   .switchIfEmpty(Mono.error( new DomainException("Any protocol contains this number")))
			   .thenReturn(event);
	}

	@Override
	public Class<AddInteractionProtocolEvent> eventType() {
		return AddInteractionProtocolEvent.class;
	}
}
