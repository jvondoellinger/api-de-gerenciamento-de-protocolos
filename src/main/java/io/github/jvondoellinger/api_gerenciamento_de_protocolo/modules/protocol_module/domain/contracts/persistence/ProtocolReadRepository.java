package io.github.jvondoellinger.api_gerenciamento_de_protocolo.modules.protocol_module.domain.contracts.persistence;

import io.github.jvondoellinger.api_gerenciamento_de_protocolo.modules.protocol_module.domain.contracts.persistence.filters.ProtocolNumberFilter;
import io.github.jvondoellinger.api_gerenciamento_de_protocolo.modules.protocol_module.domain.valueObjects.DomainId;
import io.github.jvondoellinger.api_gerenciamento_de_protocolo.modules.protocol_module.domain.contracts.persistence.filters.PaginationFilter;
import io.github.jvondoellinger.api_gerenciamento_de_protocolo.modules.protocol_module.domain.Protocol;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface ProtocolReadRepository {
    Flux<Protocol> query(PaginationFilter filter);
    Mono<Protocol> query(DomainId id);
    Mono<Protocol> query(ProtocolNumberFilter filter);

    Mono<Boolean> exists(ProtocolNumberFilter filter);
}
