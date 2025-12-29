package io.github.jvondoellinger.api_gerenciamento_de_protocolo.modules.protocol_module.infrastructure.events.pub;

import io.github.jvondoellinger.api_gerenciamento_de_protocolo.modules.protocol_module.domain.events.AddInteractionProtocolEvent;
import io.github.jvondoellinger.api_gerenciamento_de_protocolo.modules.protocol_module.domain.events.pub.EventPublisher;
import io.github.jvondoellinger.api_gerenciamento_de_protocolo.modules.protocol_module.domain.events.sub.EventHandler;
import io.github.jvondoellinger.api_gerenciamento_de_protocolo.modules.protocol_module.infrastructure.events.validators.proxy.EventValidatorProxy;
import io.github.jvondoellinger.api_gerenciamento_de_protocolo.modules.protocol_module.infrastructure.permission.annotation.HasPermission;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.List;

@Service
public class AddInteractionProtocolEventPublisher implements EventPublisher<AddInteractionProtocolEvent> {
    private final List<EventHandler<AddInteractionProtocolEvent>> handlers;

    public AddInteractionProtocolEventPublisher(EventHandler<AddInteractionProtocolEvent> handler) {
        this.handlers = new ArrayList<>(); // Inicialiando assim devido a só haver um handler (enquanto não são adicionados outros)
        handlers.add(handler);
    }

    @Override
    @HasPermission
    @EventValidatorProxy
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
