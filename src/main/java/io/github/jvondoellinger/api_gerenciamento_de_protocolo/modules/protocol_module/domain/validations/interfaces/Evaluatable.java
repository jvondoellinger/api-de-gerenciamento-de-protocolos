package io.github.jvondoellinger.api_gerenciamento_de_protocolo.modules.protocol_module.domain.validations.interfaces;

import reactor.core.publisher.Mono;

public interface Evaluatable<T> {
      Mono<Void> evaluate(T value);
}
