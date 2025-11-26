package io.github.jvondoellinger.api_gerenciamento_de_protocolo.modules.protocol_module.infrastructure.database.interactions;

import io.github.jvondoellinger.api_gerenciamento_de_protocolo.modules.protocol_module.infrastructure.database.ObjectEntity;
import io.github.jvondoellinger.api_gerenciamento_de_protocolo.modules.protocol_module.infrastructure.database.data.InteractionCassandraRepository;
import io.github.jvondoellinger.api_gerenciamento_de_protocolo.modules.protocol_module.domain.Interaction;
import io.github.jvondoellinger.api_gerenciamento_de_protocolo.modules.protocol_module.domain.contracts.persistence.InteractionsWriteRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

@Repository
public class InteractionsRepositoryImpl implements InteractionsWriteRepository {
    private final InteractionCassandraRepository repository;

    public InteractionsRepositoryImpl(InteractionCassandraRepository repository) {
        this.repository = repository;
    }

    @Override
    public Mono<Interaction> save(Interaction interaction) {
        return repository
                .save(InteractionEntity.create(interaction))
                .map(ObjectEntity::parse);
    }

    @Override
    public Mono<Interaction> update(Interaction interaction) {
        return repository
                .save(InteractionEntity.create(interaction))
                .map(ObjectEntity::parse);
    }
}
