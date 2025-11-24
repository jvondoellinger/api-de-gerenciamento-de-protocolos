package io.github.jvondoellinger.api_gerenciamento_de_protocolo.modules.protocol_module.application.facade;

import io.github.jvondoellinger.api_gerenciamento_de_protocolo.modules.protocol_module.application.requests.ProtocolQueryByProtocolNumberRequest;
import io.github.jvondoellinger.api_gerenciamento_de_protocolo.modules.protocol_module.domain.Protocol;
import reactor.core.publisher.Mono;

public interface ProtocolQueryFacade {
      Mono<Protocol> query(ProtocolQueryByProtocolNumberRequest request);
}
