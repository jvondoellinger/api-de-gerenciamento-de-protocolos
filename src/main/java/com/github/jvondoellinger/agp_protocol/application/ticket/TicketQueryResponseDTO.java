package com.github.jvondoellinger.agp_protocol.application.ticket;

import com.github.jvondoellinger.agp_protocol.application.DomainIdDTO;
import com.github.jvondoellinger.agp_protocol.application.MentionsDTO;
import com.github.jvondoellinger.agp_protocol.application.ticket.valueObjects.QueueIdDTO;

import java.time.LocalDateTime;

public record TicketQueryResponseDTO(
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
