package io.github.jvondoellinger.api_gerenciamento_de_protocolo.modules.protocol_module.infrastructure;

import io.github.jvondoellinger.api_gerenciamento_de_protocolo.modules.protocol_module.infrastructure.permission.Permission;
import io.github.jvondoellinger.api_gerenciamento_de_protocolo.modules.protocol_module.domain.valueObjects.DomainId;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

public class UserProtocolPermissionRelationship {
      private DomainId id;

      private String userId;
      private List<Permission> permissions;

      private LocalDateTime addedAt;
      private LocalDateTime modifiedAt;

      public UserProtocolPermissionRelationship(DomainId id,
                                                String userId,
                                                List<Permission> permissions,
                                                LocalDateTime addedAt,
                                                LocalDateTime modifiedAt) {
            this.id = id;
            this.userId = userId;
            this.permissions = permissions;
            this.addedAt = addedAt;
            this.modifiedAt = modifiedAt;
      }

      private UserProtocolPermissionRelationship(String userId, List<Permission> permissions) {
            this.userId = userId;
            this.permissions = permissions;
      }

      public static UserProtocolPermissionRelationship create(String userId, List<Permission> permission) {
            return new UserProtocolPermissionRelationship(userId, permission);
      }

      public void addPermission(Permission permission) {
            if (!permissions.contains(permission)) {
                permissions.add(permission);
            }
      }
      public void removePermission(Permission permission) {
            var stored = permissions
                    .stream()
                    .filter(x -> x.getName().equalsIgnoreCase(permission.getName()))
                    .findFirst()
                    .orElse(null);
            if (!Objects.isNull(stored)) {
                  permissions.remove(stored);
            }
      }

      public void overwritePermissions(List<Permission> permissions) {
            this.permissions = permissions;
      }

      public DomainId getId() {
            return id;
      }

      public String getUserId() {
            return userId;
      }

      public List<Permission> getPermissions() {
            return permissions;
      }

      public LocalDateTime getAddedAt() {
            return addedAt;
      }

      public LocalDateTime getModifiedAt() {
            return modifiedAt;
      }


}
