package com.github.jvondoellinger.agp_protocol.application.ticket.services;

import com.github.jvondoellinger.agp_protocol.application.ticket.QueryTicketRequestDTO;
import com.github.jvondoellinger.agp_protocol.application.ticket.QueryTicketResponseDTO;
import com.github.jvondoellinger.agp_protocol.application.ticket.TicketMapper;
import com.github.jvondoellinger.agp_protocol.application.ticket.useCases.QueryTicketUseCases;
import com.github.jvondoellinger.agp_protocol.domain.shared.QueryFilter;
import com.github.jvondoellinger.agp_protocol.domain.ticket.TicketRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class QueryTicketUseCasesImpl implements QueryTicketUseCases {
	private final TicketRepository repository;
	private final TicketMapper mapper;
	@Override
	public List<QueryTicketResponseDTO> execute(QueryTicketRequestDTO queryTicketRequestDTO) {
		var result = repository.query(QueryFilter.of(100, 0));
		return mapper.mapToResponse(result);
	}
}
