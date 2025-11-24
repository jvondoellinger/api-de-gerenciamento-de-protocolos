package io.github.jvondoellinger.api_gerenciamento_de_protocolo.modules.protocol_module.api.controllers;

import io.github.jvondoellinger.api_gerenciamento_de_protocolo.modules.protocol_module.application.requests.ProtocolQueryByProtocolNumberRequest;
import io.github.jvondoellinger.api_gerenciamento_de_protocolo.modules.protocol_module.infrastructure.subs.resolvers.DomainDynamicPublisher;
import io.github.jvondoellinger.api_gerenciamento_de_protocolo.modules.protocol_module.application.commands.CreateProtocolCommand;
import io.github.jvondoellinger.api_gerenciamento_de_protocolo.modules.protocol_module.application.mappers.CreateProtocolEventMapper;
import io.github.jvondoellinger.api_gerenciamento_de_protocolo.modules.protocol_module.application.facade.ProtocolQueryFacadeImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/protocol")
public class ProtocolController {
    private final ProtocolQueryFacadeImpl facade;
    private final DomainDynamicPublisher resolver;
    public ProtocolController(ProtocolQueryFacadeImpl facade, DomainDynamicPublisher resolver) {
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
    public Mono<ResponseEntity<?>> get(@RequestParam(required = true) String protocolNumber,
                                       @RequestParam(required = true) String userId) {
        var request = new ProtocolQueryByProtocolNumberRequest(protocolNumber, userId);

        var data = facade.query(request);

        return data.map(ResponseEntity::ok);
    }
}
