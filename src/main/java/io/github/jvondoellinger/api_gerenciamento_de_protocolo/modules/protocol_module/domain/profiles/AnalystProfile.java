package io.github.jvondoellinger.api_gerenciamento_de_protocolo.modules.protocol_module.domain.profiles;

import io.github.jvondoellinger.api_gerenciamento_de_protocolo.modules.protocol_module.domain.profiles.permissions.Permission;
import io.github.jvondoellinger.api_gerenciamento_de_protocolo.modules.protocol_module.domain.profiles.permissions.write.CreateProtocolPermission;
import io.github.jvondoellinger.api_gerenciamento_de_protocolo.modules.protocol_module.domain.profiles.permissions.write.InteractProtocolPermission;
import io.github.jvondoellinger.api_gerenciamento_de_protocolo.modules.protocol_module.domain.profiles.permissions.read.ReadProtocolPermission;

import java.util.List;

@Deprecated
public class AnalystProfile extends Profile {
	private static final List<Permission> permissions = List.of(
		   new ReadProtocolPermission(),
		   new CreateProtocolPermission(),
		   new InteractProtocolPermission()
	);

	protected AnalystProfile() {
		super("Analyst", null);
	}
}
