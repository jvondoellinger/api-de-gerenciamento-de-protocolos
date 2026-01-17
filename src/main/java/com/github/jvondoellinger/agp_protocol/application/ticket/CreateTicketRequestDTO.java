package com.github.jvondoellinger.agp_protocol.application.ticket;

import com.github.jvondoellinger.agp_protocol.application.ticket.valueObjects.MentionsDTO;
import com.github.jvondoellinger.agp_protocol.application.shared.id.QueueIdDTO;
import com.github.jvondoellinger.agp_protocol.application.shared.id.UserProfileIdDTO;

import java.time.LocalDateTime;

public record CreateTicketRequestDTO(
	   String title,
	   QueueIdDTO queueId,
	   MentionsDTO mentions,
	   LocalDateTime deadline,
	   UserProfileIdDTO openedBy) {
}
