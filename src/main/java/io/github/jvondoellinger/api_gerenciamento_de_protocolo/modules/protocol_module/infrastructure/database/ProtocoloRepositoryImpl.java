package io.github.jvondoellinger.api_gerenciamento_de_protocolo.modules.protocol_module.infrastructure.database;

import io.github.jvondoellinger.api_gerenciamento_de_protocolo.modules.protocol_module.domain.Protocol;
import io.github.jvondoellinger.api_gerenciamento_de_protocolo.modules.protocol_module.infrastructure.database.entity.ObjectEntity;
import io.github.jvondoellinger.api_gerenciamento_de_protocolo.modules.protocol_module.infrastructure.database.entity.ProtocoloEntity;
import io.github.jvondoellinger.api_gerenciamento_de_protocolo.modules.protocol_module.infrastructure.database.data.ProtocolCassandraRepository;
import io.github.jvondoellinger.api_gerenciamento_de_protocolo.modules.protocol_module.domain.contracts.persistence.ProtocolRepository;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public class ProtocoloRepositoryImpl implements ProtocolRepository {
    private final ProtocolCassandraRepository repository;

    public ProtocoloRepositoryImpl(ProtocolCassandraRepository repository) {
        this.repository = repository;
    }

    @Override
    public Mono<Protocol> save(Protocol protocol) {
        return repository
                .save(ProtocoloEntity.create(protocol))
                .map(ObjectEntity::parse);
    }

    @Override
    public Mono<Protocol> update(Protocol protocol) {
        return repository
                .save(ProtocoloEntity.create(protocol))
                .map(ObjectEntity::parse);
    }
}
