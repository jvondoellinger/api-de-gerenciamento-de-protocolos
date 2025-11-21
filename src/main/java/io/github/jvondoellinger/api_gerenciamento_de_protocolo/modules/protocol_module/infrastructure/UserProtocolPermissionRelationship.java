package io.github.jvondoellinger.api_gerenciamento_de_protocolo.modules.protocol_module.infrastructure;

import io.github.jvondoellinger.api_gerenciamento_de_protocolo.modules.protocol_module.infrastructure.permission.Permission;
import io.github.jvondoellinger.api_gerenciamento_de_protocolo.modules.protocol_module.domain.valueObjects.DomainId;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

public class UserProtocolPermissionRelationship {
      private DomainId id;

      private DomainId userId;
      private List<Permission> permissions;

      private LocalDateTime addedAt;
      private LocalDateTime modifiedAt;

      public UserProtocolPermissionRelationship(DomainId id,
                                                DomainId userId,
                                                List<Permission> permissions,
                                                LocalDateTime addedAt,
                                                LocalDateTime modifiedAt) {
            this.id = id;
            this.userId = userId;
            this.permissions = permissions;
            this.addedAt = addedAt;
            this.modifiedAt = modifiedAt;
      }

      private UserProtocolPermissionRelationship(DomainId userId, List<Permission> permissions) {
            this.id = new DomainId();
            this.userId = userId;
            this.permissions = permissions;
            this.addedAt = LocalDateTime.now();
            this.modifiedAt = LocalDateTime.now();
      }

      public static UserProtocolPermissionRelationship create(DomainId userId, List<Permission> permission) {
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

      public boolean containsPermission(Permission permission) {
            var equal = permissions
                    .stream()
                    .filter(x -> x.getName().equalsIgnoreCase(permission.getName()))
                    .findFirst()
                    .orElse(null);

            return equal != null;
      }

      public DomainId getId() {
            return id;
      }

      public DomainId getUserId() {
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
