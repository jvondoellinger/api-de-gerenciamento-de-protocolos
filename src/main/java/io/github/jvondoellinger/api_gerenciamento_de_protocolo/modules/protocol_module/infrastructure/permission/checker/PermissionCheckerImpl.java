package io.github.jvondoellinger.api_gerenciamento_de_protocolo.modules.protocol_module.infrastructure.permission.checker;

import io.github.jvondoellinger.api_gerenciamento_de_protocolo.modules.protocol_module.domain.UserProfile;
import io.github.jvondoellinger.api_gerenciamento_de_protocolo.modules.protocol_module.domain.annotiation.ImplementsAfter;
import io.github.jvondoellinger.api_gerenciamento_de_protocolo.modules.protocol_module.domain.contracts.persistence.UserProfileReadRepository;
import io.github.jvondoellinger.api_gerenciamento_de_protocolo.modules.protocol_module.domain.profiles.permissions.Permissions;
import io.github.jvondoellinger.api_gerenciamento_de_protocolo.modules.protocol_module.domain.valueObjects.DomainId;
import io.github.jvondoellinger.api_gerenciamento_de_protocolo.modules.protocol_module.domain.profiles.permissions.Permission;
import io.github.jvondoellinger.api_gerenciamento_de_protocolo.modules.protocol_module.infrastructure.permission.exception.MissingPermissionException;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
@ImplementsAfter
public class PermissionCheckerImpl implements PermissionChecker {
      private final UserProfileReadRepository repository;

      public PermissionCheckerImpl(UserProfileReadRepository repository) {
            this.repository = repository;
      }

      @Override
      public Mono<Boolean> hasPermission(Permission permission, DomainId userId) {
            return repository
                    .queryByUserId(userId)
                    .map(x -> compare(x, permission));

      }

      @Override
      public Mono<Boolean> hasPermission(Permissions permissions, DomainId userId) {
            return repository
                    .queryByUserId(userId)
                    .map(x -> compare(x, permissions));
      }

      @Override
      public Mono<Boolean> permittedOrThrow(Permission permission, DomainId userId) throws MissingPermissionException {
            return repository
                    .queryByUserId(userId)
                    .map(x -> compare(x, permission));
      }

      @Override
      public Mono<Boolean> permittedOrThrow(Permissions permissions, DomainId userId) throws MissingPermissionException {
            return repository
                    .queryByUserId(userId)
                    .map(x -> compare(x, permissions));
      }

      private boolean compare(UserProfile profile, Permission permission) {
            return profile
                    .getProfile()
                    .getPermissions()
                    .contains(permission);
      }
      private boolean compare(UserProfile profile, Permissions permissions) {
            return profile
                    .getProfile()
                    .getPermissions()
                    .equals(permissions);
      }
}
