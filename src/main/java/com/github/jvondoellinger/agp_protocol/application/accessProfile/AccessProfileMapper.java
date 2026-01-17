package com.github.jvondoellinger.agp_protocol.application.accessProfile;

import com.github.jvondoellinger.agp_protocol.domain.profile.AccessProfile;
import com.github.jvondoellinger.agp_protocol.domain.valueObjects.Permission;
import com.github.jvondoellinger.agp_protocol.domain.valueObjects.Permissions;
import org.springframework.stereotype.Service;

import static com.github.jvondoellinger.agp_protocol.application.shared.DtoSharedMapper.*;

@Service
public class AccessProfileMapper{

	public AccessProfile from(CreateAccessProfileRequestDTO requestDTO) {
		return new AccessProfile(
			   requestDTO.name(),
			   permissions(requestDTO.permissions())
		);
	}

	public CreateAccessProfileResponseDTO toCreateResponse(AccessProfile accessProfile) {
		return new CreateAccessProfileResponseDTO(
			   mapId(accessProfile.getDomainId()),
			   accessProfile.getName(),
			   permissionsDTO(accessProfile.getPermissions()),
			   accessProfile.getCreatedAt(),
			   accessProfile.getUpdatedAt()
		);
	}

	// Utilities
	private Permissions permissions(PermissionsDTO dto) {
		var permissionList = dto
			   .list()
			   .stream()
			   .map(Permission::of).toList();
		return new Permissions(permissionList);
	}
	private PermissionsDTO permissionsDTO(Permissions p) {
		var permissionStrList = p.readonlyList().stream().map(Permission::code).toList();
		return new PermissionsDTO(permissionStrList);
	}
}
