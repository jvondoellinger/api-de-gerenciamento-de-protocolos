package io.github.jvondoellinger.api_gerenciamento_de_protocolo.modules.protocol_module.application.query;

import io.github.jvondoellinger.api_gerenciamento_de_protocolo.modules.protocol_module.domain.Interaction;
import io.github.jvondoellinger.api_gerenciamento_de_protocolo.modules.protocol_module.domain.InteractionHistory;
import io.github.jvondoellinger.api_gerenciamento_de_protocolo.modules.protocol_module.domain.Protocolo;
import io.github.jvondoellinger.api_gerenciamento_de_protocolo.modules.protocol_module.domain.Queue;
import io.github.jvondoellinger.api_gerenciamento_de_protocolo.modules.protocol_module.domain.contracts.persistence.InteractionsReadRepository;
import io.github.jvondoellinger.api_gerenciamento_de_protocolo.modules.protocol_module.domain.contracts.persistence.ProtocolReadRepository;
import io.github.jvondoellinger.api_gerenciamento_de_protocolo.modules.protocol_module.domain.contracts.persistence.QueuesReadRepository;
import io.github.jvondoellinger.api_gerenciamento_de_protocolo.modules.protocol_module.domain.contracts.persistence.filters.PaginationFilter;
import io.github.jvondoellinger.api_gerenciamento_de_protocolo.modules.protocol_module.domain.contracts.persistence.filters.ProtocolNumberFilter;
import io.github.jvondoellinger.api_gerenciamento_de_protocolo.modules.protocol_module.domain.valueObjects.DomainId;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

@Service
public class ProtocolQueryFacade {
    private final ProtocolReadRepository protocolReadRepository;
    private final InteractionsReadRepository interactionsReadRepository;
    private final QueuesReadRepository queuesReadRepository;

    public ProtocolQueryFacade(ProtocolReadRepository protocolReadRepository, InteractionsReadRepository interactionsReadRepository, QueuesReadRepository queuesReadRepository) {
        this.protocolReadRepository = protocolReadRepository;
        this.interactionsReadRepository = interactionsReadRepository;
        this.queuesReadRepository = queuesReadRepository;
    }


    public Flux<Protocolo> query(PaginationFilter filter) {
        return protocolReadRepository
                .query(filter)
                .flatMap(this::mountCompleteProtocol);
    }

    public Mono<Protocolo> query(DomainId id) {
        return protocolReadRepository
                .query(id)
                .flatMap(this::mountCompleteProtocol);
    }

    public Mono<Protocolo> query(ProtocolNumberFilter filter) {
        return protocolReadRepository
                .query(filter)
                .flatMap(this::mountCompleteProtocol);
    }

    private Mono<Protocolo> mountCompleteProtocol(Protocolo incomplete) {
        var protocolNumberFilter = new ProtocolNumberFilter(incomplete.getProtocolNumber());

        // Monos
        var interactionsMono = interactionsReadRepository.query(protocolNumberFilter).collectList();
        var queueMono = queuesReadRepository.query(incomplete.getQueue().getId());

        // Mounting
        return Mono
                .zip(interactionsMono, queueMono)
                .map(x -> mounter(incomplete, x.getT2(), x.getT1()));
    }

    private Protocolo mounter(Protocolo incomplete, Queue queue, List<Interaction> interactions) {
        return new Protocolo(
                incomplete.getId(),
                incomplete.getProtocolNumber(),
                queue,
                incomplete.getDescription(),
                incomplete.getCreatedBy(),
                incomplete.getState(),
                InteractionHistory.from(interactions),
                incomplete.getAttachments(),
                incomplete.getCreatedAt(),
                incomplete.getUpdatedAt()
        );
    }
}
