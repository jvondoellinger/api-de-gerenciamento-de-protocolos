package io.github.jvondoellinger.api_gerenciamento_de_protocolo.modules.protocol_module.infrastructure.persistence.files;

import io.github.jvondoellinger.api_gerenciamento_de_protocolo.modules.protocol_module.domain.contracts.persistence.AttachmentStoragePort;
import io.github.jvondoellinger.api_gerenciamento_de_protocolo.modules.protocol_module.domain.valueObjects.Attachment;
import io.github.jvondoellinger.api_gerenciamento_de_protocolo.modules.protocol_module.domain.valueObjects.AttachmentPath;
import io.github.jvondoellinger.api_gerenciamento_de_protocolo.modules.protocol_module.domain.valueObjects.FileDetails;
import io.github.jvondoellinger.api_gerenciamento_de_protocolo.modules.protocol_module.domain.valueObjects.ProtocolNumber;
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
	public Mono<Attachment> download(AttachmentPath path) {
		return fileStorage
			   .download(config.getBucket(), path.toString())
			   .transform(buffer -> Mono.just(Attachment.create(buffer, path)))
			   .last();
	}

	@Override
	public Flux<AttachmentPath> listAttachments(ProtocolNumber path) {
		return fileStorage.searchPaths(config.getBucket(), path.getValue())
			   .map(x -> {
				   var key = x.key();
				   return AttachmentPath.parse(key);
			   });
	}

}
