package io.github.jvondoellinger.api_gerenciamento_de_protocolo.modules.protocol_module.application.validations;

import io.github.jvondoellinger.api_gerenciamento_de_protocolo.modules.protocol_module.domain.contracts.persistence.ProtocolReadRepository;
import org.springframework.stereotype.Component;

@Component
public class ProtocolValidatorProfile {
      private final ProtocolReadRepository readRepository;

      public ProtocolValidatorProfile(ProtocolReadRepository readRepository) {
            this.readRepository = readRepository;
      }
}