package com.github.jvondoellinger.agp_protocol.application.ticket;

import com.github.jvondoellinger.agp_protocol.application.DomainIdDTO;
import com.github.jvondoellinger.agp_protocol.application.MentionsDTO;

import java.time.LocalDateTime;

public record QueryTicketResponseDTO(
	   String ticketNumber,
	   String title,
	   QueueIdDTO queueId,
	   MentionsDTO mentions,
	   LocalDateTime deadline,
	   DomainIdDTO openedBy,
	   LocalDateTime openedOn,
	   DomainIdDTO lastUpdatedBy,
	   LocalDateTime lastUpdatedOn
) {
}
