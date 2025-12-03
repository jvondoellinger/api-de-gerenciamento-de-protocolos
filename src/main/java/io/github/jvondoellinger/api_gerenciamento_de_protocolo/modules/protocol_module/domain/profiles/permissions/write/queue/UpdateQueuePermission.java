package io.github.jvondoellinger.api_gerenciamento_de_protocolo.modules.protocol_module.domain.profiles.permissions.write.queue;

import io.github.jvondoellinger.api_gerenciamento_de_protocolo.modules.protocol_module.domain.profiles.permissions.Permission;

public record UpdateQueuePermission() implements Permission {
	@Override
	public String getName() {
		return "queue::modify";
	}
}
