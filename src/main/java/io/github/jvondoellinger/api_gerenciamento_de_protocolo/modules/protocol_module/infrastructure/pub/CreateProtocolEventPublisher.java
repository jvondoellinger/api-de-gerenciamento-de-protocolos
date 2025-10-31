package io.github.jvondoellinger.api_gerenciamento_de_protocolo.modules.protocol_module.infrastructure.pub;

import io.github.jvondoellinger.api_gerenciamento_de_protocolo.modules.protocol_module.domain.events.CreateProtocolEvent;
import io.github.jvondoellinger.api_gerenciamento_de_protocolo.modules.protocol_module.domain.events.pub.DomainEventPublisher;
import io.github.jvondoellinger.api_gerenciamento_de_protocolo.modules.protocol_module.domain.events.sub.DomainEventHandler;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.List;

@Service
public class CreateProtocolEventPublisher implements DomainEventPublisher<CreateProtocolEvent> {
    private final List<DomainEventHandler<CreateProtocolEvent>> handlers;

    public CreateProtocolEventPublisher(DomainEventHandler<CreateProtocolEvent> handlers) {
        this.handlers = new ArrayList<>();
        this.handlers.add(handlers);
    }

    @Override
    public Mono<Void> publish(CreateProtocolEvent event) {
        return Flux.fromIterable(handlers) // Get Flux<DomainEventHandler<CreateProtocolEvent>>
                .parallel() // Activate parallelism (several actions at the same time)
                .flatMap(x -> x.handle(event)) // Call the handler
                .then();
    }

    @Override
    public Class<CreateProtocolEvent> eventType() {
        return CreateProtocolEvent.class;
    }
}
