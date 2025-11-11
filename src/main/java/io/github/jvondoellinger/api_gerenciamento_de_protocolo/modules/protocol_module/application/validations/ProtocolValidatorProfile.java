package io.github.jvondoellinger.api_gerenciamento_de_protocolo.modules.protocol_module.application.validations;

import br.com.fluentvalidator.AbstractValidator;
import io.github.jvondoellinger.api_gerenciamento_de_protocolo.modules.protocol_module.domain.Protocolo;
import io.github.jvondoellinger.api_gerenciamento_de_protocolo.modules.protocol_module.domain.contracts.persistence.ProtocolReadRepository;
import io.github.jvondoellinger.api_gerenciamento_de_protocolo.modules.protocol_module.domain.contracts.persistence.filters.ProtocolNumberFilter;
import io.github.jvondoellinger.api_gerenciamento_de_protocolo.modules.protocol_module.domain.validations.ReactiveValidator;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

import java.util.concurrent.ConcurrentMap;

@Component
public class ProtocolValidatorProfile {
      private final ProtocolReadRepository readRepository;

      public ProtocolValidatorProfile(ProtocolReadRepository readRepository) {
            this.readRepository = readRepository;
      }
}