package io.github.jvondoellinger.api_gerenciamento_de_protocolo.modules.protocol_module.infrastructure.events.subs;

import io.github.jvondoellinger.api_gerenciamento_de_protocolo.modules.protocol_module.domain.annotiation.ImplementsAfter;
import io.github.jvondoellinger.api_gerenciamento_de_protocolo.modules.protocol_module.domain.events.CreateProtocolEvent;
import io.github.jvondoellinger.api_gerenciamento_de_protocolo.modules.protocol_module.domain.events.sub.DomainEventHandler;
import io.github.jvondoellinger.api_gerenciamento_de_protocolo.modules.protocol_module.domain.contracts.persistence.ProtocolWriteRepository;
import io.github.jvondoellinger.api_gerenciamento_de_protocolo.modules.protocol_module.infrastructure.composite.ProtocolValidationComposite;
import io.github.jvondoellinger.api_gerenciamento_de_protocolo.modules.protocol_module.infrastructure.events.subs.resolvers.DomainDynamicPublisher;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
@ImplementsAfter
public class CreateProtocolEventHandler implements DomainEventHandler<CreateProtocolEvent> {
    private final ProtocolWriteRepository repository;
    //private final DomainDynamicPublisher publisher;
    private final ProtocolValidationComposite composite;

    public CreateProtocolEventHandler(ProtocolWriteRepository repository, ProtocolValidationComposite composite) {
          this.repository = repository;
          //this.publisher = publisher;
          this.composite = composite;
    }

    @Override
    public Mono<Void> handle(CreateProtocolEvent event) {
            var protocol = event.getProtocolo();
            return composite
                  .validate(protocol) // Validate
                  .then(repository.save(protocol)) // Save on persistence layer (db)
/*                  .doOnNext(saved -> {
                    if (publisher != null)
                        publisher.publish(new CreatedProtocolEvent(saved)); // Call the next subscribers
                  })*/
                  .then();
    }
}
