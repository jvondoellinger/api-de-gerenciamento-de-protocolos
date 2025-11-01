package io.github.jvondoellinger.api_gerenciamento_de_protocolo.modules.protocol_module.domain.contracts.persistence;

import io.github.jvondoellinger.api_gerenciamento_de_protocolo.modules.protocol_module.domain.Interaction;
import io.github.jvondoellinger.api_gerenciamento_de_protocolo.modules.protocol_module.domain.contracts.persistence.filters.PaginationFilter;
import io.github.jvondoellinger.api_gerenciamento_de_protocolo.modules.protocol_module.domain.contracts.persistence.filters.ProtocolNumberFilter;
import io.github.jvondoellinger.api_gerenciamento_de_protocolo.modules.protocol_module.domain.valueObjects.DomainId;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface InteractionsReadRepository {
    Flux<Interaction> query(PaginationFilter filter);
    Flux<Interaction> query(ProtocolNumberFilter filter);

    Mono<Boolean> exists(ProtocolNumberFilter filter);
    Mono<Interaction> query(DomainId id);
}
