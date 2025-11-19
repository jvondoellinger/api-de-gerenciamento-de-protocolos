package io.github.jvondoellinger.api_gerenciamento_de_protocolo.modules.protocol_module.infrastructure.pub;

import io.github.jvondoellinger.api_gerenciamento_de_protocolo.modules.protocol_module.domain.events.pub.DomainEventPublisher;
import io.github.jvondoellinger.api_gerenciamento_de_protocolo.modules.protocol_module.infrastructure.permission.events.CreatePermissionsRelationshipEvent;
import io.github.jvondoellinger.api_gerenciamento_de_protocolo.modules.protocol_module.infrastructure.subs.CreatePermissionRelationshipEventHandler;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public class CreatePermissionRelationshipEventPublisher implements DomainEventPublisher<CreatePermissionsRelationshipEvent> {
      private final CreatePermissionRelationshipEventHandler handler;

      public CreatePermissionRelationshipEventPublisher(CreatePermissionRelationshipEventHandler handler) {
            this.handler = handler;
      }

      @Override
      public Mono<Void> publish(CreatePermissionsRelationshipEvent event) {
            return handler.handle(event);
      }

      @Override
      public Class<CreatePermissionsRelationshipEvent> eventType() {
            return CreatePermissionsRelationshipEvent.class;
      }
}
