package io.github.jvondoellinger.api_gerenciamento_de_protocolo.modules.protocol_module.infrastructure.permission;

import io.github.jvondoellinger.api_gerenciamento_de_protocolo.modules.protocol_module.domain.exception.DomainException;
import io.github.jvondoellinger.api_gerenciamento_de_protocolo.modules.protocol_module.infrastructure.permission.impl.CreateProtocolPermission;
import io.github.jvondoellinger.api_gerenciamento_de_protocolo.modules.protocol_module.infrastructure.permission.impl.InteractProtocolPermission;
import io.github.jvondoellinger.api_gerenciamento_de_protocolo.modules.protocol_module.infrastructure.permission.impl.ViewProtocolPermission;

import java.util.List;

public class PermissionFactory {
      private static final List<Permission> instances = List.of(
              new CreateProtocolPermission(),
              new InteractProtocolPermission(),
              new ViewProtocolPermission()
      );
      private PermissionFactory() {}

      public static Permission getInstance(String permission) {
            return instances
                    .stream()
                    .filter(x -> x.getName().equalsIgnoreCase(permission))
                    .findFirst()
                    .orElseThrow(() -> new DomainException("Don't have any permission: %s".formatted(permission)));
      }
}
