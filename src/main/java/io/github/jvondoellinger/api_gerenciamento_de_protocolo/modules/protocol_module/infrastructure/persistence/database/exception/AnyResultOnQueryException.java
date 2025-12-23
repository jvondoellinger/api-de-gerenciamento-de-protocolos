package io.github.jvondoellinger.api_gerenciamento_de_protocolo.modules.protocol_module.infrastructure.persistence.database.exception;

public class AnyResultOnQueryException extends RuntimeException {
	public AnyResultOnQueryException() {
	}

	public AnyResultOnQueryException(String message) {
		super(message);
	}

	public AnyResultOnQueryException(String message, Throwable cause) {
		super(message, cause);
	}

	public AnyResultOnQueryException(Throwable cause) {
		super(cause);
	}

	public AnyResultOnQueryException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}
}
