package com.github.jvondoellinger.agp_protocol.application.ticket.useCases;

import com.github.jvondoellinger.agp_protocol.application.ticket.CreateTicketRequestDTO;
import com.github.jvondoellinger.agp_protocol.application.ticket.CreateTicketResponseDTO;

public interface TicketUseCases {
	CreateTicketResponseDTO create(CreateTicketRequestDTO createTicketRequestDTO);
}
