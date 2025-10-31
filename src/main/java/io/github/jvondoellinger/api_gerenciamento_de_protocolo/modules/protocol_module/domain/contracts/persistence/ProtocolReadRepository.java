package io.github.jvondoellinger.api_gerenciamento_de_protocolo.modules.protocol_module.domain.contracts.persistence;

import io.github.jvondoellinger.api_gerenciamento_de_protocolo.modules.protocol_module.domain.Interaction;
import io.github.jvondoellinger.api_gerenciamento_de_protocolo.modules.protocol_module.domain.contracts.persistence.filters.ProtocolNumberFilter;
import io.github.jvondoellinger.api_gerenciamento_de_protocolo.modules.protocol_module.domain.valueObjects.ProtocolDomainId;
import io.github.jvondoellinger.api_gerenciamento_de_protocolo.modules.protocol_module.domain.contracts.persistence.filters.PaginationFilter;
import io.github.jvondoellinger.api_gerenciamento_de_protocolo.modules.protocol_module.domain.Protocolo;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface ProtocolReadRepository {
    Flux<Protocolo> query(PaginationFilter filter);
    Mono<Protocolo> query(ProtocolDomainId id);
    Mono<Protocolo> query(ProtocolNumberFilter filter);

    Mono<Boolean> exists(ProtocolNumberFilter filter);
}
