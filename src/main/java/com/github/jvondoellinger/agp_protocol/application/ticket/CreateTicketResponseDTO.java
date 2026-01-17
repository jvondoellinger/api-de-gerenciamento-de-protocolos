package com.github.jvondoellinger.agp_protocol.application.ticket;

import com.github.jvondoellinger.agp_protocol.application.shared.id.DomainIdDTO;
import com.github.jvondoellinger.agp_protocol.application.shared.id.UserProfileIdDTO;
import com.github.jvondoellinger.agp_protocol.application.ticket.valueObjects.MentionsDTO;
import com.github.jvondoellinger.agp_protocol.application.shared.id.QueueIdDTO;

import java.time.LocalDateTime;

public record CreateTicketResponseDTO(
	   String ticketNumber,
	   String title,
	   QueueIdDTO queueId,
	   MentionsDTO mentions,
	   LocalDateTime deadline,
	   UserProfileIdDTO openedBy,
	   LocalDateTime openedOn,
	   UserProfileIdDTO lastUpdatedBy,
	   LocalDateTime lastUpdatedOn
	   ) {
}
