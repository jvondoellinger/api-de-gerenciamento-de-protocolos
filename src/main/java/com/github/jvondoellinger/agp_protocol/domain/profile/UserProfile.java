package com.github.jvondoellinger.agp_protocol.domain.profile;

import com.github.jvondoellinger.agp_protocol.domain.DomainId;

import java.time.LocalDateTime;

public class UserProfile {
	private final DomainId userId;
	private final AccessProfile accessProfile;

	private final LocalDateTime createdAt;
	private final LocalDateTime updatedAt;

	public UserProfile(DomainId userId, AccessProfile accessProfile, LocalDateTime createdAt, LocalDateTime updatedAt) {
		this.userId = userId;
		this.accessProfile = accessProfile;
		this.createdAt = createdAt;
		this.updatedAt = updatedAt;
	}

	public UserProfile(AccessProfile accessProfile) {
		this.userId = DomainId.create();
		this.accessProfile = accessProfile;
		this.createdAt = LocalDateTime.now();
		this.updatedAt = null;
	}

	public DomainId getUserId() {
		return userId;
	}
	public AccessProfile getAccessProfile() {
		return accessProfile;
	}
	public LocalDateTime getCreatedAt() {
		return createdAt;
	}
	public LocalDateTime getUpdatedAt() {
		return updatedAt;
	}
}
