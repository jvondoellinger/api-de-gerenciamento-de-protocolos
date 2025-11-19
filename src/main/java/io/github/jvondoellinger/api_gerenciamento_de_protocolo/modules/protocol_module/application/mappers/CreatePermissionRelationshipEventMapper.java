package io.github.jvondoellinger.api_gerenciamento_de_protocolo.modules.protocol_module.application.mappers;

import io.github.jvondoellinger.api_gerenciamento_de_protocolo.modules.protocol_module.application.commands.CreatePermissionRelationshipCommand;
import io.github.jvondoellinger.api_gerenciamento_de_protocolo.modules.protocol_module.domain.valueObjects.DomainId;
import io.github.jvondoellinger.api_gerenciamento_de_protocolo.modules.protocol_module.infrastructure.permission.PermissionFactory;
import io.github.jvondoellinger.api_gerenciamento_de_protocolo.modules.protocol_module.infrastructure.permission.events.CreatePermissionsRelationshipEvent;

public class CreatePermissionRelationshipEventMapper {
      private CreatePermissionRelationshipEventMapper() {}

      public static CreatePermissionsRelationshipEvent map(CreatePermissionRelationshipCommand command) {
            var permissions = command
                    .permissions()
                    .stream()
                    .map(PermissionFactory::getInstance)
                    .toList();
            return new CreatePermissionsRelationshipEvent(
                    DomainId.from(command.userId()),
                    permissions
            );
      }
}
