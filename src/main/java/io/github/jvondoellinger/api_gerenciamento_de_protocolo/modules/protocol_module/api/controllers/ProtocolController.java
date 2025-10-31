package io.github.jvondoellinger.api_gerenciamento_de_protocolo.modules.protocol_module.api.controllers;

import io.github.jvondoellinger.api_gerenciamento_de_protocolo.modules.protocol_module.Infrastructure.subs.resolvers.DomainPublisherResolver;
import io.github.jvondoellinger.api_gerenciamento_de_protocolo.modules.protocol_module.application.commands.CreateProtocolCommand;
import io.github.jvondoellinger.api_gerenciamento_de_protocolo.modules.protocol_module.application.mappers.CreateProtocolEventMapper;
import io.github.jvondoellinger.api_gerenciamento_de_protocolo.modules.protocol_module.application.query.ProtocolQueryFacade;
import io.github.jvondoellinger.api_gerenciamento_de_protocolo.modules.protocol_module.domain.contracts.persistence.filters.PaginationFilter;
import io.github.jvondoellinger.api_gerenciamento_de_protocolo.modules.protocol_module.domain.Protocolo;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/protocolo")
public class ProtocolController {
    //private final DomainEventPublisher<CreateProtocolEvent> publisher;
    private final ProtocolQueryFacade facade;
    private final DomainPublisherResolver resolver;
    public ProtocolController(ProtocolQueryFacade facade, DomainPublisherResolver resolver) {
        this.resolver = resolver;
        this.facade = facade;
    }

    @PostMapping
    public Mono<ResponseEntity<Void>> create(@RequestBody CreateProtocolCommand command) {
        var event = CreateProtocolEventMapper.map(command);
        return resolver
                .dynamicPublish(event)
                .map(x -> ResponseEntity.accepted().build());
    }

    @GetMapping
    public ResponseEntity<Flux<Protocolo>> get() {
        var data = facade.query(new PaginationFilter(0, 10));
        return ResponseEntity.ok(data);
    }
}
