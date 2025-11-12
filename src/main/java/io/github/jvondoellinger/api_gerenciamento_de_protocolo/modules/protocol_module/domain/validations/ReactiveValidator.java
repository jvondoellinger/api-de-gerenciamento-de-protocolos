package io.github.jvondoellinger.api_gerenciamento_de_protocolo.modules.protocol_module.domain.validations;

import io.github.jvondoellinger.api_gerenciamento_de_protocolo.modules.protocol_module.domain.Protocolo;
import io.github.jvondoellinger.api_gerenciamento_de_protocolo.modules.protocol_module.domain.exception.ValidationException;
import io.github.jvondoellinger.api_gerenciamento_de_protocolo.modules.protocol_module.domain.validations.interfaces.ConditionRule;
import io.github.jvondoellinger.api_gerenciamento_de_protocolo.modules.protocol_module.domain.validations.interfaces.ConditionValidator;
import io.github.jvondoellinger.api_gerenciamento_de_protocolo.modules.protocol_module.domain.validations.interfaces.Conditions;
import io.github.jvondoellinger.api_gerenciamento_de_protocolo.modules.protocol_module.domain.validations.interfaces.RuleConditionBuilder;
import io.github.jvondoellinger.api_gerenciamento_de_protocolo.modules.protocol_module.infrastructure.database.entity.ProtocoloEntity;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

public class ReactiveValidator<T> implements ConditionValidator<T> {
      private final List<ConditionRule<T>> rules = new ArrayList<>();
      private final List<RuleConditionBuilder<T, ?>> conditions = new ArrayList<>();

      public <R> Conditions<T> ruleFor(RuleConditionBuilder<T, R> condition) {
            conditions.add(condition);
            var rule = new ConditionRuleImpl<T>();
            return new ConditionsImpl<T>();
      }


      @Override
      public Mono<Void> evaluate(T value) throws ValidationException {
            return null;
      }
}
