package io.github.jvondoellinger.api_gerenciamento_de_protocolo.modules.protocol_module.infrastructure.permission.checker;

import io.github.jvondoellinger.api_gerenciamento_de_protocolo.modules.protocol_module.domain.profiles.permissions.Permissions;
import io.github.jvondoellinger.api_gerenciamento_de_protocolo.modules.protocol_module.domain.valueObjects.DomainId;
import io.github.jvondoellinger.api_gerenciamento_de_protocolo.modules.protocol_module.domain.profiles.permissions.Permission;
import io.github.jvondoellinger.api_gerenciamento_de_protocolo.modules.protocol_module.infrastructure.permission.exception.MissingPermissionException;
import reactor.core.publisher.Mono;

public interface PermissionChecker {

      /**
       * @param permission Permissão à ser verificada
       * @param userId Identificador do usuario para validar se há ou não permissão
       * @return Retorna o protocol ajustada à permissão do usuario.
      **/

      Mono<Boolean> hasPermission(Permission permission, DomainId userId);
      Mono<Boolean> hasPermission(Permissions permissions, DomainId userId);

      Mono<Void> permittedOrThrow(Permission permission, DomainId userId) throws MissingPermissionException;
      Mono<Void> permittedOrThrow(Permissions permissions, DomainId userId) throws MissingPermissionException;
}
