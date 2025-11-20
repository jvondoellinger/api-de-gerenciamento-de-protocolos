package io.github.jvondoellinger.api_gerenciamento_de_protocolo.modules.protocol_module.infrastructure.permission.checker;

import io.github.jvondoellinger.api_gerenciamento_de_protocolo.modules.protocol_module.domain.valueObjects.DomainId;
import io.github.jvondoellinger.api_gerenciamento_de_protocolo.modules.protocol_module.infrastructure.permission.enums.ReadPermissions;
import reactor.core.publisher.Mono;

public interface ReadPermissionChecker {
      Mono<Boolean> hasPermission(ReadPermissions permission, DomainId userId);
}
