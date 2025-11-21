package io.github.jvondoellinger.api_gerenciamento_de_protocolo.modules.protocol_module.infrastructure.subs;

import io.github.jvondoellinger.api_gerenciamento_de_protocolo.modules.protocol_module.domain.annotiation.ImplementsAfter;
import io.github.jvondoellinger.api_gerenciamento_de_protocolo.modules.protocol_module.domain.events.CreateProtocolEvent;
import io.github.jvondoellinger.api_gerenciamento_de_protocolo.modules.protocol_module.domain.events.sub.DomainEventHandler;
import io.github.jvondoellinger.api_gerenciamento_de_protocolo.modules.protocol_module.domain.contracts.persistence.ProtocolRepository;
import io.github.jvondoellinger.api_gerenciamento_de_protocolo.modules.protocol_module.infrastructure.database.composite.ProtocolValidationComposite;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
@ImplementsAfter
public class CreateProtocoloEventHandler implements DomainEventHandler<CreateProtocolEvent> {
    private final ProtocolRepository repository;
    //private final DomainEventPublisher<CreatedProtocolEvent> publisher;
    private final ProtocolValidationComposite composite;

    public CreateProtocoloEventHandler(ProtocolRepository repository, ProtocolValidationComposite composite) {
          this.repository = repository;
          // this.publisher = publisher;
          this.composite = composite;
    }

    @Override
    public Mono<Void> handle(CreateProtocolEvent event) {
            var protocolo = event.getProtocolo();
            return composite
                  .validate(protocolo) // Validate
                  .then(repository.save(protocolo)) // Save on persistence layer (db)
                  .doOnNext(saved -> {
                    //  f (publisher != null)
                        //publisher.publish(new CreatedProtocolEvent(saved)); // Call the next subscribers
                  })
                  .then();
    }
}
