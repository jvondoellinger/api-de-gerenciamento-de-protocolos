package io.github.jvondoellinger.api_gerenciamento_de_protocolo.modules.protocol_module.infrastructure.database.data;

import io.github.jvondoellinger.api_gerenciamento_de_protocolo.modules.protocol_module.infrastructure.database.entity.ProtocoloEntity;
import org.springframework.data.cassandra.repository.ReactiveCassandraRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

import java.util.UUID;

@Repository
public interface ProtocolCassandraRepository extends ReactiveCassandraRepository<ProtocoloEntity, UUID> {
    Mono<ProtocoloEntity> findByProtocolNumber(String protocolNumber);
    Mono<Boolean> existsByProtocolNumber(String protocolNumber);
}
