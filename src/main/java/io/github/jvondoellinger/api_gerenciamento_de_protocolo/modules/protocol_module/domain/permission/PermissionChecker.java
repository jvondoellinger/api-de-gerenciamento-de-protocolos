package io.github.jvondoellinger.api_gerenciamento_de_protocolo.modules.protocol_module.domain.permission;

import io.github.jvondoellinger.api_gerenciamento_de_protocolo.modules.protocol_module.domain.Protocolo;
import io.github.jvondoellinger.api_gerenciamento_de_protocolo.modules.protocol_module.domain.valueObjects.DomainId;
import reactor.core.publisher.Mono;

public interface PermissionChecker {

      /**
       * @param protocolo Protocolo que será utilizado para verificar se há permissão
       * @param permission Permissão à ser verificada
       * @param userId Identificador do usuario para validar se há ou não permissão
       * @return Retorna o protocolo ajustada à permissão do usuario.
      **/

      Mono<Protocolo> hasPermission(Protocolo protocolo, Permissions permission, DomainId userId);
}
