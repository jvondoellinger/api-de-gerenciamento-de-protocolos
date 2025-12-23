package io.github.jvondoellinger.api_gerenciamento_de_protocolo.modules.protocol_module.infrastructure.persistence.database.protocol;

import io.github.jvondoellinger.api_gerenciamento_de_protocolo.modules.protocol_module.domain.Protocol;
import io.github.jvondoellinger.api_gerenciamento_de_protocolo.modules.protocol_module.infrastructure.persistence.database.ObjectEntity;
import io.github.jvondoellinger.api_gerenciamento_de_protocolo.modules.protocol_module.infrastructure.persistence.database.data.ProtocolCassandraRepository;
import io.github.jvondoellinger.api_gerenciamento_de_protocolo.modules.protocol_module.infrastructure.persistence.database.utils.CassandraQueryUtils;
import io.github.jvondoellinger.api_gerenciamento_de_protocolo.modules.protocol_module.domain.contracts.persistence.filters.ProtocolNumberFilter;
import io.github.jvondoellinger.api_gerenciamento_de_protocolo.modules.protocol_module.domain.valueObjects.DomainId;
import io.github.jvondoellinger.api_gerenciamento_de_protocolo.modules.protocol_module.domain.contracts.persistence.ProtocolReadRepository;
import io.github.jvondoellinger.api_gerenciamento_de_protocolo.modules.protocol_module.domain.contracts.persistence.filters.PaginationFilter;
import org.springframework.data.cassandra.core.ReactiveCassandraTemplate;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Objects;

@Repository
public class ProtocolReadRepositoryImpl implements ProtocolReadRepository {

    private final ProtocolCassandraRepository repository;
    private final ReactiveCassandraTemplate template;

    public ProtocolReadRepositoryImpl(ProtocolCassandraRepository repository, ReactiveCassandraTemplate template) {
        this.repository = repository;
        this.template = template;
    }

    @Override
    public Flux<Protocol> query(PaginationFilter filter) {
        var request = CassandraQueryUtils.convert(filter);
        return template
                .select(request, ProtocolEntity.class)
                .map(ObjectEntity::parse);
    }

    @Override
    public Mono<Protocol> query(DomainId id) {
        return repository
                .findById(id.getValue())
                .map(ObjectEntity::parse);
    }

    @Override
    public Mono<Protocol> query(ProtocolNumberFilter filter) {
        return repository
                .findByProtocolNumber(filter.getProtocolNumber().getValue())
                .map(ObjectEntity::parse);
    }

    @Override
    public Mono<Boolean> exists(ProtocolNumberFilter filter) {
        return repository
                .findByProtocolNumber(filter.getProtocolNumber().getValue())
                .map(Objects::isNull) // Se for null, o metodo abaixo diz: false para o existes, senão, o inverso.
                .map(x -> !x); // Reversing boolean
    }
}
