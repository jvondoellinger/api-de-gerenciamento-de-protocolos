package io.github.jvondoellinger.api_gerenciamento_de_protocolo.modules.protocol_module.infrastructure.events.subs.resolvers;

import io.github.jvondoellinger.api_gerenciamento_de_protocolo.modules.protocol_module.domain.events.DomainEvent;
import io.github.jvondoellinger.api_gerenciamento_de_protocolo.modules.protocol_module.domain.events.pub.DomainEventPublisher;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.List;

@Service
public class DomainDynamicPublisher {
    private final List<DomainEventPublisher<?>> publishers;

    public DomainDynamicPublisher(List<DomainEventPublisher<?>> publishers) {
        this.publishers = publishers;
    }

    public <TEvent extends DomainEvent> Mono<Void> publish(TEvent event) {
          //noinspection unchecked
        var resolver = (DomainEventPublisher<TEvent>) resolve(event.getClass());
        return resolver.publish(event);
    }

    public <TEvent extends DomainEvent> DomainEventPublisher<TEvent> resolve(Class<TEvent> eventClass) {
        for (var publisher : publishers) {
            if (publisher.eventType().isAssignableFrom(eventClass))
                //noinspection unchecked
                return (DomainEventPublisher<TEvent>) publisher;
        }
        throw new RuntimeException("Don't have any publisher for type: %s".formatted(eventClass.getName()));
    }
}
