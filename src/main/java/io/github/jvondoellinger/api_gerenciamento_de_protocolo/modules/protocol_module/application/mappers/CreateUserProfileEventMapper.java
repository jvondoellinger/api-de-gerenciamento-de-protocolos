package io.github.jvondoellinger.api_gerenciamento_de_protocolo.modules.protocol_module.application.mappers;

import io.github.jvondoellinger.api_gerenciamento_de_protocolo.modules.protocol_module.application.commands.CreateUserProfileCommand;
import io.github.jvondoellinger.api_gerenciamento_de_protocolo.modules.protocol_module.domain.UserProfile;
import io.github.jvondoellinger.api_gerenciamento_de_protocolo.modules.protocol_module.domain.profiles.Profile;
import io.github.jvondoellinger.api_gerenciamento_de_protocolo.modules.protocol_module.domain.profiles.permissions.Permissions;
import io.github.jvondoellinger.api_gerenciamento_de_protocolo.modules.protocol_module.domain.valueObjects.DomainId;
import io.github.jvondoellinger.api_gerenciamento_de_protocolo.modules.protocol_module.domain.profiles.PermissionFactory;
import io.github.jvondoellinger.api_gerenciamento_de_protocolo.modules.protocol_module.domain.events.CreateUserProfileEvent;

public class CreateUserProfileEventMapper {
      private CreateUserProfileEventMapper() {}

      public static CreateUserProfileEvent map(CreateUserProfileCommand command) {
            // Parsing fields
            var userId = DomainId.from(command.userId());

            var permissionsList = command
                    .permissions()
                    .stream()
                    .map(PermissionFactory::getInstance)
                    .toList();

            var permissions = Permissions.create(permissionsList);

            // Creating profile
            var profile = new Profile(command.profileName(), permissions);

            // Created user profile
            var userProfile = new UserProfile(userId, profile);

            // Returning event
            return new CreateUserProfileEvent(userProfile);
      }
}
