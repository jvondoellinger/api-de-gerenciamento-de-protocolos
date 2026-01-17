package com.github.jvondoellinger.agp_protocol.application.userProfile;

import com.github.jvondoellinger.agp_protocol.application.shared.id.DomainIdDTO;
import com.github.jvondoellinger.agp_protocol.domain.DomainId;
import com.github.jvondoellinger.agp_protocol.domain.profile.AccessProfile;
import com.github.jvondoellinger.agp_protocol.domain.profile.UserProfile;
import org.springframework.stereotype.Service;

@Service
public class UserProfileMapper implements Mapper<UserProfile, CreateUserProfileRequestDTO, CreateUserProfileResponseDTO> {
	@Override
	public UserProfile mapToEntity(CreateUserProfileRequestDTO requestDTO) {
		var accessProfileIdStr = requestDTO.accessProfileId().value();
		var accessProfile = accessProfileIdOnly(accessProfileIdStr);
		return new UserProfile(
			accessProfile
		);
	}

	@Override
	public CreateUserProfileResponseDTO mapToResponse(UserProfile userProfile) {
		var domainDtoId = new DomainIdDTO(userProfile.getUserId().value());
		var accessProfileId = new DomainIdDTO(userProfile.getAccessProfile().getDomainId().value());

		return new CreateUserProfileResponseDTO(
			domainDtoId,
			   accessProfileId,
			   userProfile.getCreatedAt(),
			   userProfile.getUpdatedAt()
		);
	}


	private AccessProfile accessProfileIdOnly(String id) {
		return new AccessProfile(
			   DomainId.parse(id),
			   null,
			   null,
			   null,
			   null
		);
	}
}
