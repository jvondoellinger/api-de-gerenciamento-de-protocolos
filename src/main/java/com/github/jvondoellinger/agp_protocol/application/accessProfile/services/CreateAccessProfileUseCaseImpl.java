package com.github.jvondoellinger.agp_protocol.application.accessProfile.services;

import com.github.jvondoellinger.agp_protocol.application.accessProfile.AccessProfileMapper;
import com.github.jvondoellinger.agp_protocol.application.accessProfile.CreateAccessProfileRequestDTO;
import com.github.jvondoellinger.agp_protocol.application.accessProfile.CreateAccessProfileResponseDTO;
import com.github.jvondoellinger.agp_protocol.application.accessProfile.useCases.CreateAccessProfileUseCase;
import com.github.jvondoellinger.agp_protocol.domain.profile.AccessProfileRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CreateAccessProfileUseCaseImpl implements CreateAccessProfileUseCase {
	private final AccessProfileRepository repository;
	private final AccessProfileMapper mapper;

	@Override
	public CreateAccessProfileResponseDTO execute(CreateAccessProfileRequestDTO createAccessProfileRequestDTO) {
		var mappedEntity = mapper.mapToEntity(createAccessProfileRequestDTO);
		var savedEntity = repository.save(mappedEntity);
		return mapper.mapToResponse(savedEntity);
	}
}
