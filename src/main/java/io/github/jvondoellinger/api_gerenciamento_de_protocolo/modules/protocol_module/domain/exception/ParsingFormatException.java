package io.github.jvondoellinger.api_gerenciamento_de_protocolo.modules.protocol_module.domain.exception;

public class ParsingFormatException extends RuntimeException {
  public ParsingFormatException() {
  }

  public ParsingFormatException(String message) {
    super(message);
  }

  public ParsingFormatException(String message, Throwable cause) {
    super(message, cause);
  }

  public ParsingFormatException(Throwable cause) {
    super(cause);
  }

  public ParsingFormatException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
    super(message, cause, enableSuppression, writableStackTrace);
  }
}
