package io.github.jvondoellinger.api_gerenciamento_de_protocolo.modules.protocol_module.domain.profiles.permissions.read.protocol;

import io.github.jvondoellinger.api_gerenciamento_de_protocolo.modules.protocol_module.domain.profiles.permissions.Permission;

public record ReadImagesProtocolPermission() implements Permission {
	@Override
	public String getName() {
		return "protocol::images::read";
	}
}
