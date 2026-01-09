package com.github.jvondoellinger.agp_protocol.domain.profile;

import com.github.jvondoellinger.agp_protocol.domain.DomainId;

import java.time.LocalDateTime;

public class UserProfile {
	private final DomainId domainId;
	private final AccessProfile accessProfile;

	private final LocalDateTime createdAt;
	private final LocalDateTime updatedAt;

	public UserProfile(DomainId domainId, AccessProfile accessProfile, LocalDateTime createdAt, LocalDateTime updatedAt) {
		this.domainId = domainId;
		this.accessProfile = accessProfile;
		this.createdAt = createdAt;
		this.updatedAt = updatedAt;
	}

	public DomainId getDomainId() {
		return domainId;
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
