package io.github.jvondoellinger.api_gerenciamento_de_protocolo.modules.protocol_module.infrastructure.database;

import io.github.jvondoellinger.api_gerenciamento_de_protocolo.modules.protocol_module.infrastructure.database.entity.ObjectEntity;
import io.github.jvondoellinger.api_gerenciamento_de_protocolo.modules.protocol_module.infrastructure.database.entity.ProtocoloEntity;
import io.github.jvondoellinger.api_gerenciamento_de_protocolo.modules.protocol_module.infrastructure.database.springboot.ProtocolCassandraRepository;
import io.github.jvondoellinger.api_gerenciamento_de_protocolo.modules.protocol_module.domain.contracts.persistence.ProtocolRepository;
import io.github.jvondoellinger.api_gerenciamento_de_protocolo.modules.protocol_module.domain.Protocolo;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public class ProtocoloRepositoryImpl implements ProtocolRepository {
    private final ProtocolCassandraRepository repository;

    public ProtocoloRepositoryImpl(ProtocolCassandraRepository repository) {
        this.repository = repository;
    }

    @Override
    public Mono<Protocolo> save(Protocolo protocolo) {
        return repository
                .save(ProtocoloEntity.create(protocolo))
                .map(ObjectEntity::parse);
    }

    @Override
    public Mono<Protocolo> update(Protocolo protocolo) {
        return repository
                .save(ProtocoloEntity.create(protocolo))
                .map(ObjectEntity::parse);
    }
}
