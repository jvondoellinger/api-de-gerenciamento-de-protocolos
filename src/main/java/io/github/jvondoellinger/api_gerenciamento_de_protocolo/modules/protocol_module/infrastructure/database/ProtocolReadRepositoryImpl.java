package io.github.jvondoellinger.api_gerenciamento_de_protocolo.modules.protocol_module.infrastructure.database;

import io.github.jvondoellinger.api_gerenciamento_de_protocolo.modules.protocol_module.infrastructure.database.entity.ObjectEntity;
import io.github.jvondoellinger.api_gerenciamento_de_protocolo.modules.protocol_module.infrastructure.database.entity.ProtocoloEntity;
import io.github.jvondoellinger.api_gerenciamento_de_protocolo.modules.protocol_module.infrastructure.database.data.ProtocolCassandraRepository;
import io.github.jvondoellinger.api_gerenciamento_de_protocolo.modules.protocol_module.infrastructure.database.utils.CassandraQueryUtils;
import io.github.jvondoellinger.api_gerenciamento_de_protocolo.modules.protocol_module.domain.contracts.persistence.filters.ProtocolNumberFilter;
import io.github.jvondoellinger.api_gerenciamento_de_protocolo.modules.protocol_module.domain.valueObjects.DomainId;
import io.github.jvondoellinger.api_gerenciamento_de_protocolo.modules.protocol_module.domain.contracts.persistence.ProtocolReadRepository;
import io.github.jvondoellinger.api_gerenciamento_de_protocolo.modules.protocol_module.domain.contracts.persistence.filters.PaginationFilter;
import io.github.jvondoellinger.api_gerenciamento_de_protocolo.modules.protocol_module.domain.Protocolo;
import org.springframework.data.cassandra.core.ReactiveCassandraTemplate;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Objects;

@Service
public class ProtocolReadRepositoryImpl implements ProtocolReadRepository {

    private final ProtocolCassandraRepository repository;
    private final ReactiveCassandraTemplate template;

    public ProtocolReadRepositoryImpl(ProtocolCassandraRepository repository, ReactiveCassandraTemplate template) {
        this.repository = repository;
        this.template = template;
    }

    @Override
    public Flux<Protocolo> query(PaginationFilter filter) {
        var request = CassandraQueryUtils.convert(filter);
        return template
                .select(request, ProtocoloEntity.class)
                .map(ObjectEntity::parse);
    }

    @Override
    public Mono<Protocolo> query(DomainId id) {
        return repository
                .findById(id.getValue())
                .map(ObjectEntity::parse);
    }

    @Override
    public Mono<Protocolo> query(ProtocolNumberFilter filter) {
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
