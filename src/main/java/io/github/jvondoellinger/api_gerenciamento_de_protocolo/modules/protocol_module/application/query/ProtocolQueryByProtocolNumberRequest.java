package io.github.jvondoellinger.api_gerenciamento_de_protocolo.modules.protocol_module.application.query;

import java.util.Objects;

public record ProtocolQueryByProtocolNumberRequest(String protocolNumber, String userId) implements ProtocolQueryRequest {
      @Override
      public String getUserId() {
            return userId;
      }

      @Override
      public boolean equals(Object object) {
            if (object == null || getClass() != object.getClass()) return false;
            ProtocolQueryByProtocolNumberRequest that = (ProtocolQueryByProtocolNumberRequest) object;
            return Objects.equals(userId, that.userId) && Objects.equals(protocolNumber, that.protocolNumber);
      }

      @Override
      public int hashCode() {
            return Objects.hash(protocolNumber, userId);
      }
}
