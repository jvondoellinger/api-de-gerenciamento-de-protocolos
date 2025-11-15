package io.github.jvondoellinger.api_gerenciamento_de_protocolo.modules.protocol_module.infrastructure.permission;

import io.github.jvondoellinger.api_gerenciamento_de_protocolo.modules.protocol_module.domain.Protocolo;
import io.github.jvondoellinger.api_gerenciamento_de_protocolo.modules.protocol_module.domain.annotiation.ImplementsAfter;
import io.github.jvondoellinger.api_gerenciamento_de_protocolo.modules.protocol_module.domain.permission.PermissionChecker;
import io.github.jvondoellinger.api_gerenciamento_de_protocolo.modules.protocol_module.domain.permission.Permissions;
import io.github.jvondoellinger.api_gerenciamento_de_protocolo.modules.protocol_module.domain.valueObjects.DomainId;
import reactor.core.publisher.Mono;

@ImplementsAfter
public class PermissionCheckerImpl implements PermissionChecker {

      @Override
      public Mono<Protocolo> hasPermission(Protocolo protocolo, Permissions permission, DomainId userId) {

            // Para verificar se há permissão, na teoria, teria que bater na API de usuarios e
            // verificar se ele teria a permissão necssaria, ou seja, de um analista
            // supervisor, etc...

            return Mono.just(protocolo);
      }
}
