package io.github.jvondoellinger.api_gerenciamento_de_protocolo.modules.protocol_module.infrastructure.events.subs;

import io.github.jvondoellinger.api_gerenciamento_de_protocolo.modules.protocol_module.domain.contracts.persistence.InteractionsWriteRepository;
import io.github.jvondoellinger.api_gerenciamento_de_protocolo.modules.protocol_module.domain.contracts.persistence.filters.ProtocolNumberFilter;
import io.github.jvondoellinger.api_gerenciamento_de_protocolo.modules.protocol_module.domain.events.AddInteractionProtocolEvent;
import io.github.jvondoellinger.api_gerenciamento_de_protocolo.modules.protocol_module.domain.events.sub.DomainEventHandler;
import io.github.jvondoellinger.api_gerenciamento_de_protocolo.modules.protocol_module.domain.contracts.persistence.ProtocolReadRepository;
import io.github.jvondoellinger.api_gerenciamento_de_protocolo.modules.protocol_module.domain.exception.DomainException;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public class AddInteractionProtocolEventHandler implements DomainEventHandler<AddInteractionProtocolEvent> {
    private final ProtocolReadRepository readRepository;
    private final InteractionsWriteRepository interactionsWriteRepository;

    public AddInteractionProtocolEventHandler(ProtocolReadRepository protocolReadRepository, InteractionsWriteRepository interactionsWriteRepository) {
        this.readRepository = protocolReadRepository;
        this.interactionsWriteRepository = interactionsWriteRepository;
    }

    @Override
    public Mono<Void> handle(AddInteractionProtocolEvent event) {
        var interaction = event.getInteraction();
        return readRepository
                .exists(ProtocolNumberFilter.create(interaction.getProtocolNumber()))
                .flatMap(exists -> {
                    if (!exists)
                        throw new DomainException("Any protocol contains this number");
                    return interactionsWriteRepository.save(interaction); // Save on persistence layer (database)
                 })
                .then();
    }
}
