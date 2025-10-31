package io.github.jvondoellinger.api_gerenciamento_de_protocolo.modules.protocol_module.infrastructure.database.springboot;

import io.github.jvondoellinger.api_gerenciamento_de_protocolo.modules.protocol_module.infrastructure.database.serialize.ProtocoloSerializable;
import org.springframework.data.cassandra.repository.ReactiveCassandraRepository;
import reactor.core.publisher.Mono;

import java.util.UUID;

public interface ProtocolCassandraRepository extends ReactiveCassandraRepository<ProtocoloSerializable, UUID> {
    Mono<ProtocoloSerializable> findByProtocolNumber(String protocolNumber);
    Mono<Boolean> existsByProtocolNumber(String protocolNumber);
}
