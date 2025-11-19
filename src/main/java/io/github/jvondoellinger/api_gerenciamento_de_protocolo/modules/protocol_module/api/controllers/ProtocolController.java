package io.github.jvondoellinger.api_gerenciamento_de_protocolo.modules.protocol_module.api.controllers;

import io.github.jvondoellinger.api_gerenciamento_de_protocolo.modules.protocol_module.infrastructure.subs.resolvers.DomainDynamicPublisher;
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
    private final ProtocolQueryFacade facade;
    private final DomainDynamicPublisher resolver;
    public ProtocolController(ProtocolQueryFacade facade, DomainDynamicPublisher resolver) {
        this.resolver = resolver;
        this.facade = facade;
    }

    @PostMapping
    public Mono<ResponseEntity<Void>> create(@RequestBody CreateProtocolCommand command) {
        var event = CreateProtocolEventMapper.map(command);
        return resolver
                .publish(event)
                .map(x -> ResponseEntity.accepted().build());
    }

    @GetMapping
    public ResponseEntity<Flux<Protocolo>> get() {
        var data = facade.query(PaginationFilter.of(0, 10));
        return ResponseEntity.ok(data);
    }
}
