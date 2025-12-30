package io.github.jvondoellinger.api_gerenciamento_de_protocolo.modules.protocol_module.infrastructure.persistence.database.profile;

import io.github.jvondoellinger.api_gerenciamento_de_protocolo.modules.protocol_module.domain.contracts.persistence.UserProfileReadRepository;
import io.github.jvondoellinger.api_gerenciamento_de_protocolo.modules.protocol_module.domain.valueObjects.DomainId;
import io.github.jvondoellinger.api_gerenciamento_de_protocolo.modules.protocol_module.domain.UserProfile;
import io.github.jvondoellinger.api_gerenciamento_de_protocolo.modules.protocol_module.infrastructure.helper.ReactiveLog;
import io.github.jvondoellinger.api_gerenciamento_de_protocolo.modules.protocol_module.infrastructure.persistence.database.data.UserProfileCassandraRepository;
import io.github.jvondoellinger.api_gerenciamento_de_protocolo.modules.protocol_module.infrastructure.persistence.database.ObjectEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

@Repository
public class UserUserProfileReadRepositoryImpl implements UserProfileReadRepository {
	private static final Logger log = LoggerFactory.getLogger(UserUserProfileReadRepositoryImpl.class);
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
			   .map(ObjectEntity::parse)
			   .doOnEach(ReactiveLog.onNext(log, "action=query_profile id={} status=completed", strId))
			   .doOnEach(ReactiveLog.onError(log, "action=query_profile id={} status=error", strId));
	}

	@Override
	public Mono<UserProfile> queryByUserId(DomainId userId) {
		// Getting ID in String value
		var strId = userId.getValue().toString();

		// Querying...
		return repository
			   .findByUserId(strId)
			   .map(ObjectEntity::parse)
			   .singleOrEmpty()
			   .doOnEach(ReactiveLog.onNext(log, "action=query_profile id={} status=completed", strId))
			   .doOnEach(ReactiveLog.onError(log, "action=query_profile id={} status=error", strId));
	}
}
