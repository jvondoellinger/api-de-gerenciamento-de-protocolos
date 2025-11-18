package io.github.jvondoellinger.api_gerenciamento_de_protocolo.modules.protocol_module.infrastructure.database;

import io.github.jvondoellinger.api_gerenciamento_de_protocolo.modules.protocol_module.domain.Queue;
import io.github.jvondoellinger.api_gerenciamento_de_protocolo.modules.protocol_module.domain.contracts.persistence.QueuesRepository;
import io.github.jvondoellinger.api_gerenciamento_de_protocolo.modules.protocol_module.infrastructure.database.entity.ObjectEntity;
import io.github.jvondoellinger.api_gerenciamento_de_protocolo.modules.protocol_module.infrastructure.database.entity.QueueEntity;
import io.github.jvondoellinger.api_gerenciamento_de_protocolo.modules.protocol_module.infrastructure.database.data.QueueCassandraRepository;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public class QueueRepositoryImpl implements QueuesRepository {
      private final QueueCassandraRepository cassandraRepository;

      public QueueRepositoryImpl(QueueCassandraRepository cassandraRepository) {
            this.cassandraRepository = cassandraRepository;
      }

      @Override
      public Mono<Queue> save(Queue protocolo) {
            return cassandraRepository
                    .save(QueueEntity.create(protocolo))
                    .map(ObjectEntity::parse);
      }

      @Override
      public Mono<Queue> update(Queue protocolo) {
            return cassandraRepository
                    .save(QueueEntity.create(protocolo))
                    .map(ObjectEntity::parse);
      }
}
