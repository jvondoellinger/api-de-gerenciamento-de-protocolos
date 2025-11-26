package io.github.jvondoellinger.api_gerenciamento_de_protocolo.modules.protocol_module.api.controllers;

import io.github.jvondoellinger.api_gerenciamento_de_protocolo.modules.protocol_module.application.commands.CreateUserProfileCommand;
import io.github.jvondoellinger.api_gerenciamento_de_protocolo.modules.protocol_module.application.mappers.CreateUserProfileEventMapper;
import io.github.jvondoellinger.api_gerenciamento_de_protocolo.modules.protocol_module.infrastructure.events.subs.resolvers.DomainDynamicPublisher;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/permissions")
public class PermissionController {
      private DomainDynamicPublisher publisher;

      public PermissionController(DomainDynamicPublisher publisher) {
            this.publisher = publisher;
      }

      @PostMapping
      public Mono<Void> createRelationship(@RequestBody CreateUserProfileCommand command) {
            var event = CreateUserProfileEventMapper.map(command);
            return publisher.publish(event);
      }
}
