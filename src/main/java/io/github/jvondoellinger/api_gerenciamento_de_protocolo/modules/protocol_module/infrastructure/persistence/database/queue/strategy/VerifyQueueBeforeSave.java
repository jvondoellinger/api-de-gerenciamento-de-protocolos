package io.github.jvondoellinger.api_gerenciamento_de_protocolo.modules.protocol_module.infrastructure.persistence.database.queue.strategy;

import io.github.jvondoellinger.api_gerenciamento_de_protocolo.modules.protocol_module.domain.Protocol;
import io.github.jvondoellinger.api_gerenciamento_de_protocolo.modules.protocol_module.domain.contracts.persistence.QueueReadRepository;
import io.github.jvondoellinger.api_gerenciamento_de_protocolo.modules.protocol_module.domain.contracts.strategy.ProtocolValidation;
import io.github.jvondoellinger.api_gerenciamento_de_protocolo.modules.protocol_module.infrastructure.persistence.database.protocol.exceptions.ProtocoloValidationException;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.Objects;

@Service
public class VerifyQueueBeforeSave implements ProtocolValidation {
      private final QueueReadRepository readRepository;

      public VerifyQueueBeforeSave(QueueReadRepository readRepository) {
            this.readRepository = readRepository;
      }

      @Override
      public Mono<Void> validate(Protocol protocol) {
            var id = protocol.getQueue().getId();

            if (Objects.isNull(id)) {
                  throw new ProtocoloValidationException("It is not possible to save a protocol without queue destination");
            }
            return readRepository
                    .exists(id)
                    .doOnNext(this::validate)
                    .then();
      }

      private void validate(Boolean exists) {
            if (Objects.isNull(exists)) {
                  throw new RuntimeException("Unexpected value is received! Type: Boolean Param: 'exists' - can't be null.");
            }
            if (!exists) {
                  throw new ProtocoloValidationException("It is not possible to save a protocol without a valid queue");
            }
      }
}
