package io.github.jvondoellinger.api_gerenciamento_de_protocolo.modules.protocol_module.infrastructure.persistence.database.data;

import io.github.jvondoellinger.api_gerenciamento_de_protocolo.modules.protocol_module.infrastructure.persistence.database.protocol.InteractionColumn;
import org.springframework.data.cassandra.repository.ReactiveCassandraRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface InteractionCassandraRepository extends ReactiveCassandraRepository<InteractionColumn, UUID> {
    //Flux<InteractionColumn> findByProtocolNumber(String protocolNumber);
}
