package io.github.jvondoellinger.api_gerenciamento_de_protocolo.modules.protocol_module.infrastructure.events.subs;

import io.github.jvondoellinger.api_gerenciamento_de_protocolo.modules.protocol_module.domain.contracts.persistence.ProtocolReadRepository;
import io.github.jvondoellinger.api_gerenciamento_de_protocolo.modules.protocol_module.domain.contracts.persistence.ProtocolWriteRepository;
import io.github.jvondoellinger.api_gerenciamento_de_protocolo.modules.protocol_module.domain.contracts.persistence.filters.ProtocolNumberFilter;
import io.github.jvondoellinger.api_gerenciamento_de_protocolo.modules.protocol_module.domain.events.AddInteractionProtocolEvent;
import io.github.jvondoellinger.api_gerenciamento_de_protocolo.modules.protocol_module.domain.events.sub.DomainEventHandler;
import io.github.jvondoellinger.api_gerenciamento_de_protocolo.modules.protocol_module.infrastructure.events.validators.proxy.EventValidatorProxy;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public class AddInteractionProtocolEventHandler implements DomainEventHandler<AddInteractionProtocolEvent> {
	private final ProtocolReadRepository readRepository;
	private final ProtocolWriteRepository writeRepository;

	public AddInteractionProtocolEventHandler(ProtocolReadRepository readRepository, ProtocolWriteRepository writeRepository) {
		this.readRepository = readRepository;
		this.writeRepository = writeRepository;
	}

	@Override
	@EventValidatorProxy
	public Mono<Void> handle(AddInteractionProtocolEvent event) {
		var interaction = event.getInteraction();

		var filter = ProtocolNumberFilter.create(event.getProtocolNumber());
		System.out.println("Salvando a interação no banco");
		return readRepository
			   .query(filter)
			   .switchIfEmpty(Mono.error(new RuntimeException("No protocols found.")))
			   .flatMap(protocol -> {
				   protocol.interact(interaction);
				   return writeRepository.update(protocol);
			   })
			   .then();
	}
}
