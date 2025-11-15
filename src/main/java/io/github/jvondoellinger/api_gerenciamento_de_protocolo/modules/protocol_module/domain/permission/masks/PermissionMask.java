package io.github.jvondoellinger.api_gerenciamento_de_protocolo.modules.protocol_module.domain.permission.masks;

import io.github.jvondoellinger.api_gerenciamento_de_protocolo.modules.protocol_module.domain.Protocolo;
import io.github.jvondoellinger.api_gerenciamento_de_protocolo.modules.protocol_module.domain.permission.Permission;

public interface PermissionMask {
      <TPermission extends Permission> Protocolo mask(Protocolo protocol, TPermission permission);
}