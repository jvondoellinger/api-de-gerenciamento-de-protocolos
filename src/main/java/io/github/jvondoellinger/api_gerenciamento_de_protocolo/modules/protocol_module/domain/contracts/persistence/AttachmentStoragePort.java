package io.github.jvondoellinger.api_gerenciamento_de_protocolo.modules.protocol_module.domain.contracts.persistence;

import io.github.jvondoellinger.api_gerenciamento_de_protocolo.modules.protocol_module.domain.valueObjects.AttachmentPath;
import io.github.jvondoellinger.api_gerenciamento_de_protocolo.modules.protocol_module.domain.valueObjects.FileDetails;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.nio.ByteBuffer;

/**
 * Serviço responsável por gerenciar usuários do sistema.
 */
public interface  AttachmentStoragePort {
	Mono<FileDetails> upload(AttachmentPath path, Flux<ByteBuffer> buffer);
	Flux<ByteBuffer> download(AttachmentPath path);
}
