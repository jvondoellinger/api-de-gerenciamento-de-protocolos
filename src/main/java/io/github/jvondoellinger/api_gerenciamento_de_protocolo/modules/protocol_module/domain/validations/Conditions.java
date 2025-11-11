package io.github.jvondoellinger.api_gerenciamento_de_protocolo.modules.protocol_module.domain.validations;

public abstract class Conditions {
      private Conditions() {}

      abstract <T> ConditionRule<T> self();

      public <T> ConditionRule<T> notNull() {
            return self();
      }
}
