package io.github.jvondoellinger.api_gerenciamento_de_protocolo.modules.protocol_module.Infrastructure.subs;

import io.github.jvondoellinger.api_gerenciamento_de_protocolo.modules.protocol_module.domain.contracts.persistence.InteractionsRepository;
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
    private final InteractionsRepository interactionsRepository;

    public AddInteractionProtocolEventHandler(ProtocolReadRepository protocolReadRepository, InteractionsRepository interactionsRepository) {
        this.readRepository = protocolReadRepository;
        this.interactionsRepository = interactionsRepository;
    }

    @Override
    public Mono<Void> handle(AddInteractionProtocolEvent event) {
        var interaction = event.getInteraction();
        return readRepository
                .exists(new ProtocolNumberFilter(interaction.getProtocolNumber()))
                .flatMap(exists -> {
                    if (!exists)
                        throw new DomainException("Any protocol contains this number");
                    return interactionsRepository.save(interaction); // Save on persistence layer (database)
                 })
                .then();
    }
}
