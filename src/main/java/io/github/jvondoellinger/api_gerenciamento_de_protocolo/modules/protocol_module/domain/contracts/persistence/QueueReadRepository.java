package io.github.jvondoellinger.api_gerenciamento_de_protocolo.modules.protocol_module.domain.contracts.persistence;

import io.github.jvondoellinger.api_gerenciamento_de_protocolo.modules.protocol_module.domain.Queue;
import io.github.jvondoellinger.api_gerenciamento_de_protocolo.modules.protocol_module.domain.contracts.persistence.filters.PaginationFilter;
import io.github.jvondoellinger.api_gerenciamento_de_protocolo.modules.protocol_module.domain.valueObjects.DomainId;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface QueueReadRepository {
      Flux<Queue> query(PaginationFilter filter);
      Mono<Queue> query(DomainId id);

      Mono<Boolean> exists(DomainId id);


}
