package io.github.jvondoellinger.api_gerenciamento_de_protocolo.modules.protocol_module.infrastructure.database.springboot;

import io.github.jvondoellinger.api_gerenciamento_de_protocolo.modules.protocol_module.infrastructure.database.serialize.InteractionSerializable;
import org.springframework.data.cassandra.repository.ReactiveCassandraRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

import java.util.UUID;

@Repository
public interface InteractionCassandraRepository extends ReactiveCassandraRepository<InteractionSerializable, UUID> {
    Flux<InteractionSerializable> findByProtocolNumber(String protocolNumber);
}
