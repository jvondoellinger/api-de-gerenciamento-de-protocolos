package com.github.jvondoellinger.agp_protocol.application.ticket;

import java.util.List;

public record CollectionTicketQueryResponseDTO(
	   List<TicketQueryResponseDTO> responses) {
}
