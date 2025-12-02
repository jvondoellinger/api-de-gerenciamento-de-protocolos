package io.github.jvondoellinger.api_gerenciamento_de_protocolo.modules.protocol_module.domain.profiles;

import io.github.jvondoellinger.api_gerenciamento_de_protocolo.modules.protocol_module.domain.profiles.permissions.Permission;
import io.github.jvondoellinger.api_gerenciamento_de_protocolo.modules.protocol_module.domain.profiles.permissions.Permissions;
import io.github.jvondoellinger.api_gerenciamento_de_protocolo.modules.protocol_module.domain.profiles.permissions.write.protocol.CreateProtocolPermission;
import io.github.jvondoellinger.api_gerenciamento_de_protocolo.modules.protocol_module.domain.profiles.permissions.write.protocol.InteractProtocolPermission;
import io.github.jvondoellinger.api_gerenciamento_de_protocolo.modules.protocol_module.domain.profiles.permissions.read.protocol.ReadProtocolPermission;
import io.github.jvondoellinger.api_gerenciamento_de_protocolo.modules.protocol_module.domain.profiles.permissions.read.protocol.ReadSensitiveProtocolPermission;

public class Profile {
	public Profile(String name, Permissions permissions) {
		this.name = name;
		this.permissions = permissions;
	}

	private final String name;
	private final Permissions permissions;

	public String getName() {
		return name;
	}

	public Permissions getPermissions() {
		return permissions;
	}

	public boolean canReadInteractions() {
		return containsType(ReadProtocolPermission.class);
	}
	public boolean canInteract() {
		return containsType(InteractProtocolPermission.class);
	}
	public boolean canCreate() {
		return containsType(CreateProtocolPermission.class);
	}
	public boolean canReadSensitiveData() {
		return containsType(ReadSensitiveProtocolPermission.class);
	}

	protected  <T extends Permission> boolean  containsType(Class<T> clazz) {
		return permissions.containsType(clazz);
	}
}
