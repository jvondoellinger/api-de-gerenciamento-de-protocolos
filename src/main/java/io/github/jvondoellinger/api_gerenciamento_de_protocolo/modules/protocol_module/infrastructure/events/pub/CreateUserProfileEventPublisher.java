package io.github.jvondoellinger.api_gerenciamento_de_protocolo.modules.protocol_module.infrastructure.events.pub;

import io.github.jvondoellinger.api_gerenciamento_de_protocolo.modules.protocol_module.domain.events.pub.EventPublisher;
import io.github.jvondoellinger.api_gerenciamento_de_protocolo.modules.protocol_module.domain.events.CreateUserProfileEvent;
import io.github.jvondoellinger.api_gerenciamento_de_protocolo.modules.protocol_module.infrastructure.events.subs.CreateUserProfileEventHandler;
import io.github.jvondoellinger.api_gerenciamento_de_protocolo.modules.protocol_module.infrastructure.events.validators.proxy.EventValidatorProxy;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public class CreateUserProfileEventPublisher implements EventPublisher<CreateUserProfileEvent> {
      private final CreateUserProfileEventHandler handler;

      public CreateUserProfileEventPublisher(CreateUserProfileEventHandler handler) {
            this.handler = handler;
      }

      @Override
      @EventValidatorProxy
      public Mono<Void> publish(CreateUserProfileEvent event) {
            return handler.handle(event);
      }

      @Override
      public Class<CreateUserProfileEvent> eventType() {
            return CreateUserProfileEvent.class;
      }
}
