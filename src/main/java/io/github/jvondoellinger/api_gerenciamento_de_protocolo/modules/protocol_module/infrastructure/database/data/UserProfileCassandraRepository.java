package io.github.jvondoellinger.api_gerenciamento_de_protocolo.modules.protocol_module.infrastructure.database.data;

import io.github.jvondoellinger.api_gerenciamento_de_protocolo.modules.protocol_module.infrastructure.database.profile.UserProfileEntity;
import org.springframework.data.cassandra.repository.ReactiveCassandraRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

@Repository
public interface UserProfileCassandraRepository extends ReactiveCassandraRepository<UserProfileEntity, String> {
	Flux<UserProfileEntity> findByUserId(String userId);
}
