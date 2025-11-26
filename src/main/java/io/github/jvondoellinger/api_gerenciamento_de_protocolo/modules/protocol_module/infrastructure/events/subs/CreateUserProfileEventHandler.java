package io.github.jvondoellinger.api_gerenciamento_de_protocolo.modules.protocol_module.infrastructure.events.subs;

import io.github.jvondoellinger.api_gerenciamento_de_protocolo.modules.protocol_module.domain.contracts.persistence.UserProfileWriteRepository;
import io.github.jvondoellinger.api_gerenciamento_de_protocolo.modules.protocol_module.domain.events.sub.DomainEventHandler;
import io.github.jvondoellinger.api_gerenciamento_de_protocolo.modules.protocol_module.domain.events.CreateUserProfileEvent;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public class CreateUserProfileEventHandler implements DomainEventHandler<CreateUserProfileEvent> {
      private final UserProfileWriteRepository repository;

      public CreateUserProfileEventHandler(UserProfileWriteRepository repository) {
            this.repository = repository;
      }

      @Override
      public Mono<Void> handle(CreateUserProfileEvent event) {
            var profile = event.getProfile();
            return repository.save(profile);
      }
}
