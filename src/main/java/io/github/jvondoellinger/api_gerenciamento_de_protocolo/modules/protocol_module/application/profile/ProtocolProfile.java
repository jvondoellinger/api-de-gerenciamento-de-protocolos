package io.github.jvondoellinger.api_gerenciamento_de_protocolo.modules.protocol_module.application.profile;

public interface ProtocolProfile {
      String getRule();

      boolean compatible(String rule);
}
