package com.github.jvondoellinger.agp_protocol.adapters.inbound;

import com.github.jvondoellinger.agp_protocol.application.ticket.CreateTicketRequestDTO;
import com.github.jvondoellinger.agp_protocol.application.ticket.CreateTicketResponseDTO;
import com.github.jvondoellinger.agp_protocol.application.ticket.useCases.TicketUseCases;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/ticket")
@AllArgsConstructor
public class TicketController {
	private final TicketUseCases ticketUseCases;

	@PostMapping
	public CreateTicketResponseDTO create(@RequestBody CreateTicketRequestDTO requestDTO) {
		return ticketUseCases.create(requestDTO);
	}
}
