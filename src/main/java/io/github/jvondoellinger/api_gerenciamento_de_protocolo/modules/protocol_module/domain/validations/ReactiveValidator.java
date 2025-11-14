package io.github.jvondoellinger.api_gerenciamento_de_protocolo.modules.protocol_module.domain.validations;

import io.github.jvondoellinger.api_gerenciamento_de_protocolo.modules.protocol_module.domain.Protocolo;
import io.github.jvondoellinger.api_gerenciamento_de_protocolo.modules.protocol_module.domain.annotiation.ImplementsAfter;
import io.github.jvondoellinger.api_gerenciamento_de_protocolo.modules.protocol_module.domain.validations.interfaces.ConditionRule;
import io.github.jvondoellinger.api_gerenciamento_de_protocolo.modules.protocol_module.domain.validations.interfaces.PropertyRuleBuilder;
import io.github.jvondoellinger.api_gerenciamento_de_protocolo.modules.protocol_module.domain.validations.interfaces.RuleBuilder;

import java.util.ArrayList;
import java.util.List;

@ImplementsAfter
public class ReactiveValidator<T> {

      private final List<ConditionRule<T>> rules = new ArrayList<>();

      public <R> RuleBuilder<T> ruleFor(PropertyRuleBuilder<T, R> condition) {

            // Depois, criar um rule builder para juntar os fragmentos
            return null;
      }

      void teste() {
            var a = new ReactiveValidator<Protocolo>();

            a.ruleFor(Protocolo::getId)
                    .addValidator(null)
                    .withMessage("")
                    .build()
                    .evaluate(null);
      }
}
