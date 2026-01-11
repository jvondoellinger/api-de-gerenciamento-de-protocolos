package com.github.jvondoellinger.agp_protocol.infrastructure.entity;

import com.github.jvondoellinger.agp_protocol.adapters.outbound.converter.PermissionsConverter;
import com.github.jvondoellinger.agp_protocol.domain.DomainId;
import com.github.jvondoellinger.agp_protocol.domain.profile.AccessProfile;
import com.github.jvondoellinger.agp_protocol.domain.valueObjects.Permissions;
import jakarta.persistence.Convert;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Entity
@Table(name = "tb_access_profile")
@Getter
@Setter
public class AccessProfileDbEntity implements DbEntity<AccessProfile>{
	@Id
	private String domainId;

	private String name;

	@Convert(converter = PermissionsConverter.class)
	private Permissions permissions;

	@CreationTimestamp
	private LocalDateTime createdAt;
	@UpdateTimestamp
	private LocalDateTime updatedAt;

	public AccessProfileDbEntity(AccessProfile profile) {
		this.domainId = profile.getId().value();
		this.name = profile.getName();
		this.permissions = profile.getPermissions();

		this.createdAt = LocalDateTime.now();
		this.updatedAt = LocalDateTime.now();
	}

	@Override
	public AccessProfile toDomainEntity() {
		return new AccessProfile(
			   DomainId.parse(domainId),
			   name,
			   permissions,
			   createdAt,
			   updatedAt
		);
	}
}
