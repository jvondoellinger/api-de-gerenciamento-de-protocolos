package com.github.jvondoellinger.agp_protocol.infrastructure.entity;

import com.github.jvondoellinger.agp_protocol.domain.DomainId;
import com.github.jvondoellinger.agp_protocol.domain.profile.AccessProfile;
import com.github.jvondoellinger.agp_protocol.domain.valueObjects.Permissions;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Entity
@Table(name = "tb_access_profile")
public class AccessProfileDbEntity implements DbEntity<AccessProfile>{
	@Id
	private DomainId domainId;

	private String name;
	private Permissions permissions;

	@CreationTimestamp
	private LocalDateTime createdAt;
	@UpdateTimestamp
	private LocalDateTime updatedAt;

	public AccessProfileDbEntity(AccessProfile profile) {
		this.domainId = profile.getId();
		this.name = profile.getName();
		this.permissions = profile.getPermissions();

		this.createdAt = LocalDateTime.now();
		this.updatedAt = LocalDateTime.now();
	}

	public DomainId getDomainId() {
		return domainId;
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

	public void setDomainId(DomainId domainId) {
		this.domainId = domainId;
	}
	public void setName(String name) {
		this.name = name;
	}
	public void setPermissions(Permissions permissions) {
		this.permissions = permissions;
	}
	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}
	public void setUpdatedAt(LocalDateTime updatedAt) {
		this.updatedAt = updatedAt;
	}

	@Override
	public AccessProfile toDomainEntity() {
		return new AccessProfile(
			   domainId,
			   name,
			   permissions,
			   createdAt,
			   updatedAt
		);
	}
}
