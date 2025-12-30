package io.github.jvondoellinger.api_gerenciamento_de_protocolo.modules.protocol_module.infrastructure.helper;

import org.slf4j.Logger;
import reactor.core.publisher.Signal;

import java.util.function.Consumer;

public final class ReactiveLog {
	private ReactiveLog() {}

	public static <T> Consumer<Signal<T>> onNext(Logger logger, String message, Object... args) {
		return signal -> {
			if (!signal.isOnNext())
				return;
			logger.info(message, args);
		};
	}

	public static <T> Consumer<Signal<T>> onError(Logger logger, String message, Object... args) {
		return signal -> {
			if (signal.getThrowable() != null) {
				logger.error(message, args, signal.getThrowable());
			}
		};
	}
}
