package io.github.jvondoellinger.api_gerenciamento_de_protocolo.modules.protocol_module.application.mounter;

import io.github.jvondoellinger.api_gerenciamento_de_protocolo.modules.protocol_module.domain.Interaction;
import io.github.jvondoellinger.api_gerenciamento_de_protocolo.modules.protocol_module.domain.InteractionHistory;
import io.github.jvondoellinger.api_gerenciamento_de_protocolo.modules.protocol_module.domain.Protocolo;
import io.github.jvondoellinger.api_gerenciamento_de_protocolo.modules.protocol_module.domain.Queue;
import io.github.jvondoellinger.api_gerenciamento_de_protocolo.modules.protocol_module.domain.contracts.persistence.InteractionsReadRepository;
import io.github.jvondoellinger.api_gerenciamento_de_protocolo.modules.protocol_module.domain.contracts.persistence.ProtocolReadRepository;
import io.github.jvondoellinger.api_gerenciamento_de_protocolo.modules.protocol_module.domain.contracts.persistence.QueuesReadRepository;
import io.github.jvondoellinger.api_gerenciamento_de_protocolo.modules.protocol_module.domain.contracts.persistence.filters.ProtocolNumberFilter;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.List;

@Service
public class ProtocolMounterHelper {
      public Mono<Protocolo> mount(Mono<Protocolo> protocoloMono, Mono<Queue> queueMono, Mono<List<Interaction>> interactionsMono) {
            return Mono.zip(protocoloMono, queueMono, interactionsMono)
                    .map(tuple ->
                            mount(tuple.getT1(), tuple.getT2(), tuple.getT3()));
      }

      public Protocolo mount(Protocolo incomplete, Queue queue, List<Interaction> interactions) {
            return new Protocolo(
                    incomplete.getId(),
                    incomplete.getProtocolNumber(),
                    queue,
                    incomplete.getDescription(),
                    incomplete.getCreatedBy(),
                    incomplete.getState(),
                    InteractionHistory.from(interactions),
                    incomplete.getAttachments(),
                    incomplete.getCreatedAt(),
                    incomplete.getUpdatedAt()
            );
      }
}
