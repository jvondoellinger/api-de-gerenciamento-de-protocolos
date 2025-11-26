package io.github.jvondoellinger.api_gerenciamento_de_protocolo.modules.protocol_module.infrastructure.events.subs;

import io.github.jvondoellinger.api_gerenciamento_de_protocolo.modules.protocol_module.domain.contracts.persistence.QueuesWriteRepository;
import io.github.jvondoellinger.api_gerenciamento_de_protocolo.modules.protocol_module.domain.events.CreateQueueEvent;
import io.github.jvondoellinger.api_gerenciamento_de_protocolo.modules.protocol_module.domain.events.sub.DomainEventHandler;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public class CreateQueueEventHandler implements DomainEventHandler<CreateQueueEvent> {
    private final QueuesWriteRepository repository;

    public CreateQueueEventHandler(QueuesWriteRepository repository) {
        this.repository = repository;
    }

    @Override
    public Mono<Void> handle(CreateQueueEvent event) {
        return repository
                .save(event.getQueue()) // Save entity on repository
                .then(); // Transform Mono<Protocol> to Mono<Void>
    }
}
