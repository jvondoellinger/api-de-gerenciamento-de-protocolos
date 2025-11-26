package io.github.jvondoellinger.api_gerenciamento_de_protocolo.modules.protocol_module.application.facade.proxy;

import io.github.jvondoellinger.api_gerenciamento_de_protocolo.modules.protocol_module.application.facade.ProtocolQueryFacade;
import io.github.jvondoellinger.api_gerenciamento_de_protocolo.modules.protocol_module.application.facade.ProtocolQueryFacadeImpl;
import io.github.jvondoellinger.api_gerenciamento_de_protocolo.modules.protocol_module.application.requests.ProtocolQueryByProtocolNumberRequest;
import io.github.jvondoellinger.api_gerenciamento_de_protocolo.modules.protocol_module.domain.Protocol;
import io.github.jvondoellinger.api_gerenciamento_de_protocolo.modules.protocol_module.domain.valueObjects.DomainId;
import io.github.jvondoellinger.api_gerenciamento_de_protocolo.modules.protocol_module.infrastructure.permission.checker.PermissionChecker;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public class PermissionProtocolQueryProxy implements ProtocolQueryFacade {
      private final ProtocolQueryFacadeImpl next;
      private final PermissionChecker checker;

      public PermissionProtocolQueryProxy(ProtocolQueryFacadeImpl next, PermissionChecker checker) {
            this.next = next;
            this.checker = checker;
      }
      @Override
      public Mono<Protocol> query(ProtocolQueryByProtocolNumberRequest request) {
            var userId = DomainId.from(request.userId());
            // Isso vai fazer haver 2 chamadas ao database de forma desnecssaria
            // var relationship = repository.findRelationships(userId);

            return next.query(request);
      }
}
