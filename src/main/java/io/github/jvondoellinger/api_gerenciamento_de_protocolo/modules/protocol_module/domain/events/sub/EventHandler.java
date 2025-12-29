package io.github.jvondoellinger.api_gerenciamento_de_protocolo.modules.protocol_module.domain.events.sub;

import io.github.jvondoellinger.api_gerenciamento_de_protocolo.modules.protocol_module.domain.events.DomainEvent;
import reactor.core.publisher.Mono;

public interface EventHandler<TEvent extends DomainEvent>  {
    Mono<Void> handle(TEvent event);
}
