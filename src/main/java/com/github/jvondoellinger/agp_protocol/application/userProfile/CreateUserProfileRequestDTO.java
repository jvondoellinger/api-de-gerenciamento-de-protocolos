package com.github.jvondoellinger.agp_protocol.application.userProfile;

import com.github.jvondoellinger.agp_protocol.application.DomainIdDTO;

public record CreateUserProfileRequestDTO(
	   DomainIdDTO accessProfileId
) {
}
