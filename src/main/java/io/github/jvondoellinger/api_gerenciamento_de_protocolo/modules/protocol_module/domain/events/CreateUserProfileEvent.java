package io.github.jvondoellinger.api_gerenciamento_de_protocolo.modules.protocol_module.domain.events;

import io.github.jvondoellinger.api_gerenciamento_de_protocolo.modules.protocol_module.domain.UserProfile;

public class CreateUserProfileEvent extends UserActivityEvent {
      public CreateUserProfileEvent(UserProfile profile) {
            super(profile.getUserId()); // Send a userId to event
            this.profile = profile;
      }

      private final UserProfile profile;

      public UserProfile getProfile() {
            return profile;
      }
}
