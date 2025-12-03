package io.github.jvondoellinger.api_gerenciamento_de_protocolo.modules.protocol_module.domain.profiles;

import io.github.jvondoellinger.api_gerenciamento_de_protocolo.modules.protocol_module.domain.exception.DomainException;
import io.github.jvondoellinger.api_gerenciamento_de_protocolo.modules.protocol_module.domain.profiles.permissions.Permission;
import io.github.jvondoellinger.api_gerenciamento_de_protocolo.modules.protocol_module.domain.profiles.permissions.read.protocol.*;
import io.github.jvondoellinger.api_gerenciamento_de_protocolo.modules.protocol_module.domain.profiles.permissions.read.queue.ReadQueuePermission;
import io.github.jvondoellinger.api_gerenciamento_de_protocolo.modules.protocol_module.domain.profiles.permissions.read.queue.ReadSensitiveQueuePermission;
import io.github.jvondoellinger.api_gerenciamento_de_protocolo.modules.protocol_module.domain.profiles.permissions.write.protocol.*;
import io.github.jvondoellinger.api_gerenciamento_de_protocolo.modules.protocol_module.domain.profiles.permissions.write.queue.CreateQueuePermission;
import io.github.jvondoellinger.api_gerenciamento_de_protocolo.modules.protocol_module.domain.profiles.permissions.write.queue.DeleteQueuePermission;
import io.github.jvondoellinger.api_gerenciamento_de_protocolo.modules.protocol_module.domain.profiles.permissions.write.queue.UpdateQueuePermission;

import java.util.List;

public class PermissionFactory {
      private static final List<Permission> instances = List.of(
              new ReadAttachmentsProtocolPermission(),
              new ReadImagesProtocolPermission(),
              new ReadInvisibleProtocolPermission(),
              new ReadMetadataProtocolPermission(),
              new ReadProtocolPermission(),
              new ReadSensitiveProtocolPermission(),
              new WatchVideosProtocolPermission(),
              new ReadQueuePermission(),
              new ReadSensitiveQueuePermission(),
              new CreateProtocolPermission(),
              new DelegateProtocolPermission(),
              new DelegateSealedProtocolPermission("DYNAMIC"),
              new InteractProtocolPermission(),
              new ModifyInteractionVisibilityPermission(),
              new ModifyProtocolVisibilityPermission(),
              new CreateQueuePermission(),
              new DeleteQueuePermission(),
              new UpdateQueuePermission()
      );

      private PermissionFactory() {}

      public static Permission getInstance(String permission) {
            return instances
                    .stream()
                    .filter(x -> x.getName().equalsIgnoreCase(permission))
                    .findFirst()
                    .orElseThrow(() -> new DomainException("Don't have any permission: %s".formatted(permission)));
      }


}
