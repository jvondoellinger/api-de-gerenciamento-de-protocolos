package com.github.jvondoellinger.agp_protocol.adapters.inbound;

import com.github.jvondoellinger.agp_protocol.application.ticket.CreateTicketRequestDTO;
import com.github.jvondoellinger.agp_protocol.application.ticket.CreateTicketResponseDTO;
import com.github.jvondoellinger.agp_protocol.application.ticket.QueryTicketRequestDTO;
import com.github.jvondoellinger.agp_protocol.application.ticket.QueryTicketResponseDTO;
import com.github.jvondoellinger.agp_protocol.application.ticket.useCases.CreateTicketUseCases;
import com.github.jvondoellinger.agp_protocol.application.ticket.useCases.QueryTicketUseCases;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/ticket")
@AllArgsConstructor
public class TicketController {
	private final CreateTicketUseCases createTicketUseCases;
	private final QueryTicketUseCases queryTicketUseCases;

	@GetMapping
	public List<QueryTicketResponseDTO> get() {
		return queryTicketUseCases.execute(new QueryTicketRequestDTO());
	}

	@PostMapping
	public CreateTicketResponseDTO create(@RequestBody CreateTicketRequestDTO requestDTO) {
		return createTicketUseCases.execute(requestDTO);
	}
}
