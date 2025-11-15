package io.github.jvondoellinger.api_gerenciamento_de_protocolo.modules.protocol_module.infrastructure.database.relationship;

import io.github.jvondoellinger.api_gerenciamento_de_protocolo.modules.protocol_module.domain.permission.Permission;
import io.github.jvondoellinger.api_gerenciamento_de_protocolo.modules.protocol_module.domain.valueObjects.DomainId;
import reactor.core.publisher.Mono;

import java.util.List;

public interface UserProtocolRelationshipRepository {
       /**
        * Cria uma relação de One To Many, ou seja, um ID de usuario para varias permissões
       **/
      Mono<Void> createRelationship(DomainId userId, List<Permission> permission);

      Mono<Void> addPermission(DomainId userId, Permission permission);
      Mono<Void> removePermission(DomainId userId, Permission permission);
      Mono<Void> editPermissions(DomainId userId, List<Permission> permission);
      
      Mono<Void> deleteRelationship(DomainId userId);
}
