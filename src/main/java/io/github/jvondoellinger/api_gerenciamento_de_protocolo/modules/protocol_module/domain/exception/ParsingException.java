package io.github.jvondoellinger.api_gerenciamento_de_protocolo.modules.protocol_module.domain.exception;

public class ParsingException extends DomainException {
  public ParsingException() {
  }

  public ParsingException(String message) {
    super(message);
  }

  public ParsingException(String message, Throwable cause) {
    super(message, cause);
  }

  public ParsingException(Throwable cause) {
    super(cause);
  }

  public ParsingException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
    super(message, cause, enableSuppression, writableStackTrace);
  }
}
