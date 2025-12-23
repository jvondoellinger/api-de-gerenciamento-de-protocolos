package io.github.jvondoellinger.api_gerenciamento_de_protocolo.modules.protocol_module.infrastructure.persistence.database.profile;

import io.github.jvondoellinger.api_gerenciamento_de_protocolo.modules.protocol_module.domain.contracts.persistence.UserProfileWriteRepository;
import io.github.jvondoellinger.api_gerenciamento_de_protocolo.modules.protocol_module.domain.UserProfile;
import io.github.jvondoellinger.api_gerenciamento_de_protocolo.modules.protocol_module.infrastructure.persistence.database.data.UserProfileCassandraRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

@Repository
public class UserProfileWriteRepositoryImpl implements UserProfileWriteRepository {
	private final UserProfileCassandraRepository repository;

	public UserProfileWriteRepositoryImpl(UserProfileCassandraRepository repository) {
		this.repository = repository;
	}

	@Override
	public Mono<Void> save(UserProfile profile) {
		var entity = UserProfileEntity.create(profile);
		return repository
			   .save(entity)
			   .then();
	}

	@Override
	public Mono<Void> remove(UserProfile profile) {
		var idStr = profile.getId().toString();
		return repository.deleteById(idStr);
	}
}
