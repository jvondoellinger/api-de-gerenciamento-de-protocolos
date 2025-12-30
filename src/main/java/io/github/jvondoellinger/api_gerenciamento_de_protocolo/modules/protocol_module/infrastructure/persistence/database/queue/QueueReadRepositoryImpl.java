package io.github.jvondoellinger.api_gerenciamento_de_protocolo.modules.protocol_module.infrastructure.persistence.database.queue;

import io.github.jvondoellinger.api_gerenciamento_de_protocolo.modules.protocol_module.domain.Queue;
import io.github.jvondoellinger.api_gerenciamento_de_protocolo.modules.protocol_module.domain.contracts.persistence.QueueReadRepository;
import io.github.jvondoellinger.api_gerenciamento_de_protocolo.modules.protocol_module.domain.contracts.persistence.filters.PaginationFilter;
import io.github.jvondoellinger.api_gerenciamento_de_protocolo.modules.protocol_module.domain.valueObjects.DomainId;
import io.github.jvondoellinger.api_gerenciamento_de_protocolo.modules.protocol_module.infrastructure.helper.ReactiveLog;
import io.github.jvondoellinger.api_gerenciamento_de_protocolo.modules.protocol_module.infrastructure.persistence.database.ObjectEntity;
import io.github.jvondoellinger.api_gerenciamento_de_protocolo.modules.protocol_module.infrastructure.persistence.database.data.QueueCassandraRepository;
import io.github.jvondoellinger.api_gerenciamento_de_protocolo.modules.protocol_module.infrastructure.persistence.database.utils.CassandraQueryUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.cassandra.core.ReactiveCassandraTemplate;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Repository
public class QueueReadRepositoryImpl implements QueueReadRepository {
	private static final Logger log = LoggerFactory.getLogger(QueueReadRepositoryImpl.class);
	private final QueueCassandraRepository cassandraRepository;
	private final ReactiveCassandraTemplate template;

	public QueueReadRepositoryImpl(QueueCassandraRepository cassandraRepository, ReactiveCassandraTemplate template) {
		this.cassandraRepository = cassandraRepository;
		this.template = template;
	}


	@Override
	public Flux<Queue> query(PaginationFilter filter) {
		var request = CassandraQueryUtils.convert(filter);
		var offset = filter.getOffset();
		var limit = filter.getLimit();

		return template
			   .select(request, QueueEntity.class)
			   .map(ObjectEntity::parse)
			   .doOnEach(ReactiveLog.onNext(log, "action=query_queue offset={} limit={} status=completed", offset, limit))
			   .doOnEach(ReactiveLog.onError(log, "action=query_queue offset={} limit={} status=error", offset, limit));
	}

	@Override
	public Mono<Queue> query(DomainId filter) {
		var id = filter.getValue();

		return cassandraRepository
			   .findById(id)
			   .map(ObjectEntity::parse)
			   .doOnEach(ReactiveLog.onNext(log, "action=query_queue queueId={} status=completed", id))
			   .doOnEach(ReactiveLog.onError(log, "action=query_queue queueId={} status=error", id));
	}

	@Override
	public Mono<Boolean> exists(DomainId id) {
		var idValue = id.getValue();

		return cassandraRepository
			   .existsById(idValue)
			   .doOnEach(ReactiveLog.onNext(log, "action=exists_queue queueId={} status=completed", idValue))
			   .doOnEach(ReactiveLog.onError(log, "action=exists_queue queueId={} status=error", idValue));
	}
}
