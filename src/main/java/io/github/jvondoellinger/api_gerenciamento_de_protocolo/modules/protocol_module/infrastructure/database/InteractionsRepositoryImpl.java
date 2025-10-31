package io.github.jvondoellinger.api_gerenciamento_de_protocolo.modules.protocol_module.infrastructure.database;

import io.github.jvondoellinger.api_gerenciamento_de_protocolo.modules.protocol_module.infrastructure.database.serialize.InteractionSerializable;
import io.github.jvondoellinger.api_gerenciamento_de_protocolo.modules.protocol_module.infrastructure.database.serialize.ObjectSerialize;
import io.github.jvondoellinger.api_gerenciamento_de_protocolo.modules.protocol_module.infrastructure.database.springboot.InteractionCassandraRepository;
import io.github.jvondoellinger.api_gerenciamento_de_protocolo.modules.protocol_module.domain.Interaction;
import io.github.jvondoellinger.api_gerenciamento_de_protocolo.modules.protocol_module.domain.contracts.persistence.InteractionsRepository;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public class InteractionsRepositoryImpl implements InteractionsRepository {
    private final InteractionCassandraRepository repository;

    public InteractionsRepositoryImpl(InteractionCassandraRepository repository) {
        this.repository = repository;
    }

    @Override
    public Mono<Interaction> save(Interaction interaction) {
        return repository
                .save(InteractionSerializable.create(interaction))
                .map(ObjectSerialize::parse);
    }

    @Override
    public Mono<Interaction> update(Interaction interaction) {
        return repository
                .save(InteractionSerializable.create(interaction))
                .map(ObjectSerialize::parse);
    }
}
