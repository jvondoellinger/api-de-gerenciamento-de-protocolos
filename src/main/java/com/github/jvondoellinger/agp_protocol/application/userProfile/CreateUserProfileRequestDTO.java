package com.github.jvondoellinger.agp_protocol.application.userProfile;

import com.github.jvondoellinger.agp_protocol.application.shared.id.DomainIdDTO;

public record CreateUserProfileRequestDTO(
	   DomainIdDTO accessProfileId
) {
}
