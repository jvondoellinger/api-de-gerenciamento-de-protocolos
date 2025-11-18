package io.github.jvondoellinger.api_gerenciamento_de_protocolo.modules.protocol_module.infrastructure.database.entity;

import io.github.jvondoellinger.api_gerenciamento_de_protocolo.modules.protocol_module.infrastructure.permission.Permission;
import io.github.jvondoellinger.api_gerenciamento_de_protocolo.modules.protocol_module.infrastructure.permission.PermissionFactory;
import io.github.jvondoellinger.api_gerenciamento_de_protocolo.modules.protocol_module.domain.valueObjects.DomainId;
import io.github.jvondoellinger.api_gerenciamento_de_protocolo.modules.protocol_module.infrastructure.UserProtocolPermissionRelationship;
import org.springframework.data.annotation.PersistenceCreator;
import org.springframework.data.cassandra.core.cql.Ordering;
import org.springframework.data.cassandra.core.cql.PrimaryKeyType;
import org.springframework.data.cassandra.core.mapping.PrimaryKeyColumn;
import org.springframework.data.cassandra.core.mapping.Table;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Table("user_protocol_permission_relationship")
public class UserProtocolPermissionRelationshipEntity implements ObjectEntity<UserProtocolPermissionRelationship> {
      @PersistenceCreator
      public UserProtocolPermissionRelationshipEntity(UUID id,
                                                      String userId,
                                                      List<String> permissions,
                                                      LocalDateTime addedAt,
                                                      LocalDateTime modifiedAt,
                                                      boolean isDeleted) {
            this.id = id;
            this.userId = userId;
            this.permissions = permissions;
            this.addedAt = addedAt;
            this.modifiedAt = modifiedAt;
            this.isDeleted = isDeleted;
      }

      @PrimaryKeyColumn(name = "id", type = PrimaryKeyType.CLUSTERED, ordering = Ordering.DESCENDING)
      private UUID id;

      @PrimaryKeyColumn(name = "userId", type = PrimaryKeyType.PARTITIONED)

      private String userId;
      private List<String> permissions;

      private LocalDateTime addedAt;
      private LocalDateTime modifiedAt;
      private boolean isDeleted;
      /**
            *@apiNote Cria uma entidade de relacionamento entre Usuario e Protocolo. OBS: isDeleted SEMPRE vem false
       */
      public static UserProtocolPermissionRelationshipEntity create(UserProtocolPermissionRelationship relationship) {
            var permissions = relationship
                    .getPermissions()
                    .stream()
                    .map(Permission::getName)
                    .toList();
            var entity = new UserProtocolPermissionRelationshipEntity(
                    relationship.getId().getValue(),
                    relationship.getUserId(),
                    permissions,
                    relationship.getAddedAt(),
                    relationship.getModifiedAt(),
                    false // Se está sendo criada agora, não há como estar deletada
            );
            return entity;
      }

      @Override
      public UserProtocolPermissionRelationship parse() {
            var permissionObjs = permissions
                     .stream()
                     .map(PermissionFactory::getInstance)
                     .toList();
            return new UserProtocolPermissionRelationship(
                    DomainId.from(id),
                    userId,
                    permissionObjs,
                    addedAt,
                    modifiedAt
            );
      }
}
