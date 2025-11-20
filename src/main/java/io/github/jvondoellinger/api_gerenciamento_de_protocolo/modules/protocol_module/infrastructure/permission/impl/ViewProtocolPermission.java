package io.github.jvondoellinger.api_gerenciamento_de_protocolo.modules.protocol_module.infrastructure.permission.impl;

import io.github.jvondoellinger.api_gerenciamento_de_protocolo.modules.protocol_module.infrastructure.permission.Permission;
import io.github.jvondoellinger.api_gerenciamento_de_protocolo.modules.protocol_module.infrastructure.permission.enums.PublishPermissions;

public record ViewProtocolPermission() implements Permission {
      @Override
      public String getName() {
            return PublishPermissions.VIEW_PROTOCOL.name();
      }
}
