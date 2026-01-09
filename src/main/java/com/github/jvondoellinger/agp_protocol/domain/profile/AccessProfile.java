package com.github.jvondoellinger.agp_protocol.domain.profile;

import com.github.jvondoellinger.agp_protocol.domain.DomainId;
import com.github.jvondoellinger.agp_protocol.domain.valueObjects.Permissions;

import java.time.LocalDateTime;

public class AccessProfile {
	private final DomainId id;
	private final String name;
	private final Permissions permissions;
	private final LocalDateTime createdAt;
	private final LocalDateTime updatedAt;

	public AccessProfile(DomainId id, String name, Permissions permissions, LocalDateTime createdAt, LocalDateTime updatedAt) {
		this.id = id;
		this.name = name;
		this.permissions = permissions;
		this.createdAt = createdAt;
		this.updatedAt = updatedAt;
	}

	public AccessProfile(String name, Permissions permissions, LocalDateTime createdAt, LocalDateTime updatedAt) {
		this.id = DomainId.create();
		this.name = name;
		this.permissions = permissions;
		this.createdAt = createdAt;
		this.updatedAt = updatedAt;
	}

	public DomainId getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public Permissions getPermissions() {
		return permissions;
	}

	public LocalDateTime getCreatedAt() {
		return createdAt;
	}

	public LocalDateTime getUpdatedAt() {
		return updatedAt;
	}
}

