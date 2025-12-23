package io.github.jvondoellinger.api_gerenciamento_de_protocolo.modules.protocol_module.infrastructure.persistence.files;

import io.github.jvondoellinger.api_gerenciamento_de_protocolo.modules.protocol_module.domain.contracts.persistence.AttachmentStoragePort;
import io.github.jvondoellinger.api_gerenciamento_de_protocolo.modules.protocol_module.domain.valueObjects.AttachmentPath;
import io.github.jvondoellinger.api_gerenciamento_de_protocolo.modules.protocol_module.domain.valueObjects.FileDetails;
import io.github.jvondoellinger.api_gerenciamento_de_protocolo.modules.protocol_module.infrastructure.persistence.files.config.ProtocolS3Config;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import java.nio.ByteBuffer;

@Service
public class AttachmentStorageS3Adapter implements AttachmentStoragePort {
	private final ProtocolS3Config config;
	private final StorageService fileStorage;

	public AttachmentStorageS3Adapter(ProtocolS3Config config, StorageService fileStorage) {
		this.fileStorage = fileStorage;
		this.config = config;
	}

	@Override
	public Mono<FileDetails> upload(AttachmentPath path, Flux<ByteBuffer> buffer) {
		return fileStorage.upload(config.getBucket(), path.toString(), buffer);
	}

	@Override
	public Flux<ByteBuffer> download(AttachmentPath path) {
		return fileStorage.download(config.getBucket(), path.toString());
	}
}
