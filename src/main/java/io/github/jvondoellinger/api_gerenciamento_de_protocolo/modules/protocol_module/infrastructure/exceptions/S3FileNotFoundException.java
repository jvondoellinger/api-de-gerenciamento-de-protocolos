package io.github.jvondoellinger.api_gerenciamento_de_protocolo.modules.protocol_module.infrastructure.exceptions;

public class S3FileNotFoundException extends RuntimeException {
	public S3FileNotFoundException(String message) {
		super(message);
	}

	public S3FileNotFoundException(String message, Throwable cause) {
		super(message, cause);
	}

	public S3FileNotFoundException(Throwable cause) {
		super(cause);
	}

	public S3FileNotFoundException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public S3FileNotFoundException() {
	}
}
