package io.github.jvondoellinger.api_gerenciamento_de_protocolo.modules.protocol_module.domain.contracts.persistence.strategy;

import io.github.jvondoellinger.api_gerenciamento_de_protocolo.modules.protocol_module.domain.Protocol;
import reactor.core.publisher.Mono;

public interface ProtocolValidation {
      Mono<Void> validate(Protocol protocol);
}
