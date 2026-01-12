package com.github.jvondoellinger.agp_protocol.application.queue;

import com.github.jvondoellinger.agp_protocol.application.DomainIdDTO;

public record CreateQueueRequestDTO(
	   String area,
	   String subarea,
	   DomainIdDTO createdBy
) {
}
