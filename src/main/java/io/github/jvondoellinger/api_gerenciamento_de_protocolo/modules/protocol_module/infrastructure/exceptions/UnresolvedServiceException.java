package io.github.jvondoellinger.api_gerenciamento_de_protocolo.modules.protocol_module.infrastructure.exceptions;

public class UnresolvedServiceException extends RuntimeException {
	public UnresolvedServiceException() {
	}

	public UnresolvedServiceException(String message) {
		super(message);
	}

	public UnresolvedServiceException(String message, Throwable cause) {
		super(message, cause);
	}

	public UnresolvedServiceException(Throwable cause) {
		super(cause);
	}

	public UnresolvedServiceException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}
}
