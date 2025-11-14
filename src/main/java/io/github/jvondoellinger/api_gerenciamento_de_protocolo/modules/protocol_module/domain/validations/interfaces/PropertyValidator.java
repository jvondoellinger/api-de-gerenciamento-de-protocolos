package io.github.jvondoellinger.api_gerenciamento_de_protocolo.modules.protocol_module.domain.validations.interfaces;

public interface PropertyValidator<T> {
      RuleBuilder<T> validate(T instance);
}
