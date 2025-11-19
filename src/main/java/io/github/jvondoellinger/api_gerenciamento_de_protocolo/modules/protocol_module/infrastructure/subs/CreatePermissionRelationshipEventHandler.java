package io.github.jvondoellinger.api_gerenciamento_de_protocolo.modules.protocol_module.infrastructure.subs;

import io.github.jvondoellinger.api_gerenciamento_de_protocolo.modules.protocol_module.domain.events.sub.DomainEventHandler;
import io.github.jvondoellinger.api_gerenciamento_de_protocolo.modules.protocol_module.infrastructure.UserProtocolPermissionRelationship;
import io.github.jvondoellinger.api_gerenciamento_de_protocolo.modules.protocol_module.infrastructure.database.contracts.UserProtocolRelationshipRepository;
import io.github.jvondoellinger.api_gerenciamento_de_protocolo.modules.protocol_module.infrastructure.permission.events.CreatePermissionsRelationshipEvent;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public class CreatePermissionRelationshipEventHandler implements DomainEventHandler<CreatePermissionsRelationshipEvent> {
      private final UserProtocolRelationshipRepository repository;

      public CreatePermissionRelationshipEventHandler(UserProtocolRelationshipRepository repository) {
            this.repository = repository;
      }

      @Override
      public Mono<Void> handle(CreatePermissionsRelationshipEvent event) {
            var relationship = UserProtocolPermissionRelationship.create(event.getUserId(), event.getPermission());
            return repository.createRelationship(relationship);
      }
}
