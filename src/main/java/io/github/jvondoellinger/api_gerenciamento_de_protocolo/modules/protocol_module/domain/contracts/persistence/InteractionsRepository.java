package io.github.jvondoellinger.api_gerenciamento_de_protocolo.modules.protocol_module.domain.contracts.persistence;

import io.github.jvondoellinger.api_gerenciamento_de_protocolo.modules.protocol_module.domain.Interaction;
import reactor.core.publisher.Mono;

public interface InteractionsRepository {
    Mono<Interaction> save(Interaction interaction);
    Mono<Interaction> update(Interaction interaction);
}