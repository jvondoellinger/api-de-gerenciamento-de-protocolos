package com.github.jvondoellinger.agp_protocol.application.accessProfile;

import com.github.jvondoellinger.agp_protocol.application.DomainIdDTO;
import com.github.jvondoellinger.agp_protocol.application.shared.Mapper;
import com.github.jvondoellinger.agp_protocol.domain.profile.AccessProfile;
import com.github.jvondoellinger.agp_protocol.domain.valueObjects.Permission;
import com.github.jvondoellinger.agp_protocol.domain.valueObjects.Permissions;
import org.springframework.stereotype.Service;

@Service
public class AccessProfileMapper implements Mapper<AccessProfile, CreateAccessProfileRequestDTO, CreateAccessProfileResponseDTO> {

	@Override
	public AccessProfile mapToEntity(CreateAccessProfileRequestDTO requestDTO) {
		var permissionList = requestDTO
			   .permissions()
			   .list()
			   .stream()
			   .map(Permission::of).toList();
		var permission = new Permissions(permissionList);

		return new AccessProfile(
			   requestDTO.name(),
			   permission
		);
	}

	@Override
	public CreateAccessProfileResponseDTO mapToResponse(AccessProfile accessProfile) {
		var domainIdDto = new DomainIdDTO(accessProfile.getDomainId().value());
		var permissionStrList = accessProfile.getPermissions().readonlyList().stream().map(Permission::code).toList();
		var permissionsDto = new PermissionsDTO(permissionStrList);

		return new CreateAccessProfileResponseDTO(
			   domainIdDto,
			   accessProfile.getName(),
			   permissionsDto,
			   accessProfile.getCreatedAt(),
			   accessProfile.getUpdatedAt()
		);
	}
}
