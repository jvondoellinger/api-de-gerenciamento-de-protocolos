package io.github.jvondoellinger.api_gerenciamento_de_protocolo.modules.protocol_module.infrastructure.persistence.database.queue;

import io.github.jvondoellinger.api_gerenciamento_de_protocolo.modules.protocol_module.domain.Queue;
import io.github.jvondoellinger.api_gerenciamento_de_protocolo.modules.protocol_module.domain.contracts.persistence.QueuesWriteRepository;
import io.github.jvondoellinger.api_gerenciamento_de_protocolo.modules.protocol_module.infrastructure.helper.ReactiveLog;
import io.github.jvondoellinger.api_gerenciamento_de_protocolo.modules.protocol_module.infrastructure.persistence.database.ObjectEntity;
import io.github.jvondoellinger.api_gerenciamento_de_protocolo.modules.protocol_module.infrastructure.persistence.database.data.QueueCassandraRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

@Repository
public class QueueWriteRepositoryImpl implements QueuesWriteRepository {
	private static final Logger log = LoggerFactory.getLogger(QueueWriteRepositoryImpl.class);
	private final QueueCassandraRepository cassandraRepository;

	public QueueWriteRepositoryImpl(QueueCassandraRepository cassandraRepository) {
		this.cassandraRepository = cassandraRepository;
	}

	@Override
	public Mono<Queue> save(Queue queue) {
		var entity = QueueEntity.create(queue);
		var queueId = entity.getId();
		var createdByUserId = entity.getCreatedById();

		return cassandraRepository
			   .save(QueueEntity.create(queue))
			   .map(ObjectEntity::parse)
			   .doOnEach(ReactiveLog.onNext(log, "action=create_queue createdByUserId={} queueId={} status=completed", queueId, createdByUserId))
			   .doOnEach(ReactiveLog.onError(log, "action=create_queue createdByUserId={} queueId={} status=error", queueId, createdByUserId));
	}

	@Override
	public Mono<Queue> update(Queue queue) {
		var entity = QueueEntity.create(queue);
		var queueId = entity.getId();

		return cassandraRepository
			   .save(entity)
			   .map(ObjectEntity::parse)
			   .doOnEach(ReactiveLog.onNext(log, "action=update_queue queueId={} status=completed", queueId))
			   .doOnEach(ReactiveLog.onError(log, "action=update_queue queueId={} status=error", queueId));
	}
}
