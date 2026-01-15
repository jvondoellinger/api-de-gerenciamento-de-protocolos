package com.github.jvondoellinger.agp_protocol.application.ticket.useCases;

import com.github.jvondoellinger.agp_protocol.application.shared.UseCase;
import com.github.jvondoellinger.agp_protocol.application.ticket.CreateTicketRequestDTO;
import com.github.jvondoellinger.agp_protocol.application.ticket.QueryTicketRequestDTO;
import com.github.jvondoellinger.agp_protocol.application.ticket.QueryTicketResponseDTO;

import java.util.List;

public interface QueryTicketUseCases extends UseCase<QueryTicketRequestDTO, List<QueryTicketResponseDTO>> {
}
