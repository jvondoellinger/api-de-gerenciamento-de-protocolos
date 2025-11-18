package io.github.jvondoellinger.api_gerenciamento_de_protocolo.modules.protocol_module.infrastructure.database.data;

import io.github.jvondoellinger.api_gerenciamento_de_protocolo.modules.protocol_module.infrastructure.database.entity.UserProtocolPermissionRelationshipEntity;
import org.springframework.data.cassandra.repository.ReactiveCassandraRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

import java.util.UUID;

@Repository
public interface UserProtocolRelationshipCassandraRepository extends ReactiveCassandraRepository<UserProtocolPermissionRelationshipEntity, UUID> {
      Flux<UserProtocolPermissionRelationshipEntity> findByUserId(String userId);
}
