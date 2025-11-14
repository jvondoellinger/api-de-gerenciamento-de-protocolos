package io.github.jvondoellinger.api_gerenciamento_de_protocolo.modules.protocol_module.domain.validations.interfaces;

public interface RuleBuilder<T> {
      RuleBuilder<T> addValidator(PropertyValidator<T> validator);
      RuleBuilder<T> withMessage(String message);
      Rule<T> build();
}