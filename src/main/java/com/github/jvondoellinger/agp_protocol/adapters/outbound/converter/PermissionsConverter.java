package com.github.jvondoellinger.agp_protocol.adapters.outbound.converter;

import com.github.jvondoellinger.agp_protocol.domain.valueObjects.Permission;
import com.github.jvondoellinger.agp_protocol.domain.valueObjects.Permissions;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter
public class PermissionsConverter implements AttributeConverter<Permissions, String> {
	@Override
	public String convertToDatabaseColumn(Permissions permissions) {
		return serialize(permissions);
	}

	@Override
	public Permissions convertToEntityAttribute(String s) {
		return deserialize(s);
	}

	public String serialize(Permissions permissions) {
		StringBuilder allPermissions = new StringBuilder();

		for (var p : permissions.readonlyList()) {
			allPermissions.append("%s;".formatted(p.code()));
		}

		return allPermissions.toString();
	}

	public Permissions deserialize(String serialized) {
		String[] values = serialized.split(";");
		Permissions permissions = new Permissions();

		for (var value : values) {
			permissions.add(Permission.of(value));
		}

		return permissions;
	}
}
