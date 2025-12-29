package io.github.jvondoellinger.api_gerenciamento_de_protocolo.modules.protocol_module.domain.events;

import io.github.jvondoellinger.api_gerenciamento_de_protocolo.modules.protocol_module.domain.valueObjects.AttachmentPath;
import io.github.jvondoellinger.api_gerenciamento_de_protocolo.modules.protocol_module.domain.valueObjects.DomainId;
import reactor.core.publisher.Flux;
import java.nio.ByteBuffer;

public class AddAttachmentProtocolEvent extends UserActivityEvent {
	public AddAttachmentProtocolEvent(DomainId userId, AttachmentPath path, Flux<ByteBuffer> buffer) {
		super(userId);
		this.path = path;
		this.buffer = buffer;
	}
	private final AttachmentPath path;
	private final Flux<ByteBuffer> buffer;

	public AttachmentPath getPath() {
		return path;
	}

	public Flux<ByteBuffer> getBuffer() {
		return buffer;
	}
}
