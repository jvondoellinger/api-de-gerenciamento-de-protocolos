package io.github.jvondoellinger.api_gerenciamento_de_protocolo.modules.protocol_module.infrastructure.permission.impl;

import io.github.jvondoellinger.api_gerenciamento_de_protocolo.modules.protocol_module.infrastructure.permission.Permission;
import io.github.jvondoellinger.api_gerenciamento_de_protocolo.modules.protocol_module.infrastructure.permission.enums.PublishPermissions;

public record CreateProtocolPermission() implements Permission {
      @Override
      public String getName() {
            return PublishPermissions.CREATE_PROTOCOL.name();
      }
}
