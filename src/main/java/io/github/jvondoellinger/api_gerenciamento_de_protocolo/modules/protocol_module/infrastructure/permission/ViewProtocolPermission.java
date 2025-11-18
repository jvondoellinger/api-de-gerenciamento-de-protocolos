package io.github.jvondoellinger.api_gerenciamento_de_protocolo.modules.protocol_module.infrastructure.permission;

public record ViewProtocolPermission() implements Permission {
      @Override
      public String getName() {
            return Permissions.VIEW_PROTOCOL.name();
      }
}
