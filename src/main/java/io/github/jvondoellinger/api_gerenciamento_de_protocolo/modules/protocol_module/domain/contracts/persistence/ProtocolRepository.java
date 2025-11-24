package io.github.jvondoellinger.api_gerenciamento_de_protocolo.modules.protocol_module.domain.contracts.persistence;

import io.github.jvondoellinger.api_gerenciamento_de_protocolo.modules.protocol_module.domain.Protocol;
import reactor.core.publisher.Mono;

public interface ProtocolRepository {
    Mono<Protocol> save(Protocol protocol);
    Mono<Protocol> update(Protocol protocol);


}
