package io.github.jvondoellinger.api_gerenciamento_de_protocolo.modules.protocol_module.infrastructure.events.subs;

import io.github.jvondoellinger.api_gerenciamento_de_protocolo.modules.protocol_module.domain.contracts.persistence.InteractionsWriteRepository;
import io.github.jvondoellinger.api_gerenciamento_de_protocolo.modules.protocol_module.domain.events.AddInteractionProtocolEvent;
import io.github.jvondoellinger.api_gerenciamento_de_protocolo.modules.protocol_module.domain.events.sub.DomainEventHandler;
import io.github.jvondoellinger.api_gerenciamento_de_protocolo.modules.protocol_module.infrastructure.events.validators.proxy.EventValidatorProxy;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public class AddInteractionProtocolEventHandler implements DomainEventHandler<AddInteractionProtocolEvent> {
    private final InteractionsWriteRepository interactionsWriteRepository;

    public AddInteractionProtocolEventHandler(InteractionsWriteRepository interactionsWriteRepository) {
        this.interactionsWriteRepository = interactionsWriteRepository;
    }

    @Override
    @EventValidatorProxy
    public Mono<Void> handle(AddInteractionProtocolEvent event) {
        var interaction = event.getInteraction();

        return interactionsWriteRepository
                .save(interaction)
                .then();
    }
}
