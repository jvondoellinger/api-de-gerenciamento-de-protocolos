package io.github.jvondoellinger.api_gerenciamento_de_protocolo.modules.protocol_module.infrastructure.database.relationship;

import io.github.jvondoellinger.api_gerenciamento_de_protocolo.modules.protocol_module.infrastructure.permission.Permission;
import io.github.jvondoellinger.api_gerenciamento_de_protocolo.modules.protocol_module.domain.valueObjects.DomainId;
import io.github.jvondoellinger.api_gerenciamento_de_protocolo.modules.protocol_module.infrastructure.UserProtocolPermissionRelationship;
import io.github.jvondoellinger.api_gerenciamento_de_protocolo.modules.protocol_module.infrastructure.database.contracts.UserProtocolRelationshipRepository;
import io.github.jvondoellinger.api_gerenciamento_de_protocolo.modules.protocol_module.infrastructure.database.data.UserProtocolRelationshipCassandraRepository;
import io.github.jvondoellinger.api_gerenciamento_de_protocolo.modules.protocol_module.infrastructure.database.entity.UserProtocolPermissionRelationshipEntity;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

@Service
public class UserProtocolRelationshipRepositoryImpl implements UserProtocolRelationshipRepository {
      private final UserProtocolRelationshipCassandraRepository cassandraRepository;

      public UserProtocolRelationshipRepositoryImpl(UserProtocolRelationshipCassandraRepository cassandraRepository) {
            this.cassandraRepository = cassandraRepository;
      }

      @Override
      public Mono<Void> createRelationship(UserProtocolPermissionRelationship relationship) {
            var entity = UserProtocolPermissionRelationshipEntity.create(relationship);
            return cassandraRepository
                    .save(entity)
                    .then();
      }

      @Override
      public Mono<Void> addPermission(DomainId userId, Permission permission) {
            return cassandraRepository
                    .findByUserId(userId.getValue().toString())
                    .flatMap(x -> {
                        var relationship = x.parse();
                        relationship.addPermission(permission);

                        var entity = UserProtocolPermissionRelationshipEntity.create(relationship);
                        return cassandraRepository.save(entity);
                    })
                    .then();
      }

      @Override
      public Mono<Void> removePermission(DomainId userId, Permission permission) {
            return cassandraRepository
                    .findByUserId(userId.getValue().toString())
                    .flatMap(x -> {
                          var relationship = x.parse();
                          relationship.removePermission(permission);

                          var entity = UserProtocolPermissionRelationshipEntity.create(relationship);
                          return cassandraRepository.save(entity);
                    })
                    .then();
      }

      @Override
      public Mono<Void> editPermissions(DomainId userId, List<Permission> permission) {
            return cassandraRepository
                    .findByUserId(userId.getValue().toString())
                    .flatMap(x -> {
                          var relationship = x.parse();
                          relationship.overwritePermissions(permission); // Overwrite permissions

                          var entity = UserProtocolPermissionRelationshipEntity.create(relationship);
                          return cassandraRepository.save(entity);
                    })
                    .then();
      }

      @Override
      public Mono<Void> deleteRelationship(DomainId userId) {
            return cassandraRepository
                    .findByUserId(userId.getValue().toString())
                    .flatMap(cassandraRepository::delete)
                    .then();
      }

      @Override
      public Flux<UserProtocolPermissionRelationship> findRelationships(DomainId userId) {
            return cassandraRepository
                    .findByUserId(userId.getValue().toString())
                    .map(UserProtocolPermissionRelationshipEntity::parse);
      }
}
