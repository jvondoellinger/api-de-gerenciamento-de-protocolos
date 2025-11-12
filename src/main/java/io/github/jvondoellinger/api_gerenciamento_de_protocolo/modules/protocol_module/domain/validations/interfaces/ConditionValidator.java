package io.github.jvondoellinger.api_gerenciamento_de_protocolo.modules.protocol_module.domain.validations.interfaces;

import io.github.jvondoellinger.api_gerenciamento_de_protocolo.modules.protocol_module.domain.exception.ValidationException;
import reactor.core.publisher.Mono;

public interface ConditionValidator<T> {
      /**
       * @param value Outro objeto á ser comparado
       * @throws ValidationException Quando as regras/condições forem quebradas
       */
      Mono<Void> evaluate(T value) throws ValidationException;
}
