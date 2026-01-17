package com.github.jvondoellinger.agp_protocol.application.accessProfile.services;

import com.github.jvondoellinger.agp_protocol.application.accessProfile.AccessProfileMapper;
import com.github.jvondoellinger.agp_protocol.application.accessProfile.CreateAccessProfileRequestDTO;
import com.github.jvondoellinger.agp_protocol.application.accessProfile.CreateAccessProfileResponseDTO;
import com.github.jvondoellinger.agp_protocol.application.accessProfile.useCases.CreateAccessProfileCommandUseCase;
import com.github.jvondoellinger.agp_protocol.domain.profile.AccessProfileRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CreateAccessProfileCommandUseCaseImpl implements CreateAccessProfileCommandUseCase {
	private final AccessProfileRepository repository;
	private final AccessProfileMapper mapper;

	@Override
	public CreateAccessProfileResponseDTO execute(CreateAccessProfileRequestDTO createAccessProfileRequestDTO) {
		var mappedEntity = mapper.from(createAccessProfileRequestDTO);
		var savedEntity = repository.save(mappedEntity);
		return mapper.toCreateResponse(savedEntity);
	}
}
