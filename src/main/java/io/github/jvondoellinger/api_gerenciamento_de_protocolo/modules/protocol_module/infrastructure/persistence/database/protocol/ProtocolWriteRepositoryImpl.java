package io.github.jvondoellinger.api_gerenciamento_de_protocolo.modules.protocol_module.infrastructure.persistence.database.protocol;

import io.github.jvondoellinger.api_gerenciamento_de_protocolo.modules.protocol_module.domain.Protocol;
import io.github.jvondoellinger.api_gerenciamento_de_protocolo.modules.protocol_module.infrastructure.persistence.database.ObjectEntity;
import io.github.jvondoellinger.api_gerenciamento_de_protocolo.modules.protocol_module.infrastructure.persistence.database.data.ProtocolCassandraRepository;
import io.github.jvondoellinger.api_gerenciamento_de_protocolo.modules.protocol_module.domain.contracts.persistence.ProtocolWriteRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

@Repository
public class ProtocolWriteRepositoryImpl implements ProtocolWriteRepository {
    private final ProtocolCassandraRepository repository;

    public ProtocolWriteRepositoryImpl(ProtocolCassandraRepository repository) {
        this.repository = repository;
    }

    @Override
    public Mono<Protocol> save(Protocol protocol) {
        return repository
                .save(ProtocolEntity.create(protocol))
                .map(ObjectEntity::parse);
    }

    @Override
    public Mono<Protocol> update(Protocol protocol) {
        return repository
                .save(ProtocolEntity.create(protocol))
                .map(ObjectEntity::parse);
    }
}
