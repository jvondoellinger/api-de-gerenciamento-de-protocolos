package io.github.jvondoellinger.api_gerenciamento_de_protocolo.modules.protocol_module.domain.exception;

public class BuildingException extends RuntimeException {
      public BuildingException() {
      }

      public BuildingException(String message) {
            super(message);
      }

      public BuildingException(String message, Throwable cause) {
            super(message, cause);
      }

      public BuildingException(Throwable cause) {
            super(cause);
      }

      public BuildingException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
            super(message, cause, enableSuppression, writableStackTrace);
      }
}
