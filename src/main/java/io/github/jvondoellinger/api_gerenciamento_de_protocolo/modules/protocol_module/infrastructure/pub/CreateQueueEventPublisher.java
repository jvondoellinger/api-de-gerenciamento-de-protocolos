package io.github.jvondoellinger.api_gerenciamento_de_protocolo.modules.protocol_module.infrastructure.pub;

import io.github.jvondoellinger.api_gerenciamento_de_protocolo.modules.protocol_module.domain.events.CreateQueueEvent;
import io.github.jvondoellinger.api_gerenciamento_de_protocolo.modules.protocol_module.domain.events.pub.DomainEventPublisher;
import io.github.jvondoellinger.api_gerenciamento_de_protocolo.modules.protocol_module.domain.events.sub.DomainEventHandler;
import io.github.jvondoellinger.api_gerenciamento_de_protocolo.modules.protocol_module.infrastructure.permission.annotation.HasPermission;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.List;

@Service
public class CreateQueueEventPublisher implements DomainEventPublisher<CreateQueueEvent> {
    private final List<DomainEventHandler<CreateQueueEvent>> handlers;

    public CreateQueueEventPublisher(DomainEventHandler<CreateQueueEvent> handlers) {
        this.handlers = new ArrayList<>();
        this.handlers.add(handlers);
    }

    @Override
    public Mono<Void> publish(CreateQueueEvent event) {
        return Flux
                .fromIterable(handlers) // Get Flux<DomainEventHandler<CreateProtocolEvent>>
                .parallel() // Activate parallelism (several actions at the same time)
                .flatMap(x -> x.handle(event)) // Call the handler
                .then();
    }

    @Override
    public Class<CreateQueueEvent> eventType() {
        return CreateQueueEvent.class;
    }
}
