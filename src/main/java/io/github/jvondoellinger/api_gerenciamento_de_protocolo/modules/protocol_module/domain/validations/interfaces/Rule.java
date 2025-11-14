package io.github.jvondoellinger.api_gerenciamento_de_protocolo.modules.protocol_module.domain.validations.interfaces;

public interface Rule<T> {
      Rule<T> evaluate(T instancew);
}
