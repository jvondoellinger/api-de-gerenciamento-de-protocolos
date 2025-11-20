package io.github.jvondoellinger.api_gerenciamento_de_protocolo.modules.protocol_module.infrastructure.permission.checker;

import io.github.jvondoellinger.api_gerenciamento_de_protocolo.modules.protocol_module.domain.valueObjects.DomainId;
import io.github.jvondoellinger.api_gerenciamento_de_protocolo.modules.protocol_module.infrastructure.permission.enums.PublishPermissions;
import reactor.core.publisher.Mono;

public interface PublishPermissionChecker {

      /**
       * @param permission Permissão à ser verificada
       * @param userId Identificador do usuario para validar se há ou não permissão
       * @return Retorna o protocolo ajustada à permissão do usuario.
      **/

      Mono<Boolean> hasPermission(PublishPermissions permission, DomainId userId);
}
