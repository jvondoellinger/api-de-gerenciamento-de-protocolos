package io.github.jvondoellinger.api_gerenciamento_de_protocolo.modules.protocol_module.infrastructure.database;

import io.github.jvondoellinger.api_gerenciamento_de_protocolo.modules.protocol_module.domain.Queue;
import io.github.jvondoellinger.api_gerenciamento_de_protocolo.modules.protocol_module.domain.contracts.persistence.QueuesReadRepository;
import io.github.jvondoellinger.api_gerenciamento_de_protocolo.modules.protocol_module.domain.contracts.persistence.filters.PaginationFilter;
import io.github.jvondoellinger.api_gerenciamento_de_protocolo.modules.protocol_module.domain.valueObjects.DomainId;
import io.github.jvondoellinger.api_gerenciamento_de_protocolo.modules.protocol_module.infrastructure.database.entity.ObjectEntity;
import io.github.jvondoellinger.api_gerenciamento_de_protocolo.modules.protocol_module.infrastructure.database.entity.QueueEntity;
import io.github.jvondoellinger.api_gerenciamento_de_protocolo.modules.protocol_module.infrastructure.database.data.QueueCassandraRepository;
import io.github.jvondoellinger.api_gerenciamento_de_protocolo.modules.protocol_module.infrastructure.database.utils.CassandraQueryUtils;
import org.springframework.data.cassandra.core.ReactiveCassandraTemplate;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class QueueReadRepositoryImpl implements QueuesReadRepository {
      private final QueueCassandraRepository cassandraRepository;
      private final ReactiveCassandraTemplate template;

      public QueueReadRepositoryImpl(QueueCassandraRepository cassandraRepository, ReactiveCassandraTemplate template) {
            this.cassandraRepository = cassandraRepository;
            this.template = template;
      }


      @Override
      public Flux<Queue> query(PaginationFilter filter) {
            var request = CassandraQueryUtils.convert(filter);
            return template
                    .select(request, QueueEntity.class)
                    .map(ObjectEntity::parse);
      }

      @Override
      public Mono<Queue> query(DomainId id) {
            return cassandraRepository
                    .findById(id.getValue())  // id.getValue returns UUID
                    .map(ObjectEntity::parse);
      }

      @Override
      public Mono<Boolean> exists(DomainId id) {
            return cassandraRepository
                    .existsById(id.getValue());
      }
}
