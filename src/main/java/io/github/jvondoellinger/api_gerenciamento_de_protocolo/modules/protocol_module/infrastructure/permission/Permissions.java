package io.github.jvondoellinger.api_gerenciamento_de_protocolo.modules.protocol_module.infrastructure.permission;

import java.util.ArrayList;
import java.util.List;

public final class Permissions {
      private final List<Permission> permissions;
      private Permissions() {
            permissions = new ArrayList<>();
      }

      public static Permissions getInstance() {
            return new Permissions();
      }

      public Permissions add(Permission permission) {
            permissions.add(permission);
            return this;
      }

      public List<Permission> getPermissions() {
            return List.copyOf(permissions); // Read-only
      }
}
