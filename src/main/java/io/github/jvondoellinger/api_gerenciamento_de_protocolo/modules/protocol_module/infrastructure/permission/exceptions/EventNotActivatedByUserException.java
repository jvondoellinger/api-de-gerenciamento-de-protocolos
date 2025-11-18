package io.github.jvondoellinger.api_gerenciamento_de_protocolo.modules.protocol_module.infrastructure.permission.exceptions;

public class EventNotActivatedByUserException extends RuntimeException {
      public EventNotActivatedByUserException() {
      }

      public EventNotActivatedByUserException(String message) {
            super(message);
      }

      public EventNotActivatedByUserException(String message, Throwable cause) {
            super(message, cause);
      }

      public EventNotActivatedByUserException(Throwable cause) {
            super(cause);
      }

      public EventNotActivatedByUserException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
            super(message, cause, enableSuppression, writableStackTrace);
      }
}
