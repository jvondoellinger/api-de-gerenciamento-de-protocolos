package io.github.jvondoellinger.api_gerenciamento_de_protocolo.modules.protocol_module.application.mounter;

import io.github.jvondoellinger.api_gerenciamento_de_protocolo.modules.protocol_module.domain.Interaction;
import io.github.jvondoellinger.api_gerenciamento_de_protocolo.modules.protocol_module.domain.InteractionHistory;
import io.github.jvondoellinger.api_gerenciamento_de_protocolo.modules.protocol_module.domain.Protocol;
import io.github.jvondoellinger.api_gerenciamento_de_protocolo.modules.protocol_module.domain.Queue;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;
import reactor.util.function.Tuple2;

import java.util.List;

@Service
public class ProtocolMounterHelper {
      public Mono<Protocol> mount(Mono<Protocol> protocoloMono, Mono<Queue> queueMono) {
            return Mono.zip(protocoloMono, queueMono).map(this::mount);
      }

	 private Protocol mount(Tuple2<Protocol, Queue> tuple) {
		 return mount(tuple.getT1(), tuple.getT2());
	 }

      public Protocol mount(Protocol incomplete, Queue queue) {
            return new Protocol(
                    incomplete.getId(),
                    incomplete.getProtocolNumber(),
                    queue,
                    incomplete.getDescription(),
                    incomplete.getCreatedBy(),
				incomplete.getCreatedById(),
                    incomplete.getState(),
                    incomplete.getInteractionHistory(),
                    incomplete.getAttachments(),
                    incomplete.getCreatedAt(),
                    incomplete.getUpdatedAt()
            );
      }
}
