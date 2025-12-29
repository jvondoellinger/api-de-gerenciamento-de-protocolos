package io.github.jvondoellinger.api_gerenciamento_de_protocolo.modules.protocol_module.domain.valueObjects;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.nio.ByteBuffer;
import java.util.List;
import java.util.function.Function;
import java.util.function.Supplier;

public class Attachment {
	private final Flux<ByteBuffer> buffer;
	private final AttachmentPath path;
	private Attachment(Flux<ByteBuffer> buffer, AttachmentPath path) {
		this.buffer = buffer;
		this.path = path;
	}

	public static Attachment create(Flux<ByteBuffer> buffer, AttachmentPath path) {
		return new Attachment(buffer, path);
	}

	public Flux<ByteBuffer> getBuffer() {
		return buffer;
	}

	public AttachmentPath getPath() {
		return path;
	}
}
