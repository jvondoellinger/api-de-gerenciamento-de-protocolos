package io.github.jvondoellinger.api_gerenciamento_de_protocolo.modules.protocol_module.infrastructure.database.queue;

import io.github.jvondoellinger.api_gerenciamento_de_protocolo.modules.protocol_module.domain.Queue;
import io.github.jvondoellinger.api_gerenciamento_de_protocolo.modules.protocol_module.domain.contracts.persistence.QueuesWriteRepository;
import io.github.jvondoellinger.api_gerenciamento_de_protocolo.modules.protocol_module.infrastructure.database.ObjectEntity;
import io.github.jvondoellinger.api_gerenciamento_de_protocolo.modules.protocol_module.infrastructure.database.data.QueueCassandraRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

@Repository
public class QueueWriteRepositoryImpl implements QueuesWriteRepository {
      private final QueueCassandraRepository cassandraRepository;

      public QueueWriteRepositoryImpl(QueueCassandraRepository cassandraRepository) {
            this.cassandraRepository = cassandraRepository;
      }

      @Override
      public Mono<Queue> save(Queue queue) {
            return cassandraRepository
                    .save(QueueEntity.create(queue))
                    .map(ObjectEntity::parse);
      }

      @Override
      public Mono<Queue> update(Queue queue) {
            return cassandraRepository
                    .save(QueueEntity.create(queue))
                    .map(ObjectEntity::parse);
      }
}
