package io.github.jvondoellinger.api_gerenciamento_de_protocolo.modules.protocol_module.domain.contracts.persistence;

import io.github.jvondoellinger.api_gerenciamento_de_protocolo.modules.protocol_module.domain.Queue;
import reactor.core.publisher.Mono;

public interface QueuesWriteRepository {
      Mono<Queue> save(Queue queue);
      Mono<Queue> update(Queue queue);
}
