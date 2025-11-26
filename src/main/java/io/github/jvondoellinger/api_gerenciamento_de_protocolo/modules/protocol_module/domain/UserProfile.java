package io.github.jvondoellinger.api_gerenciamento_de_protocolo.modules.protocol_module.domain;

import io.github.jvondoellinger.api_gerenciamento_de_protocolo.modules.protocol_module.domain.profiles.Profile;
import io.github.jvondoellinger.api_gerenciamento_de_protocolo.modules.protocol_module.domain.valueObjects.DomainId;

public class UserProfile {

	public UserProfile(DomainId id, DomainId userId, Profile profile) {
		this.id = id;
		this.userId = userId;
		this.profile = profile;
	}

	public UserProfile(DomainId userId, Profile profile) {
		this.id = new DomainId();
		this.profile = profile;
		this.userId = userId;
	}

	// Fields
	private final DomainId id;
	private final DomainId userId; // Relationship
	private final Profile profile;

	// Getter
	public Profile getProfile() {
		return profile;
	}
	public DomainId getId() {
		return id;
	}
	public DomainId getUserId() {
		return userId;
	}

	public boolean containsPermission(String permission) {
		return profile.getPermissions().contains(permission);
	}
}
