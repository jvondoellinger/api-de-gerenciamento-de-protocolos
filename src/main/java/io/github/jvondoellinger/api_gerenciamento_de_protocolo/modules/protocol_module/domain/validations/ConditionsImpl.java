package io.github.jvondoellinger.api_gerenciamento_de_protocolo.modules.protocol_module.domain.validations;

import io.github.jvondoellinger.api_gerenciamento_de_protocolo.modules.protocol_module.domain.validations.interfaces.ConditionBuilder;
import io.github.jvondoellinger.api_gerenciamento_de_protocolo.modules.protocol_module.domain.validations.interfaces.ConditionRule;
import io.github.jvondoellinger.api_gerenciamento_de_protocolo.modules.protocol_module.domain.validations.interfaces.Conditions;
import reactor.core.publisher.Mono;

import java.util.Objects;

public class ConditionsImpl<T> implements Conditions<T> {
      private boolean broken;
      private final ConditionRule<T> rule;
      protected ConditionsImpl(ConditionRule<T> rule) {
            this.rule = rule;
      }


      @Override
      public ConditionRule<T> notNull() {
            if (Objects.isNull(rule.getValue()))
                  broken();

            return rule;
      }

      @Override
      public ConditionRule<T> when(ConditionBuilder condition) {
            var supplies = condition.execute();

            if (!supplies)
                  broken();

            return rule;
      }

      @Override
      public ConditionRule<T> notEmpty() {
            var value = rule.getValue();
            // Validar Strings, Listas e HashMaps
            return rule;
      }

      @Override
      public ConditionRule<T> length(int min, int max) {
            // Validar Strings, Listas e HashMaps
            return rule;
      }

      @Override
      public ConditionRule<T> must(Mono<Boolean> mono) {
            // Verificar como fazer isso de forma async
            return rule;
      }

      @Override
      public ConditionRule<T> notEquals(Object another) {
            if(rule.getValue().equals(another))
                  broken();

            return rule;
      }

      @Override
      public boolean isBroken() {
            return broken;
      }

      private void broken() {
            broken = true;
      }
}
