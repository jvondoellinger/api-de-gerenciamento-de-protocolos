package com.github.jvondoellinger.agp_protocol.application.ticket.services;

import com.github.jvondoellinger.agp_protocol.application.ticket.CreateTicketRequestDTO;
import com.github.jvondoellinger.agp_protocol.application.ticket.CreateTicketResponseDTO;
import com.github.jvondoellinger.agp_protocol.application.ticket.TicketMapper;
import com.github.jvondoellinger.agp_protocol.application.ticket.useCases.CreateTicketUseCases;
import com.github.jvondoellinger.agp_protocol.domain.ticket.TicketRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CreateTicketUseCasesImpl implements CreateTicketUseCases {
	private final TicketRepository repository;
	private final TicketMapper mapper;

	@Override
	public CreateTicketResponseDTO execute(CreateTicketRequestDTO createTicketRequestDTO) {
		var mappedTicket = mapper.mapToEntity(createTicketRequestDTO);
		var ticket = repository.save(mappedTicket);

		// Map and return de response
		return mapper.mapToResponse(ticket);
	}
}
