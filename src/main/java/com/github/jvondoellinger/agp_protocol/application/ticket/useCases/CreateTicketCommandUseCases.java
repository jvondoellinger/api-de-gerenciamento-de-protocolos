package com.github.jvondoellinger.agp_protocol.application.ticket.useCases;

import com.github.jvondoellinger.agp_protocol.application.shared.CommandUseCase;
import com.github.jvondoellinger.agp_protocol.application.ticket.CreateTicketRequestDTO;
import com.github.jvondoellinger.agp_protocol.application.ticket.CreateTicketResponseDTO;

public interface CreateTicketCommandUseCases extends CommandUseCase<CreateTicketRequestDTO, CreateTicketResponseDTO> {
}
