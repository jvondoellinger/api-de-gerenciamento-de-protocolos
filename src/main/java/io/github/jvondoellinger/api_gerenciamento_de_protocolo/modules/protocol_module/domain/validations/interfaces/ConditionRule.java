package io.github.jvondoellinger.api_gerenciamento_de_protocolo.modules.protocol_module.domain.validations.interfaces;

import io.github.jvondoellinger.api_gerenciamento_de_protocolo.modules.protocol_module.domain.exception.ValidationException;
import reactor.core.publisher.Mono;

public interface ConditionRule<T> {
      T getValue();
      /**
       * @param message Mensagem de erro, caso a condição não seja atendida
       */
      ConditionRule<T> withMessage(String message);

}
