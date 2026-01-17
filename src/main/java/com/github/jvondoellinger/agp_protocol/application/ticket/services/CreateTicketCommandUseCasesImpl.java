package com.github.jvondoellinger.agp_protocol.application.ticket.services;

import com.github.jvondoellinger.agp_protocol.application.ticket.CreateTicketRequestDTO;
import com.github.jvondoellinger.agp_protocol.application.ticket.CreateTicketResponseDTO;
import com.github.jvondoellinger.agp_protocol.application.ticket.mappers.TicketMapper;
import com.github.jvondoellinger.agp_protocol.application.ticket.useCases.CreateTicketCommandUseCases;
import com.github.jvondoellinger.agp_protocol.domain.ticket.TicketRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CreateTicketCommandUseCasesImpl implements CreateTicketCommandUseCases {
	private final TicketRepository repository;
	private final TicketMapper mapper;

	@Override
	public CreateTicketResponseDTO execute(CreateTicketRequestDTO createTicketRequestDTO) {
		var mappedTicket = mapper.from(createTicketRequestDTO);
		var ticket = repository.save(mappedTicket);
		return mapper.toCreateResponse(ticket);
	}
}
