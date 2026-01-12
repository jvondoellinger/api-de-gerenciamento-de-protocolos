package com.github.jvondoellinger.agp_protocol.application.shared;

import com.github.jvondoellinger.agp_protocol.application.ticket.CreateTicketRequestDTO;
import com.github.jvondoellinger.agp_protocol.application.ticket.CreateTicketResponseDTO;

public interface UseCase<TRequest, TResponse> {
	TResponse create(TRequest request);
}
