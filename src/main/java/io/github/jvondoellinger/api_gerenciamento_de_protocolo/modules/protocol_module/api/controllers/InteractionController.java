package io.github.jvondoellinger.api_gerenciamento_de_protocolo.modules.protocol_module.api.controllers;

import io.github.jvondoellinger.api_gerenciamento_de_protocolo.modules.protocol_module.infrastructure.events.subs.resolvers.DomainDynamicPublisher;
import io.github.jvondoellinger.api_gerenciamento_de_protocolo.modules.protocol_module.application.commands.AddInteractionProtocolCommand;
import io.github.jvondoellinger.api_gerenciamento_de_protocolo.modules.protocol_module.application.mappers.AddInteractionProtocolEventMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/interaction")
public class InteractionController {
    private final DomainDynamicPublisher resolver;

    public InteractionController(DomainDynamicPublisher resolver) {
        this.resolver = resolver;
    }

    @PostMapping
    public Mono<ResponseEntity<Void>> interact(@RequestBody AddInteractionProtocolCommand command) {
        var event = AddInteractionProtocolEventMapper.map(command);
        return resolver
                .publish(event)
                .map(x -> ResponseEntity.accepted().build());
    }
}
