package io.github.jvondoellinger.api_gerenciamento_de_protocolo.modules.protocol_module.infrastructure.database.protocol.exceptions;

public class ProtocoloValidationException extends RuntimeException {
      public ProtocoloValidationException() {
      }

      public ProtocoloValidationException(String message) {
            super(message);
      }

      public ProtocoloValidationException(String message, Throwable cause) {
            super(message, cause);
      }

      public ProtocoloValidationException(Throwable cause) {
            super(cause);
      }

      public ProtocoloValidationException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
            super(message, cause, enableSuppression, writableStackTrace);
      }
}
