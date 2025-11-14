package io.github.jvondoellinger.api_gerenciamento_de_protocolo.modules.protocol_module.domain.validations.interfaces;

import io.github.jvondoellinger.api_gerenciamento_de_protocolo.modules.protocol_module.domain.exception.ValidationException;
import reactor.core.publisher.Mono;

public interface ConditionBuilder<T> {;

      ConditionRule<T> notNull() throws ValidationException;

      ConditionRule<T> when(Condition condition) throws ValidationException;

      ConditionRule<T> notEmpty() throws ValidationException;

      ConditionRule<T> length(int min, int max)  throws ValidationException;

      ConditionRule<T> must(Mono<Boolean> mono) throws ValidationException;

      ConditionRule<T> notEquals(T another) throws ValidationException;
}