package io.github.jvondoellinger.api_gerenciamento_de_protocolo.modules.protocol_module.application.masks;

import io.github.jvondoellinger.api_gerenciamento_de_protocolo.modules.protocol_module.domain.Protocol;

public abstract class ProtocolMask {
      protected final Protocol protocol;

      public ProtocolMask(Protocol protocol) {
            this.protocol = protocol;
      }

      public abstract Protocol mask();
}