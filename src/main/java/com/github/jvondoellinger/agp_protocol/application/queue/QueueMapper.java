package com.github.jvondoellinger.agp_protocol.application.queue;

import com.github.jvondoellinger.agp_protocol.application.DomainIdDTO;
import com.github.jvondoellinger.agp_protocol.application.shared.Mapper;
import com.github.jvondoellinger.agp_protocol.domain.DomainId;
import com.github.jvondoellinger.agp_protocol.domain.profile.UserProfile;
import com.github.jvondoellinger.agp_protocol.domain.queue.Queue;
import org.springframework.stereotype.Service;

@Service
public class QueueMapper implements Mapper<Queue, CreateQueueRequestDTO, CreateQueueResponseDTO> {

	@Override
	public Queue mapToEntity(CreateQueueRequestDTO requestDTO) {
		var userProfileIdOnly = userProfileIdOnly(requestDTO.createdBy().value());
		return new Queue(
			   requestDTO.area(),
			   requestDTO.subarea(),
			   userProfileIdOnly
		);
	}
	@Override
	public CreateQueueResponseDTO mapToResponse(Queue queue) {
		var id = new DomainIdDTO(queue.getDomainId().value());
		var createdById = new DomainIdDTO(queue.getCreatedBy().getDomainId().value());
		var updatedById = queue.getLastUpdatedBy() == null ?
			   null : new DomainIdDTO(queue.getLastUpdatedBy().getDomainId().value());
		return new CreateQueueResponseDTO(
			   id,
			   queue.getArea(),
			   queue.getSubarea(),
			   createdById,
			   queue.getCreatedAt(),
			   updatedById,
			   queue.getUpdatedAt()
		);
	}

	private UserProfile userProfileIdOnly(String id) {
		return new UserProfile(
			   DomainId.parse(id),
			   null,
			   null,
			   null
		);
	}
}
