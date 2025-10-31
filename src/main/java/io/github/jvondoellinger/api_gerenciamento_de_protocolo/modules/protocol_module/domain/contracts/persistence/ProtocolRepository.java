package io.github.jvondoellinger.api_gerenciamento_de_protocolo.modules.protocol_module.domain.contracts.persistence;

import io.github.jvondoellinger.api_gerenciamento_de_protocolo.modules.protocol_module.domain.Protocolo;
import reactor.core.publisher.Mono;

public interface ProtocolRepository {
    Mono<Protocolo> save(Protocolo protocolo);
    Mono<Protocolo> update(Protocolo protocolo);


}
