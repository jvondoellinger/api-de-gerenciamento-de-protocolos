package com.github.jvondoellinger.agp_protocol.application.userProfile.services;

import com.github.jvondoellinger.agp_protocol.application.userProfile.CreateUserProfileRequestDTO;
import com.github.jvondoellinger.agp_protocol.application.userProfile.CreateUserProfileResponseDTO;
import com.github.jvondoellinger.agp_protocol.application.userProfile.UserProfileMapper;
import com.github.jvondoellinger.agp_protocol.application.userProfile.useCases.CreateUserProfileCommandUseCase;
import com.github.jvondoellinger.agp_protocol.domain.profile.UserProfileRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CreateUserProfileCommandUseCasesImpl implements CreateUserProfileCommandUseCase {
	private final UserProfileRepository repository;
	private final UserProfileMapper mapper;

	@Override
	public CreateUserProfileResponseDTO execute(CreateUserProfileRequestDTO createUserProfileRequestDTO) {
		var mappedUserProfile = mapper.mapToEntity(createUserProfileRequestDTO);
		var savedUserProfile = repository.save(mappedUserProfile);
		return mapper.mapToResponse(savedUserProfile);
	}
}
