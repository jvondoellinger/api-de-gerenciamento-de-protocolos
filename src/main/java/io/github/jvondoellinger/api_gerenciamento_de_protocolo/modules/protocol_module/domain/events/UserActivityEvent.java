package io.github.jvondoellinger.api_gerenciamento_de_protocolo.modules.protocol_module.domain.events;

import io.github.jvondoellinger.api_gerenciamento_de_protocolo.modules.protocol_module.domain.valueObjects.DomainId;

public abstract class UserActivityEvent extends DomainEvent {

      public UserActivityEvent(DomainId userId) {
            this.userId = userId;
      }

      private final DomainId userId;

      public final DomainId getUserId() {
            return userId;
      }
}
