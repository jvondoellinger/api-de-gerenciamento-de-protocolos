package io.github.jvondoellinger.api_gerenciamento_de_protocolo.modules.protocol_module.infrastructure.database.profile;

import io.github.jvondoellinger.api_gerenciamento_de_protocolo.modules.protocol_module.domain.contracts.persistence.UserProfileReadRepository;
import io.github.jvondoellinger.api_gerenciamento_de_protocolo.modules.protocol_module.domain.valueObjects.DomainId;
import io.github.jvondoellinger.api_gerenciamento_de_protocolo.modules.protocol_module.domain.UserProfile;
import io.github.jvondoellinger.api_gerenciamento_de_protocolo.modules.protocol_module.infrastructure.database.data.UserProfileCassandraRepository;
import io.github.jvondoellinger.api_gerenciamento_de_protocolo.modules.protocol_module.infrastructure.database.ObjectEntity;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

@Repository
public class UserUserProfileReadRepositoryImpl implements UserProfileReadRepository {
	private final UserProfileCassandraRepository repository;

	public UserUserProfileReadRepositoryImpl(UserProfileCassandraRepository repository) {
		this.repository = repository;
	}

	@Override
	public Mono<UserProfile> query(DomainId id) {
		// Getting ID in String value
		var strId = id.getValue().toString();

		// Querying...
		return repository
			   .findById(strId)
			   .map(ObjectEntity::parse);
	}

	@Override
	public Mono<UserProfile> queryByUserId(DomainId userId) {
		// Getting ID in String value
		var strId = userId.getValue().toString();

		// Querying...
		return repository
			   .findByUserId(strId)
			   .map(ObjectEntity::parse);
	}
}
