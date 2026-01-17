package com.github.jvondoellinger.agp_protocol.application.userProfile;

import com.github.jvondoellinger.agp_protocol.application.shared.id.DomainIdDTO;

import java.time.LocalDateTime;

public record CreateUserProfileResponseDTO(
	   DomainIdDTO id,
	   DomainIdDTO accessProfileId,
	   LocalDateTime createdAt,
	   LocalDateTime updatedAt
) {
}
