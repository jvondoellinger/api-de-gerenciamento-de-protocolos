package io.github.jvondoellinger.api_gerenciamento_de_protocolo.modules.protocol_module.domain.profiles;

import io.github.jvondoellinger.api_gerenciamento_de_protocolo.modules.protocol_module.domain.exception.DomainException;
import io.github.jvondoellinger.api_gerenciamento_de_protocolo.modules.protocol_module.domain.profiles.permissions.Permission;
import io.github.jvondoellinger.api_gerenciamento_de_protocolo.modules.protocol_module.domain.profiles.permissions.write.protocol.CreateProtocolPermission;
import io.github.jvondoellinger.api_gerenciamento_de_protocolo.modules.protocol_module.domain.profiles.permissions.write.protocol.InteractProtocolPermission;
import io.github.jvondoellinger.api_gerenciamento_de_protocolo.modules.protocol_module.domain.profiles.permissions.read.protocol.ReadProtocolPermission;
import io.github.jvondoellinger.api_gerenciamento_de_protocolo.modules.protocol_module.domain.profiles.permissions.read.protocol.ReadSensitiveProtocolPermission;

import java.util.List;

public class PermissionFactory {
      private static final List<Permission> instances = List.of(
              new CreateProtocolPermission(),
              new InteractProtocolPermission(),
              new ReadProtocolPermission(),
              new ReadSensitiveProtocolPermission()
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
