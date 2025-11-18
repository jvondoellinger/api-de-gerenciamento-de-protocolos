package io.github.jvondoellinger.api_gerenciamento_de_protocolo.modules.protocol_module.infrastructure.database.contracts;

import io.github.jvondoellinger.api_gerenciamento_de_protocolo.modules.protocol_module.infrastructure.permission.Permission;
import io.github.jvondoellinger.api_gerenciamento_de_protocolo.modules.protocol_module.domain.valueObjects.DomainId;
import io.github.jvondoellinger.api_gerenciamento_de_protocolo.modules.protocol_module.infrastructure.UserProtocolPermissionRelationship;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

public interface UserProtocolRelationshipRepository {
       /**
        * Cria uma relação de One To Many, ou seja, um ID de usuario para varias permissões
       */
      Mono<Void> createRelationship(UserProtocolPermissionRelationship relationship);

      Mono<Void> addPermission(DomainId userId, Permission permission);
      Mono<Void> removePermission(DomainId userId, Permission permission);
      Mono<Void> editPermissions(DomainId userId, List<Permission> permission);

      Mono<Void> deleteRelationship(DomainId userId);

      Flux<UserProtocolPermissionRelationship> findRelationships(DomainId userId);
}
