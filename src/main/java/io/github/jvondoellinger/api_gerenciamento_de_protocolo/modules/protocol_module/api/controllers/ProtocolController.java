package io.github.jvondoellinger.api_gerenciamento_de_protocolo.modules.protocol_module.api.controllers;

import io.github.jvondoellinger.api_gerenciamento_de_protocolo.modules.protocol_module.api.resolvers.ReactiveDownloadHeaders;
import io.github.jvondoellinger.api_gerenciamento_de_protocolo.modules.protocol_module.application.requests.ProtocolQueryByProtocolNumberRequest;
import io.github.jvondoellinger.api_gerenciamento_de_protocolo.modules.protocol_module.application.utils.FilePartUtils;
import io.github.jvondoellinger.api_gerenciamento_de_protocolo.modules.protocol_module.domain.contracts.persistence.AttachmentStoragePort;
import io.github.jvondoellinger.api_gerenciamento_de_protocolo.modules.protocol_module.domain.valueObjects.AttachmentPath;
import io.github.jvondoellinger.api_gerenciamento_de_protocolo.modules.protocol_module.domain.valueObjects.FileDetails;
import io.github.jvondoellinger.api_gerenciamento_de_protocolo.modules.protocol_module.domain.valueObjects.ProtocolNumber;
import io.github.jvondoellinger.api_gerenciamento_de_protocolo.modules.protocol_module.infrastructure.events.subs.resolvers.DomainDynamicPublisher;
import io.github.jvondoellinger.api_gerenciamento_de_protocolo.modules.protocol_module.application.commands.CreateProtocolCommand;
import io.github.jvondoellinger.api_gerenciamento_de_protocolo.modules.protocol_module.application.mappers.CreateProtocolEventMapper;
import io.github.jvondoellinger.api_gerenciamento_de_protocolo.modules.protocol_module.application.facade.ProtocolQueryFacadeImpl;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.codec.multipart.FilePart;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.awt.image.DataBuffer;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.Duration;

@RestController
@RequestMapping("/api/protocol")
public class ProtocolController {
	private final ProtocolQueryFacadeImpl facade;
	private final DomainDynamicPublisher resolver;
	private final AttachmentStoragePort storagePort;

	public ProtocolController(ProtocolQueryFacadeImpl facade, DomainDynamicPublisher resolver, AttachmentStoragePort storagePort) {
		this.resolver = resolver;
		this.facade = facade;
		this.storagePort = storagePort;
	}

	@PostMapping
	public Mono<ResponseEntity<Void>> create(@RequestBody CreateProtocolCommand command) {
		var event = CreateProtocolEventMapper.map(command);
		return resolver
			   .publish(event)
			   .map(x -> ResponseEntity.accepted().build());
	}

	@GetMapping
	public Mono<ResponseEntity<?>> get(@RequestParam(required = true) String protocolNumber,
								@RequestParam(required = true) String userId) {
		var request = new ProtocolQueryByProtocolNumberRequest(protocolNumber, userId);

		var data = facade.query(request);

		return data.map(ResponseEntity::ok);
	}

	@PostMapping("/attachment/push")
	public Mono<ResponseEntity<FileDetails>> uploadAttachment(
		   @RequestPart("file") FilePart attachment,
		   @RequestParam String protocolNumber) {

		var buffer = FilePartUtils.toByteBuffer(attachment);
		var pn = ProtocolNumber.parse(protocolNumber);
		var attachmentPath = AttachmentPath.create(attachment.filename(), pn);

		return storagePort
			   .upload(attachmentPath, buffer)
			   .map(ResponseEntity::ok);
	}

	@GetMapping("/attachment/download")
	public Mono<Void> downloadAttachment(
		   @RequestParam String path,
		   ServerHttpResponse response){
		var filename = path.substring(path.lastIndexOf('/') + 1);

		var mediaType = ReactiveDownloadHeaders.resolve(filename);

		response.getHeaders().setContentType(mediaType);
		response.getHeaders().set(
			   HttpHeaders.CONTENT_DISPOSITION,
			   (ReactiveDownloadHeaders.isInline(mediaType) ? "inline" : "attachment")
					 + "; filename=\"" + filename + "\""
		);

		var body = storagePort
			   .download(AttachmentPath.parse(path))
			   .map(buffer -> {
				   buffer.rewind();
				   return response.bufferFactory().wrap(buffer);
			   });

		return response.writeWith(body);
	}
}
