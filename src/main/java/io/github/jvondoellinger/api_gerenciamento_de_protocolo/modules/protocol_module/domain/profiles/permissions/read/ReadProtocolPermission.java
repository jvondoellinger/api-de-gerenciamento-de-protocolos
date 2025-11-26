package io.github.jvondoellinger.api_gerenciamento_de_protocolo.modules.protocol_module.domain.profiles.permissions.read;

import io.github.jvondoellinger.api_gerenciamento_de_protocolo.modules.protocol_module.domain.profiles.permissions.Permission;

public record ReadProtocolPermission() implements Permission {
      @Override
      public String getName() {
            return "read::protocol";
      }
}
