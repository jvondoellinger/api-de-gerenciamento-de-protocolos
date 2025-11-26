package io.github.jvondoellinger.api_gerenciamento_de_protocolo.modules.protocol_module.api.controllers;

import io.github.jvondoellinger.api_gerenciamento_de_protocolo.modules.protocol_module.application.commands.CreateQueueCommand;
import io.github.jvondoellinger.api_gerenciamento_de_protocolo.modules.protocol_module.application.mappers.CreateQueueEventMapper;
import io.github.jvondoellinger.api_gerenciamento_de_protocolo.modules.protocol_module.domain.Queue;
import io.github.jvondoellinger.api_gerenciamento_de_protocolo.modules.protocol_module.domain.contracts.persistence.QueuesReadRepository;
import io.github.jvondoellinger.api_gerenciamento_de_protocolo.modules.protocol_module.domain.contracts.persistence.filters.PaginationFilter;
import io.github.jvondoellinger.api_gerenciamento_de_protocolo.modules.protocol_module.infrastructure.events.subs.resolvers.DomainDynamicPublisher;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/queues")
public class QueuesController {
      private final QueuesReadRepository readRepository;
      private final DomainDynamicPublisher resolver;

      public QueuesController(QueuesReadRepository readRepository, DomainDynamicPublisher resolver) {
            this.readRepository = readRepository;
            this.resolver = resolver;
      }     

      @GetMapping
      public Flux<Queue> get() {
            return readRepository.query(PaginationFilter.of(0, 100));
      }

      @PostMapping
      public Mono<Void> create(@RequestBody CreateQueueCommand command) {
            var event = CreateQueueEventMapper.map(command);
            return resolver
                    .publish(event)
                    .doOnError(x -> System.out.println(x.getMessage()));
      }
}
