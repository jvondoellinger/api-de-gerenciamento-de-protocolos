package com.github.jvondoellinger.agp_protocol.application.accessProfile;

import com.github.jvondoellinger.agp_protocol.application.DomainIdDTO;

public record CreateAccessProfileRequestDTO(
	   DomainIdDTO id,
	   String name,
	   PermissionsDTO permissions
) {
}
