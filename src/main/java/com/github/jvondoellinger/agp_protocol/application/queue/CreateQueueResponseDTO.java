package com.github.jvondoellinger.agp_protocol.application.queue;

import com.github.jvondoellinger.agp_protocol.application.DomainIdDTO;

import java.time.LocalDateTime;

public record CreateQueueResponseDTO(
	   DomainIdDTO id,
	   String area,
	   String subarea,
	   DomainIdDTO createdById,
	   LocalDateTime createdAt,
	   DomainIdDTO lastUpdatedById,
	   LocalDateTime lastUpdatedAt
) {
}
