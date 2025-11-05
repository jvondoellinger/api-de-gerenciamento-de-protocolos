package io.github.jvondoellinger.api_gerenciamento_de_protocolo.modules.protocol_module.infrastructure.database.composite;

import io.github.jvondoellinger.api_gerenciamento_de_protocolo.modules.protocol_module.domain.Protocolo;
import io.github.jvondoellinger.api_gerenciamento_de_protocolo.modules.protocol_module.domain.contracts.persistence.strategy.ProtocolValidation;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProtocolValidationComposite implements ProtocolValidation {
      private final List<ProtocolValidation> validations;

      // While having only one implementation for this interface, it will have to stay like this
      public ProtocolValidationComposite(ProtocolValidation validations) {
            this.validations = new ArrayList<>();
            this.validations.add(validations);
      }

      @Override
      public Mono<Void> validate(Protocolo protocolo) {
            return Mono.fromRunnable(() -> {
                  for (var validator : validations) {
                        validator.validate(protocolo);
                  }
            });
      }
}
