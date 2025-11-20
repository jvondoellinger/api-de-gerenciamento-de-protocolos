package io.github.jvondoellinger.api_gerenciamento_de_protocolo.modules.protocol_module.infrastructure.permission.checker;

import io.github.jvondoellinger.api_gerenciamento_de_protocolo.modules.protocol_module.domain.valueObjects.DomainId;
import io.github.jvondoellinger.api_gerenciamento_de_protocolo.modules.protocol_module.infrastructure.database.contracts.UserProtocolRelationshipRepository;
import io.github.jvondoellinger.api_gerenciamento_de_protocolo.modules.protocol_module.infrastructure.permission.enums.PublishPermissions;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public class PublishPermissionCheckerImpl implements PublishPermissionChecker {
      private final UserProtocolRelationshipRepository repository;

      public PublishPermissionCheckerImpl(UserProtocolRelationshipRepository repository) {
            this.repository = repository;
      }

      @Override
      public Mono<Boolean> hasPermission(PublishPermissions permission, DomainId userId) {
            return repository
                    .findRelationships(userId)
                    .hasElements();
      }
}
