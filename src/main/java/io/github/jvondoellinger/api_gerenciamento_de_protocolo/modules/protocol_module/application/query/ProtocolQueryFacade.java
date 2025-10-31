package io.github.jvondoellinger.api_gerenciamento_de_protocolo.modules.protocol_module.application.query;

import io.github.jvondoellinger.api_gerenciamento_de_protocolo.modules.protocol_module.domain.Protocolo;
import io.github.jvondoellinger.api_gerenciamento_de_protocolo.modules.protocol_module.domain.contracts.persistence.InteractionsReadRepository;
import io.github.jvondoellinger.api_gerenciamento_de_protocolo.modules.protocol_module.domain.contracts.persistence.ProtocolReadRepository;
import io.github.jvondoellinger.api_gerenciamento_de_protocolo.modules.protocol_module.domain.contracts.persistence.filters.PaginationFilter;
import io.github.jvondoellinger.api_gerenciamento_de_protocolo.modules.protocol_module.domain.contracts.persistence.filters.ProtocolNumberFilter;
import io.github.jvondoellinger.api_gerenciamento_de_protocolo.modules.protocol_module.domain.valueObjects.ProtocolDomainId;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class ProtocolQueryFacade {
    private final ProtocolReadRepository protocolReadRepository;
    private final InteractionsReadRepository interactionsReadRepository;

    public ProtocolQueryFacade(ProtocolReadRepository protocolReadRepository, InteractionsReadRepository interactionsReadRepository) {
        this.protocolReadRepository = protocolReadRepository;
        this.interactionsReadRepository = interactionsReadRepository;
    }


    public Flux<Protocolo> query(PaginationFilter filter) {
        return protocolReadRepository
                .query(filter)
                .map(Mono::just)
                .flatMap(this::withInteractions);
    }

    public Mono<Protocolo> query(ProtocolDomainId id) {
        return protocolReadRepository
                .query(id)
                .transform(this::withInteractions);
    }

    public Mono<Protocolo> query(ProtocolNumberFilter filter) {
        return protocolReadRepository
                .query(filter)
                .transform(this::withInteractions);
    }

    protected Mono<Protocolo> withInteractions(Mono<Protocolo> protocolo) {
        return protocolo
                .flatMap(protocol ->
                    interactionsReadRepository
                        .query(new ProtocolNumberFilter(protocol.getProtocolNumber()))
                        .doOnNext(protocol::interact)
                        .then(Mono.just(protocol))
        );
    }
    protected Flux<Protocolo> withInteractions(Flux<Protocolo> protocolo) {
        return protocolo
                .flatMap(protocol ->
                        interactionsReadRepository
                                .query(new ProtocolNumberFilter(protocol.getProtocolNumber()))
                                .doOnNext(protocol::interact)
                                .then(Mono.just(protocol))
                );
    }
}
