package io.github.jvondoellinger.api_gerenciamento_de_protocolo.modules.protocol_module.api.controllers;

import io.github.jvondoellinger.api_gerenciamento_de_protocolo.modules.protocol_module.infrastructure.subs.resolvers.DomainPublisherResolver;
import io.github.jvondoellinger.api_gerenciamento_de_protocolo.modules.protocol_module.application.commands.AddInteractionProtocolCommand;
import io.github.jvondoellinger.api_gerenciamento_de_protocolo.modules.protocol_module.application.mappers.AddInteractionProtocolMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/interaction")
public class InteractionController {
    private final DomainPublisherResolver resolver;

    public InteractionController(DomainPublisherResolver resolver) {
        this.resolver = resolver;
    }

    @PostMapping
    public Mono<ResponseEntity<Void>> interact(@RequestBody AddInteractionProtocolCommand command) {
        var event = AddInteractionProtocolMapper.map(command);
        return resolver
                .dynamicPublish(event)
                .map(x -> ResponseEntity.accepted().build());
    }
}
