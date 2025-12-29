package io.github.jvondoellinger.api_gerenciamento_de_protocolo.modules.protocol_module.domain.events.pub;

import io.github.jvondoellinger.api_gerenciamento_de_protocolo.modules.protocol_module.domain.events.DomainEvent;
import reactor.core.publisher.Mono;

public interface EventPublisher<TEvent extends DomainEvent> {
    Mono<Void> publish(TEvent event);
    Class<TEvent> eventType();
}
