package com.github.jvondoellinger.agp_protocol.application.accessProfile;


import com.github.jvondoellinger.agp_protocol.application.DomainIdDTO;

import java.time.LocalDateTime;

public record UpdateAccessProfileResponseDTO(
	   DomainIdDTO id,
	   String name,
	   PermissionsDTO permissionsDTO,
	   LocalDateTime createdAt,
	   LocalDateTime updatedAt
) {
}
