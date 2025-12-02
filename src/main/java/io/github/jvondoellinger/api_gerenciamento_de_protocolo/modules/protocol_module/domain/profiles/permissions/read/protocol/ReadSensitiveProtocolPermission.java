package io.github.jvondoellinger.api_gerenciamento_de_protocolo.modules.protocol_module.domain.profiles.permissions.read.protocol;

import io.github.jvondoellinger.api_gerenciamento_de_protocolo.modules.protocol_module.domain.profiles.permissions.Permission;

public record ReadSensitiveProtocolPermission() implements Permission {
      @Override
      public String getName() {
            return "read::protocol::sensitive";
      }
}
