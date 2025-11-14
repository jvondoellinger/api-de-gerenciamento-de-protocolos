package io.github.jvondoellinger.api_gerenciamento_de_protocolo.modules.protocol_module.domain.validations.interfaces;

@FunctionalInterface
public interface Condition {
      boolean execute();
}
