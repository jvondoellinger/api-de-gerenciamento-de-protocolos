package io.github.jvondoellinger.api_gerenciamento_de_protocolo.modules.protocol_module.infrastructure.database.interactions;

import io.github.jvondoellinger.api_gerenciamento_de_protocolo.modules.protocol_module.infrastructure.database.ObjectEntity;
import io.github.jvondoellinger.api_gerenciamento_de_protocolo.modules.protocol_module.infrastructure.database.data.InteractionCassandraRepository;
import io.github.jvondoellinger.api_gerenciamento_de_protocolo.modules.protocol_module.infrastructure.database.utils.CassandraQueryUtils;
import io.github.jvondoellinger.api_gerenciamento_de_protocolo.modules.protocol_module.domain.Interaction;
import io.github.jvondoellinger.api_gerenciamento_de_protocolo.modules.protocol_module.domain.contracts.persistence.InteractionsReadRepository;
import io.github.jvondoellinger.api_gerenciamento_de_protocolo.modules.protocol_module.domain.contracts.persistence.filters.PaginationFilter;
import io.github.jvondoellinger.api_gerenciamento_de_protocolo.modules.protocol_module.domain.contracts.persistence.filters.ProtocolNumberFilter;
import io.github.jvondoellinger.api_gerenciamento_de_protocolo.modules.protocol_module.domain.valueObjects.DomainId;
import org.springframework.data.cassandra.core.ReactiveCassandraTemplate;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Repository
public class InteractionsReadRepositoryImpl implements InteractionsReadRepository {
    private final InteractionCassandraRepository repository;
    private final ReactiveCassandraTemplate template;

    public InteractionsReadRepositoryImpl(InteractionCassandraRepository repository, ReactiveCassandraTemplate template) {
        this.repository = repository;
        this.template = template;
    }

    @Override
    public Flux<Interaction> query(PaginationFilter filter) {
        var request = CassandraQueryUtils.convert(filter);
        return template
                .select(request, InteractionEntity.class)
                .map(ObjectEntity::parse);
    }

    @Override
    public Flux<Interaction> query(ProtocolNumberFilter filter) {
        return repository
                .findByProtocolNumber(filter.getProtocolNumber().getValue())
                .map(ObjectEntity::parse);
    }

    @Override
    public Mono<Boolean> exists(ProtocolNumberFilter filter) {
        return repository
                .findByProtocolNumber(filter.getProtocolNumber().getValue())
                .hasElements();
    }

    @Override
    public Mono<Interaction> query(DomainId id) {
        return repository
                .findById(id.getValue())
                .map(ObjectEntity::parse);
    }
}
