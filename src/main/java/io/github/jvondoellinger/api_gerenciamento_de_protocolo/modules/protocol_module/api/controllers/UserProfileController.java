package io.github.jvondoellinger.api_gerenciamento_de_protocolo.modules.protocol_module.api.controllers;

import io.github.jvondoellinger.api_gerenciamento_de_protocolo.modules.protocol_module.application.commands.CreateUserProfileCommand;
import io.github.jvondoellinger.api_gerenciamento_de_protocolo.modules.protocol_module.application.mappers.CreateUserProfileEventMapper;
import io.github.jvondoellinger.api_gerenciamento_de_protocolo.modules.protocol_module.infrastructure.events.subs.resolvers.DomainDynamicPublisher;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/profile")
public class UserProfileController {
	private final DomainDynamicPublisher publisher;

	public UserProfileController(DomainDynamicPublisher publisher) {
		this.publisher = publisher;
	}

	@PostMapping
	public Mono<ResponseEntity<Void>> create(@RequestBody CreateUserProfileCommand command) {
		var event = CreateUserProfileEventMapper.map(command);

		return publisher
			   .publish(event)
			   .map(ResponseEntity::ok);
	}
}
