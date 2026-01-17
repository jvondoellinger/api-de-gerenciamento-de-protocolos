package com.github.jvondoellinger.agp_protocol.application.ticket.services;

import com.github.jvondoellinger.agp_protocol.application.shared.id.DomainIdDTO;
import com.github.jvondoellinger.agp_protocol.application.ticket.TicketQueryResponseDTO;
import com.github.jvondoellinger.agp_protocol.application.ticket.mappers.TicketMapper;
import com.github.jvondoellinger.agp_protocol.application.ticket.useCases.TicketQueryUseCases;
import com.github.jvondoellinger.agp_protocol.domain.DomainId;
import com.github.jvondoellinger.agp_protocol.domain.shared.QueryFilter;
import com.github.jvondoellinger.agp_protocol.domain.ticket.TicketRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class TicketQueryUseCasesImpl implements TicketQueryUseCases {
	private final TicketRepository repository;
	private final TicketMapper mapper;

	@Override
	public TicketQueryResponseDTO queryById(DomainIdDTO domainIdDTO) {
		var result = repository.queryById(DomainId.parse(domainIdDTO.value()));
		return mapper.toQueryResponse(result);
	}

	@Override
	public List<TicketQueryResponseDTO> queryByPagination(QueryFilter filter) {
		var result = repository.query(QueryFilter.of(100, 0));
		return mapper.toQueryCollectionResponse(result).responses();
	}
}
