package io.github.jvondoellinger.api_gerenciamento_de_protocolo.modules.protocol_module.infrastructure.permission.exception;

public class MissingPermissionException extends RuntimeException {
	public MissingPermissionException() {
	}

	public MissingPermissionException(String message) {
		super(message);
	}

	public MissingPermissionException(String message, Throwable cause) {
		super(message, cause);
	}

	public MissingPermissionException(Throwable cause) {
		super(cause);
	}

	public MissingPermissionException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}
}
