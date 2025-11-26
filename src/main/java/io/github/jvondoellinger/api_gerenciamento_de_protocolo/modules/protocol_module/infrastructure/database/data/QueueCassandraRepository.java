package io.github.jvondoellinger.api_gerenciamento_de_protocolo.modules.protocol_module.infrastructure.database.data;

import io.github.jvondoellinger.api_gerenciamento_de_protocolo.modules.protocol_module.infrastructure.database.queue.QueueEntity;
import org.springframework.data.cassandra.repository.ReactiveCassandraRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface QueueCassandraRepository extends ReactiveCassandraRepository<QueueEntity, UUID> {
}
