package com.github.jvondoellinger.agp_protocol.application.queue.services;

import com.github.jvondoellinger.agp_protocol.application.queue.CreateQueueRequestDTO;
import com.github.jvondoellinger.agp_protocol.application.queue.CreateQueueResponseDTO;
import com.github.jvondoellinger.agp_protocol.application.queue.QueueMapper;
import com.github.jvondoellinger.agp_protocol.application.queue.useCases.CreateQueueCommandUseCase;
import com.github.jvondoellinger.agp_protocol.domain.queue.QueueRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CreateQueueCommandUseCasesImpl implements CreateQueueCommandUseCase {
	private final QueueRepository repository;
	private final QueueMapper mapper;

	@Override
	public CreateQueueResponseDTO execute(CreateQueueRequestDTO createQueueRequestDTO) {
		var mappedEntity = mapper.mapToEntity(createQueueRequestDTO);
		var savedEntity = repository.save(mappedEntity);
		return mapper.mapToResponse(savedEntity);
	}
}
