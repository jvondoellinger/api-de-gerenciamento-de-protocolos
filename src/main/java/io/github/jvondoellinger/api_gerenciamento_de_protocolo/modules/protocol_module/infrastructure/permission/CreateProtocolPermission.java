package io.github.jvondoellinger.api_gerenciamento_de_protocolo.modules.protocol_module.infrastructure.permission;

public record CreateProtocolPermission() implements Permission {
      @Override
      public String getName() {
            return Permissions.CREATE_PROTOCOL.name();
      }
}
