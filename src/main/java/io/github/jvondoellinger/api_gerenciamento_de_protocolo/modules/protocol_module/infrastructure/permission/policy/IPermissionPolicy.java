package io.github.jvondoellinger.api_gerenciamento_de_protocolo.modules.protocol_module.infrastructure.permission.policy;

import io.github.jvondoellinger.api_gerenciamento_de_protocolo.modules.protocol_module.domain.valueObjects.DomainId;
import reactor.core.publisher.Mono;

public interface IPermissionPolicy<TPermission> {
      Mono<Boolean> hasPermission(TPermission permission, DomainId userId);
}
