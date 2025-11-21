package io.github.jvondoellinger.api_gerenciamento_de_protocolo.modules.protocol_module.infrastructure.permission.checker;

import io.github.jvondoellinger.api_gerenciamento_de_protocolo.modules.protocol_module.domain.valueObjects.DomainId;
import io.github.jvondoellinger.api_gerenciamento_de_protocolo.modules.protocol_module.infrastructure.database.contracts.UserProtocolRelationshipRepository;
import io.github.jvondoellinger.api_gerenciamento_de_protocolo.modules.protocol_module.infrastructure.permission.PermissionFactory;
import io.github.jvondoellinger.api_gerenciamento_de_protocolo.modules.protocol_module.infrastructure.permission.enums.ReadPermissions;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public class ReadPermissionCheckerImpl implements ReadPermissionChecker{
      private final UserProtocolRelationshipRepository repository;

      public ReadPermissionCheckerImpl(UserProtocolRelationshipRepository repository) {
            this.repository = repository;
      }

      @Override
      public Mono<Boolean> hasPermission(ReadPermissions permission, DomainId userId) {
            var permissionObj = PermissionFactory.getInstance(permission.name());

            return repository
                    .findRelationships(userId)
                    .filter(x -> x.containsPermission(permissionObj))
                    .hasElements();
      }
}
