package io.github.jvondoellinger.api_gerenciamento_de_protocolo.modules.protocol_module.domain.validations;

import io.github.jvondoellinger.api_gerenciamento_de_protocolo.modules.protocol_module.domain.exception.ValidationException;
import io.github.jvondoellinger.api_gerenciamento_de_protocolo.modules.protocol_module.domain.validations.interfaces.ConditionRule;
import reactor.core.publisher.Mono;

public class ConditionRuleImpl<T> implements ConditionRule<T> {
      private final T value;
      private String message;

      public ConditionRuleImpl(T value) {
            this.value = value;
      }

      @Override
      public T getValue() {
            return value;
      }

      @Override
      public ConditionRule<T> withMessage(String message) {
            setMessage(message);
            return this;
      }

      private void setMessage(String message) {
            this.message = message;
      }
}
