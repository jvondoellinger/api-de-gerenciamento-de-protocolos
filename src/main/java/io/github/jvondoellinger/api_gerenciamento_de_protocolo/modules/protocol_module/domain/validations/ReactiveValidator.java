package io.github.jvondoellinger.api_gerenciamento_de_protocolo.modules.protocol_module.domain.validations;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;

public abstract class ReactiveValidator<T> {
      private final List<ConditionBuilder<T>> conditions;

      public ReactiveValidator() {
            this.conditions = new ArrayList<>();
      }

      public ConditionBuilder<T> ruleFor(ConditionBuilder<T> action) {
            return conditions.get(0);
      }
}
