package io.github.jvondoellinger.api_gerenciamento_de_protocolo.modules.protocol_module.infrastructure.persistence.database.profile;

import io.github.jvondoellinger.api_gerenciamento_de_protocolo.modules.protocol_module.domain.contracts.persistence.UserProfileWriteRepository;
import io.github.jvondoellinger.api_gerenciamento_de_protocolo.modules.protocol_module.domain.UserProfile;
import io.github.jvondoellinger.api_gerenciamento_de_protocolo.modules.protocol_module.infrastructure.helper.ReactiveLog;
import io.github.jvondoellinger.api_gerenciamento_de_protocolo.modules.protocol_module.infrastructure.persistence.database.data.UserProfileCassandraRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

@Repository
public class UserProfileWriteRepositoryImpl implements UserProfileWriteRepository {
	private static final Logger log = LoggerFactory.getLogger(UserProfileWriteRepositoryImpl.class);
	private final UserProfileCassandraRepository repository;

	public UserProfileWriteRepositoryImpl(UserProfileCassandraRepository repository) {
		this.repository = repository;
	}

	@Override
	public Mono<Void> save(UserProfile profile) {
		var entity = UserProfileEntity.create(profile);

		return repository
			   .save(entity)
			   .then()
			   .doOnEach(ReactiveLog.onNext(log, "action=save_profile profile_id={} user_id={} status=completed", entity.getId(), entity.getUserId()))
			   .doOnEach(ReactiveLog.onError(log, "action=save_profile profile_id={} user_id={} status=error", entity.getId(), entity.getUserId()));
	}

	@Override
	public Mono<Void> remove(UserProfile profile) {
		var profileId = profile.getId().toString();
		var userId = profile.getUserId().toString();

		return repository
			   .deleteById(profileId)
			   .doOnEach(ReactiveLog.onNext(log, "action=save_profile profile_id={} status=completed", profileId, userId))
			   .doOnEach(ReactiveLog.onError(log, "action=save_profile profile_id={} status=error", profileId, userId));
	}
}
