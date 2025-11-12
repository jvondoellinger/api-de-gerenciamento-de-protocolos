package io.github.jvondoellinger.api_gerenciamento_de_protocolo.modules.protocol_module.domain.validations.interfaces;

import reactor.core.publisher.Mono;

public interface Conditions<T> {
      ConditionRule<T> notNull();

      ConditionRule<T> when(ConditionBuilder condition);

      ConditionRule<T> notEmpty();

      ConditionRule<T> length(int min, int max);

      ConditionRule<T> must(Mono<Boolean> mono);

      ConditionRule<T> notEquals(T another);

      boolean isBroken();
}
