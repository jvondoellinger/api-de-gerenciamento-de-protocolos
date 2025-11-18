package io.github.jvondoellinger.api_gerenciamento_de_protocolo.modules.protocol_module.infrastructure.permission;

public record InteractProtocolPermission() implements Permission {
      @Override
      public String getName() {
            return Permissions.INTERACT_ON_PROTOCOL.name();
      }
}
