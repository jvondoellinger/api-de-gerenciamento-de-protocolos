package com.github.jvondoellinger.agp_protocol.application.queue;

import java.time.LocalDateTime;

public record CreateQueueResponseDTO(
	   String id,
	   String area,
	   String subarea,
	   String createdById,
	   LocalDateTime createdAt,
	   String lastUpdatedById,
	   LocalDateTime lastUpdatedAt
) {
}
