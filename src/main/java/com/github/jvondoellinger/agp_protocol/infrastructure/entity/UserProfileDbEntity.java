package com.github.jvondoellinger.agp_protocol.infrastructure.entity;

import com.github.jvondoellinger.agp_protocol.domain.DomainId;
import com.github.jvondoellinger.agp_protocol.domain.profile.UserProfile;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.annotation.PersistenceCreator;

import java.time.LocalDateTime;

@Entity
@Table(name = "tb_user_profile")
@Getter
@Setter
public class UserProfileDbEntity implements DbEntity<UserProfile> {
	@Id
	public String userId;

	@ManyToOne(fetch = FetchType.EAGER)
	public AccessProfileDbEntity accessProfile;

	@CreationTimestamp
	public LocalDateTime createdAt;
	@UpdateTimestamp
	public LocalDateTime updatedAt;

	public UserProfileDbEntity(UserProfile user) {
		this.userId = user.getDomainId().toString();
		this.accessProfile = new AccessProfileDbEntity(user.getAccessProfile());
		this.createdAt = user.getCreatedAt();
		this.updatedAt = user.getUpdatedAt();
	}

	@PersistenceCreator
	public UserProfileDbEntity(String userId, AccessProfileDbEntity accessProfile, LocalDateTime createdAt, LocalDateTime updatedAt) {
		this.userId = userId;
		this.accessProfile = accessProfile;
		this.createdAt = createdAt;
		this.updatedAt = updatedAt;
	}

	protected UserProfileDbEntity() {}

	@Override
	public UserProfile toDomainEntity() {
		return new UserProfile(
			   DomainId.parse(userId),
			   accessProfile.toDomainEntity(),
			   createdAt,
			   updatedAt
		);
	}
}
