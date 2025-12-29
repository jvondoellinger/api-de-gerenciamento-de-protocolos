package io.github.jvondoellinger.api_gerenciamento_de_protocolo.modules.protocol_module.infrastructure.persistence.files;

import io.github.jvondoellinger.api_gerenciamento_de_protocolo.modules.protocol_module.domain.valueObjects.FileDetails;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import software.amazon.awssdk.services.s3.model.S3Object;

import java.nio.ByteBuffer;

public interface StorageService {
	Mono<FileDetails> upload(String bucket, String key, Flux<ByteBuffer> stream);
	Flux<ByteBuffer> download(String bucket, String key);
	Flux<S3Object> searchPaths(String bucket, String prefix);
}
