package io.github.jvondoellinger.api_gerenciamento_de_protocolo.modules.protocol_module.application.services;

import io.github.jvondoellinger.api_gerenciamento_de_protocolo.modules.protocol_module.api.resolvers.ReactiveDownloadHeaders;
import io.github.jvondoellinger.api_gerenciamento_de_protocolo.modules.protocol_module.application.dtos.AttachmentPathDTO;
import io.github.jvondoellinger.api_gerenciamento_de_protocolo.modules.protocol_module.application.utils.FilePartUtils;
import io.github.jvondoellinger.api_gerenciamento_de_protocolo.modules.protocol_module.domain.contracts.persistence.AttachmentStoragePort;
import io.github.jvondoellinger.api_gerenciamento_de_protocolo.modules.protocol_module.domain.valueObjects.Attachment;
import io.github.jvondoellinger.api_gerenciamento_de_protocolo.modules.protocol_module.domain.valueObjects.AttachmentPath;
import io.github.jvondoellinger.api_gerenciamento_de_protocolo.modules.protocol_module.domain.valueObjects.FileDetails;
import io.github.jvondoellinger.api_gerenciamento_de_protocolo.modules.protocol_module.domain.valueObjects.ProtocolNumber;
import org.springframework.http.HttpHeaders;
import org.springframework.http.codec.multipart.FilePart;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class AttachmentService {
	private final AttachmentStoragePort storagePort;

	public AttachmentService(AttachmentStoragePort storagePort) {
		this.storagePort = storagePort;
	}

	public Mono<FileDetails> upload(FilePart attachment, String protocolNumber) {
		var buffer = FilePartUtils.toByteBuffer(attachment);
		var pn = ProtocolNumber.parse(protocolNumber);
		var attachmentPath = AttachmentPath.create(attachment.filename(), pn);

		return storagePort
			   .upload(attachmentPath, buffer);
	}

	public Flux<AttachmentPathDTO> listAttachments(ProtocolNumber number) {
		return storagePort.listAttachments(number).map(x -> new AttachmentPathDTO(x.toString()));
	}
	public Mono<Void> download(String path, ServerHttpResponse response) {
		return storagePort
			   .download(AttachmentPath.parse(path)) // Start download
			   .flatMap(x -> {
				   writeHeaders(path, response); // Write response headers

				   return writeBody(x, response); // Write response body
			   });

	}
	private Mono<Void> writeBody(Attachment attachment, ServerHttpResponse response) {
		return response.writeWith((
			   attachment.getBuffer()
					 .map(buffer -> {
						 buffer.rewind();
						 return response.bufferFactory().wrap(buffer);
					 })
		));
	}
	private void writeHeaders(String path, ServerHttpResponse response) {
		var filename = path.substring(path.lastIndexOf('/') + 1);
		var mediaType = ReactiveDownloadHeaders.resolve(filename);

		response.getHeaders().setContentType(mediaType);
		response.getHeaders().set(
			   HttpHeaders.CONTENT_DISPOSITION,
			   (ReactiveDownloadHeaders.isInline(mediaType) ? "inline" : "attachment")
					 + "; filename=\"" + filename + "\""
		);
	}
}
