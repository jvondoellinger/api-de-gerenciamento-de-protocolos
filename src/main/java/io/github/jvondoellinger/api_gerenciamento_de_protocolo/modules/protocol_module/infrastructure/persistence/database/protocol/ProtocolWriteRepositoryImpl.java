package io.github.jvondoellinger.api_gerenciamento_de_protocolo.modules.protocol_module.infrastructure.persistence.database.protocol;

import io.github.jvondoellinger.api_gerenciamento_de_protocolo.modules.protocol_module.domain.Protocol;
import io.github.jvondoellinger.api_gerenciamento_de_protocolo.modules.protocol_module.infrastructure.helper.ReactiveLog;
import io.github.jvondoellinger.api_gerenciamento_de_protocolo.modules.protocol_module.infrastructure.persistence.database.ObjectEntity;
import io.github.jvondoellinger.api_gerenciamento_de_protocolo.modules.protocol_module.infrastructure.persistence.database.data.ProtocolCassandraRepository;
import io.github.jvondoellinger.api_gerenciamento_de_protocolo.modules.protocol_module.domain.contracts.persistence.ProtocolWriteRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

@Repository
public class ProtocolWriteRepositoryImpl implements ProtocolWriteRepository {
	private static final Logger log = LoggerFactory.getLogger(ProtocolWriteRepositoryImpl.class);
	private final ProtocolCassandraRepository repository;

	public ProtocolWriteRepositoryImpl(ProtocolCassandraRepository repository) {
		this.repository = repository;
	}

	@Override
	public Mono<Protocol> save(Protocol protocol) {
		var entity = ProtocolEntity.create(protocol);
		var protocolNumber = entity.getProtocolNumber();
		var user = entity.getCreatedByUserId();

		return repository
			   .save(entity)
			   .map(ObjectEntity::parse)
			   .doOnEach(ReactiveLog.onNext(log, "action=create_protocol protocol={} userId={} status=completed", protocolNumber, user))
			   .doOnEach(ReactiveLog.onError(log, "action=create_protocol protocol={} userId={} status=error", protocol, user));
	}

	@Override
	public Mono<Protocol> update(Protocol protocol) {
		var entity = ProtocolEntity.create(protocol);
		var protocolNumber = entity.getProtocolNumber();
		var user = entity.getCreatedByUserId();

		return repository
			   .save(entity)
			   .map(ObjectEntity::parse)
			   .doOnEach(ReactiveLog.onNext(log, "action=update_protocol protocol={} userId={} status=completed", protocolNumber, user))
			   .doOnEach(ReactiveLog.onError(log, "action=update_protocol protocol={} userId={} status=error", protocol, user));
	}
}
