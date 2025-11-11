package io.github.jvondoellinger.api_gerenciamento_de_protocolo.modules.protocol_module.domain.validations;

@FunctionalInterface
public interface ConditionBuilder<T> {
      boolean evaluate(T value);
}
