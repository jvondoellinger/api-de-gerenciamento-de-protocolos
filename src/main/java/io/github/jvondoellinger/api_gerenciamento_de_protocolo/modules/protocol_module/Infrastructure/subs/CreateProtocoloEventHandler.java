package io.github.jvondoellinger.api_gerenciamento_de_protocolo.modules.protocol_module.Infrastructure.subs;

import io.github.jvondoellinger.api_gerenciamento_de_protocolo.modules.protocol_module.domain.events.CreateProtocolEvent;
import io.github.jvondoellinger.api_gerenciamento_de_protocolo.modules.protocol_module.domain.events.CreatedProtocolEvent;
import io.github.jvondoellinger.api_gerenciamento_de_protocolo.modules.protocol_module.domain.events.pub.DomainEventPublisher;
import io.github.jvondoellinger.api_gerenciamento_de_protocolo.modules.protocol_module.domain.events.sub.DomainEventHandler;
import io.github.jvondoellinger.api_gerenciamento_de_protocolo.modules.protocol_module.domain.contracts.persistence.ProtocolRepository;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.Objects;

@Service
public class CreateProtocoloEventHandler implements DomainEventHandler<CreateProtocolEvent> {
    private final ProtocolRepository repository;
    private final DomainEventPublisher<CreatedProtocolEvent> publisher;

    public CreateProtocoloEventHandler(ProtocolRepository repository) {
        this.repository = repository;
        this.publisher = null;
    }

    @Override
    @EventListener
    public Mono<Void> handle(CreateProtocolEvent event) {
        return repository
                .save(event.getProtocolo()) // Save entity on repository
                .doOnNext(x -> {
                    if(!Objects.isNull(publisher))
                        publisher.publish(new CreatedProtocolEvent(x));
                }) // Publish an event to subscribers of CreatedProtocolEvent
                .then(); // Transform Mono<Protocolo> to Mono<Void>
    }
}
