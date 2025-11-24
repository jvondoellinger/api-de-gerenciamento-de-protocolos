package io.github.jvondoellinger.api_gerenciamento_de_protocolo.modules.protocol_module.application.masks;

import io.github.jvondoellinger.api_gerenciamento_de_protocolo.modules.protocol_module.domain.Protocol;

public class SupervisorProtocolMask extends ProtocolMask {

      public SupervisorProtocolMask(Protocol protocol) {
            super(protocol);
      }

      @Override
      public Protocol mask() {
            return protocol;
      }
}
