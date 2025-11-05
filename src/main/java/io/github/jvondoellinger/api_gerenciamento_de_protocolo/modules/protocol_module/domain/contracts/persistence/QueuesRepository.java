package io.github.jvondoellinger.api_gerenciamento_de_protocolo.modules.protocol_module.domain.contracts.persistence;

import io.github.jvondoellinger.api_gerenciamento_de_protocolo.modules.protocol_module.domain.Queue;
import reactor.core.publisher.Mono;

public interface QueuesRepository {
      Mono<Queue> save(Queue protocolo);
      Mono<Queue> update(Queue protocolo);
}
