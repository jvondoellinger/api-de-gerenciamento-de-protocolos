package io.github.jvondoellinger.api_gerenciamento_de_protocolo.modules.protocol_module.infrastructure.resolvers.strategy;

import io.github.jvondoellinger.api_gerenciamento_de_protocolo.modules.protocol_module.domain.events.CreateProtocolEvent;
import io.github.jvondoellinger.api_gerenciamento_de_protocolo.modules.protocol_module.domain.events.UserActivityEvent;
import io.github.jvondoellinger.api_gerenciamento_de_protocolo.modules.protocol_module.domain.profiles.permissions.Permission;
import io.github.jvondoellinger.api_gerenciamento_de_protocolo.modules.protocol_module.domain.profiles.permissions.write.protocol.CreateProtocolPermission;
import org.springframework.stereotype.Service;

@Service
public class CreateProtocolEventPermissionStrategy implements EventPermissionResolver {
	@Override
	public Permission resolve(UserActivityEvent eventType) {
		if (eventType instanceof CreateProtocolEvent) {
			return new CreateProtocolPermission();
		}
		return null;
	}
}
