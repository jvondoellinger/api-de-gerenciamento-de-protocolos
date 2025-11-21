package io.github.jvondoellinger.api_gerenciamento_de_protocolo.modules.protocol_module.infrastructure.permission.impl;

import io.github.jvondoellinger.api_gerenciamento_de_protocolo.modules.protocol_module.infrastructure.permission.Permission;
import io.github.jvondoellinger.api_gerenciamento_de_protocolo.modules.protocol_module.infrastructure.permission.enums.ReadPermissions;

public record ReadProtocolPermission() implements Permission {
      @Override
      public String getName() {
            return ReadPermissions.READ.name();
      }
}
