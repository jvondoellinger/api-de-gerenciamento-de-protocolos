package io.github.jvondoellinger.api_gerenciamento_de_protocolo.modules.protocol_module.infrastructure.events.pub;

import io.github.jvondoellinger.api_gerenciamento_de_protocolo.modules.protocol_module.domain.events.CreateProtocolEvent;
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
public class CreateProtocolEventPublisher implements EventPublisher<CreateProtocolEvent> {
    private final List<EventHandler<CreateProtocolEvent>> handlers;

    public CreateProtocolEventPublisher(EventHandler<CreateProtocolEvent> handlers) {
        this.handlers = new ArrayList<>();
        this.handlers.add(handlers);
    }

    @Override
    @HasPermission
    @EventValidatorProxy
    public Mono<Void> publish(CreateProtocolEvent event) {
        return Flux.fromIterable(handlers) // Get Flux<EventHandler<CreateProtocolEvent>>
                .parallel() // Activate parallelism (several actions at the same time)
                .flatMap(x -> x.handle(event)) // Call the handler
                .then();
    }

    @Override
    public Class<CreateProtocolEvent> eventType() {
        return CreateProtocolEvent.class;
    }
}
