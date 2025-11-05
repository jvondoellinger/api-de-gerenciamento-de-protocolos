package io.github.jvondoellinger.api_gerenciamento_de_protocolo.modules.protocol_module.infrastructure.pub;

import io.github.jvondoellinger.api_gerenciamento_de_protocolo.modules.protocol_module.domain.events.AddInteractionProtocolEvent;
import io.github.jvondoellinger.api_gerenciamento_de_protocolo.modules.protocol_module.domain.events.pub.DomainEventPublisher;
import io.github.jvondoellinger.api_gerenciamento_de_protocolo.modules.protocol_module.domain.events.sub.DomainEventHandler;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.List;

@Service
public class AddInteractionProtocolEventPublisher implements DomainEventPublisher<AddInteractionProtocolEvent> {
    private final List<DomainEventHandler<AddInteractionProtocolEvent>> handlers;

    public AddInteractionProtocolEventPublisher(DomainEventHandler<AddInteractionProtocolEvent> handler) {
        this.handlers = new ArrayList<>(); // Inicialiando assim devido a só haver um handler (enquanto não são adicionados outros)
        handlers.add(handler);
    }

    @Override
    public Mono<Void> publish(AddInteractionProtocolEvent event) {
        return Flux.fromIterable(handlers)
                .parallel()
                .flatMap(handler -> handler.handle(event))
                .then();
    }

    @Override
    public Class<AddInteractionProtocolEvent> eventType() {
        return AddInteractionProtocolEvent.class;
    }
}
