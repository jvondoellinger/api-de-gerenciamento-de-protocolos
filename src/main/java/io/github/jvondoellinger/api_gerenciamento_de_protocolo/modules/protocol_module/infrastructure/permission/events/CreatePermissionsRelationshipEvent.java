package io.github.jvondoellinger.api_gerenciamento_de_protocolo.modules.protocol_module.infrastructure.permission.events;

import io.github.jvondoellinger.api_gerenciamento_de_protocolo.modules.protocol_module.domain.events.UserActivityEvent;
import io.github.jvondoellinger.api_gerenciamento_de_protocolo.modules.protocol_module.domain.valueObjects.DomainId;
import io.github.jvondoellinger.api_gerenciamento_de_protocolo.modules.protocol_module.infrastructure.permission.Permission;

import java.util.List;

public final class CreatePermissionsRelationshipEvent extends UserActivityEvent {
      public CreatePermissionsRelationshipEvent(DomainId userId, List<Permission> permissions) {
            super(userId);
            this.permissions = permissions;
      }

      private final List<Permission> permissions;

      public final List<Permission> getPermission() {
            return permissions;
      }
}
