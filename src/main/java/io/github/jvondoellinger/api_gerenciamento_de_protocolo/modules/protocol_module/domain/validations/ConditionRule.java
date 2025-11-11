package io.github.jvondoellinger.api_gerenciamento_de_protocolo.modules.protocol_module.domain.validations;

public interface ConditionRule<T> {
      ConditionRule<T> when(ConditionBuilder<T> condition);
      ConditionRule<T> withMessage(String message);

      String getMessage();

      boolean evaluate(T value);
}
