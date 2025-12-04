package io.github.jvondoellinger.api_gerenciamento_de_protocolo.modules.protocol_module.infrastructure.permission.strategy;

import io.github.jvondoellinger.api_gerenciamento_de_protocolo.modules.protocol_module.domain.events.AddInteractionProtocolEvent;
import io.github.jvondoellinger.api_gerenciamento_de_protocolo.modules.protocol_module.domain.events.UserActivityEvent;
import io.github.jvondoellinger.api_gerenciamento_de_protocolo.modules.protocol_module.domain.profiles.permissions.Permission;
import io.github.jvondoellinger.api_gerenciamento_de_protocolo.modules.protocol_module.domain.profiles.permissions.write.protocol.InteractProtocolPermission;
import org.springframework.stereotype.Service;

@Service
public class InteractProtocolEventPermissionStrategy implements EventPermissionResolver {
	@Override
	public Permission resolve(UserActivityEvent event) {
		if (event instanceof AddInteractionProtocolEvent) {
			return new InteractProtocolPermission();
		}
		return null;
	}
}
