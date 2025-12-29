package io.github.jvondoellinger.api_gerenciamento_de_protocolo.modules.protocol_module.api.controllers;

import io.github.jvondoellinger.api_gerenciamento_de_protocolo.modules.protocol_module.application.dtos.AttachmentPathDTO;
import io.github.jvondoellinger.api_gerenciamento_de_protocolo.modules.protocol_module.application.requests.ProtocolQueryByProtocolNumberRequest;
import io.github.jvondoellinger.api_gerenciamento_de_protocolo.modules.protocol_module.application.services.AttachmentService;
import io.github.jvondoellinger.api_gerenciamento_de_protocolo.modules.protocol_module.domain.valueObjects.AttachmentPath;
import io.github.jvondoellinger.api_gerenciamento_de_protocolo.modules.protocol_module.domain.valueObjects.FileDetails;
import io.github.jvondoellinger.api_gerenciamento_de_protocolo.modules.protocol_module.domain.valueObjects.ProtocolNumber;
import io.github.jvondoellinger.api_gerenciamento_de_protocolo.modules.protocol_module.infrastructure.events.subs.resolvers.DomainDynamicPublisher;
import io.github.jvondoellinger.api_gerenciamento_de_protocolo.modules.protocol_module.application.commands.CreateProtocolCommand;
import io.github.jvondoellinger.api_gerenciamento_de_protocolo.modules.protocol_module.application.mappers.CreateProtocolEventMapper;
import io.github.jvondoellinger.api_gerenciamento_de_protocolo.modules.protocol_module.application.facade.ProtocolQueryFacadeImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.http.codec.multipart.FilePart;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/protocol")
public class ProtocolController {
	private final ProtocolQueryFacadeImpl facade;
	private final DomainDynamicPublisher resolver;
	private final AttachmentService attachmentService;

	public ProtocolController(ProtocolQueryFacadeImpl facade, DomainDynamicPublisher resolver, AttachmentService attachmentService) {
		this.resolver = resolver;
		this.facade = facade;
		this.attachmentService = attachmentService;
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


	@GetMapping("/attachments")
	public Flux<AttachmentPathDTO> searchAttachments(@RequestParam(required = true) String protocolNumber) {
		var pn = ProtocolNumber.parse(protocolNumber);

		return attachmentService.listAttachments(pn);
	}

	@PostMapping("/attachment/push")
	public Mono<ResponseEntity<FileDetails>> uploadAttachment(@RequestPart(value = "file") FilePart attachment, @RequestParam String protocolNumber) {

		return attachmentService
			   .upload(attachment, protocolNumber)
			   .map(ResponseEntity::ok);
	}

	@GetMapping("/attachment/download")
	public Mono<Void> downloadAttachment(@RequestParam String path, ServerHttpResponse response) {
		return attachmentService.download(path, response);
	}
}
