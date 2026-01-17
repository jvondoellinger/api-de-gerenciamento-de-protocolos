package com.github.jvondoellinger.agp_protocol.adapters.inbound;

import com.github.jvondoellinger.agp_protocol.application.ticket.CreateTicketRequestDTO;
import com.github.jvondoellinger.agp_protocol.application.ticket.CreateTicketResponseDTO;
import com.github.jvondoellinger.agp_protocol.application.ticket.TicketQueryResponseDTO;
import com.github.jvondoellinger.agp_protocol.application.ticket.useCases.CreateTicketCommandUseCases;
import com.github.jvondoellinger.agp_protocol.application.ticket.useCases.TicketQueryUseCases;
import com.github.jvondoellinger.agp_protocol.domain.shared.QueryFilter;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/ticket")
@AllArgsConstructor
public class TicketController {
	private final CreateTicketCommandUseCases createTicketUseCases;
	private final TicketQueryUseCases ticketQueryUseCases;

	@GetMapping
	public List<TicketQueryResponseDTO> get() {
		return ticketQueryUseCases.queryByPagination(QueryFilter.of(0, 100));
	}

	@PostMapping
	public CreateTicketResponseDTO create(@RequestBody CreateTicketRequestDTO requestDTO) {
		return createTicketUseCases.execute(requestDTO);
	}
}
