package io.github.jvondoellinger.api_gerenciamento_de_protocolo.modules.protocol_module.domain.profiles.permissions.write.protocol;

import io.github.jvondoellinger.api_gerenciamento_de_protocolo.modules.protocol_module.domain.profiles.permissions.Permission;

public record DelegateSealedProtocolPermission(String department) implements Permission {
	@Override
	public String getName() {
		return "write::protocol::delegate_%s".formatted(department);
	}
}
