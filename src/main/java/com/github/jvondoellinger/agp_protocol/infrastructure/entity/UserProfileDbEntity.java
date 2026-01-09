package com.github.jvondoellinger.agp_protocol.infrastructure.entity;

import com.github.jvondoellinger.agp_protocol.domain.DomainId;
import com.github.jvondoellinger.agp_protocol.domain.profile.UserProfile;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Entity
@Table(name = "tb_user_profile")
public class UserProfileDbEntity implements DbEntity<UserProfile> {
	@Id
	public DomainId userId;

	@ManyToOne
	public AccessProfileDbEntity accessProfile;

	@CreationTimestamp
	public LocalDateTime createdAt;
	@UpdateTimestamp
	public LocalDateTime updatedAt;

	public UserProfileDbEntity(UserProfile user) {
		this.userId = user.getDomainId();
		this.accessProfile = new AccessProfileDbEntity(user.getAccessProfile());
		this.createdAt = user.getCreatedAt();
		this.updatedAt = user.getUpdatedAt();
	}

	@Override
	public UserProfile toDomainEntity() {
		return new UserProfile(
			   userId,
			   accessProfile.toDomainEntity(),
			   createdAt,
			   updatedAt
		);
	}

	public DomainId getUserId() {
		return userId;
	}
	public AccessProfileDbEntity getAccessProfile() {
		return accessProfile;
	}
	public LocalDateTime getCreatedAt() {
		return createdAt;
	}
	public LocalDateTime getUpdatedAt() {
		return updatedAt;
	}

	public void setUserId(DomainId userId) {
		this.userId = userId;
	}
	public void setAccessProfile(AccessProfileDbEntity accessProfile) {
		this.accessProfile = accessProfile;
	}
	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}
	public void setUpdatedAt(LocalDateTime updatedAt) {
		this.updatedAt = updatedAt;
	}
}
