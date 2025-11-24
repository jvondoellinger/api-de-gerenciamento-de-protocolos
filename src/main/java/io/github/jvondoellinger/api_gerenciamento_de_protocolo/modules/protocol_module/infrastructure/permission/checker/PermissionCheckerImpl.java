package io.github.jvondoellinger.api_gerenciamento_de_protocolo.modules.protocol_module.infrastructure.permission.checker;

import io.github.jvondoellinger.api_gerenciamento_de_protocolo.modules.protocol_module.domain.valueObjects.DomainId;
import io.github.jvondoellinger.api_gerenciamento_de_protocolo.modules.protocol_module.infrastructure.UserProtocolPermissionRelationship;
import io.github.jvondoellinger.api_gerenciamento_de_protocolo.modules.protocol_module.infrastructure.database.contracts.UserProtocolRelationshipRepository;
import io.github.jvondoellinger.api_gerenciamento_de_protocolo.modules.protocol_module.infrastructure.permission.Permission;
import io.github.jvondoellinger.api_gerenciamento_de_protocolo.modules.protocol_module.infrastructure.permission.Permissions;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public class PermissionCheckerImpl implements PermissionChecker {
      private final UserProtocolRelationshipRepository repository;

      public PermissionCheckerImpl(UserProtocolRelationshipRepository repository) {
            this.repository = repository;
      }

      @Override
      public Mono<Boolean> hasPermission(Permission permission, DomainId userId) {
            return repository
                    .findRelationships(userId)
                    .filter(x -> x.containsPermission(permission))
                    .hasElements();
      }

      @Override
      public Mono<Boolean> hasPermission(Permissions permissions, DomainId userId) {
            return repository
                    .findRelationships(userId)
                    .filter(rs -> containsPermissionsOnRelationship(rs, permissions))
                    .hasElements();
      }

      // Util
      private boolean containsPermissionsOnRelationship(UserProtocolPermissionRelationship relationship, Permissions permissions) {
            return !permissions // Invert o boolean retornado
                    .getPermissions()
                    .stream()
                    .filter(relationship::containsPermission) // Seleciona somente as permissoes que possuem na relationship
                    .toList()
                    .isEmpty();
      }
}
