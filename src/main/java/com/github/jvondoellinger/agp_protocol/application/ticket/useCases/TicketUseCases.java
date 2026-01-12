package com.github.jvondoellinger.agp_protocol.application.ticket.useCases;

import com.github.jvondoellinger.agp_protocol.application.shared.UseCase;
import com.github.jvondoellinger.agp_protocol.application.ticket.CreateTicketRequestDTO;
import com.github.jvondoellinger.agp_protocol.application.ticket.CreateTicketResponseDTO;

public interface TicketUseCases extends UseCase<CreateTicketRequestDTO, CreateTicketResponseDTO> {
}
