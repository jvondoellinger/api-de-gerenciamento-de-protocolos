package io.github.jvondoellinger.api_gerenciamento_de_protocolo.modules.protocol_module.domain.profiles.permissions;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Permissions {
	private final List<Permission> permissions;

	private Permissions() {
		permissions = new ArrayList<>();
	}
	private Permissions(List<Permission> permissions) {
		this.permissions = permissions;
	}




	public static Permissions create() {
		return new Permissions();
	}
	public static Permissions create(List<Permission> permissions) {
		return new Permissions(permissions);
	}





	public List<String> getListString() {
		return permissions
			   .stream()
			   .map(Permission::getName)
			   .toList();
	}
	public List<Permission> getPermissions() {
		return List.copyOf(permissions);
	}
	public boolean contains(String permission) {
		return permissions
			   .stream()
			   .anyMatch(x -> x.getName().equals(permission));
	}
	public boolean contains(Permission permission) {
		return permissions
			   .stream()
			   .anyMatch(x -> x.getName().equals(permission.getName()));
	}

	public <T extends Permission> boolean containsType(Class<T> clazz) {
		return permissions
			   .stream()
			   .anyMatch(clazz::isInstance);
	}

	@Override
	public boolean equals(Object object) {
		if (object == null || getClass() != object.getClass()) return false;
		Permissions that = (Permissions) object;
		return Objects.equals(permissions, that.permissions);
	}

	@Override
	public int hashCode() {
		return Objects.hashCode(permissions);
	}
}
