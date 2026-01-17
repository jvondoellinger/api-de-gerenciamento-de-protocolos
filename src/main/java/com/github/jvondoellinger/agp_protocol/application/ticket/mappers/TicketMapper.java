package com.github.jvondoellinger.agp_protocol.application.ticket.mappers;

import com.github.jvondoellinger.agp_protocol.application.ticket.CreateTicketRequestDTO;
import com.github.jvondoellinger.agp_protocol.application.ticket.CreateTicketResponseDTO;
import com.github.jvondoellinger.agp_protocol.application.ticket.CollectionTicketQueryResponseDTO;
import com.github.jvondoellinger.agp_protocol.application.ticket.TicketQueryResponseDTO;
import com.github.jvondoellinger.agp_protocol.domain.interaction.InteractionsHistory;
import com.github.jvondoellinger.agp_protocol.domain.mention.Mentions;
import com.github.jvondoellinger.agp_protocol.domain.ticket.Ticket;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.github.jvondoellinger.agp_protocol.application.shared.DtoSharedMapper.*;

@Service
@AllArgsConstructor
public class TicketMapper {
	public Ticket from(CreateTicketRequestDTO createTicketRequestDTO) {
		var openedBy = userProfileIdOnly(createTicketRequestDTO.openedBy().value());
		var history = new InteractionsHistory();
		var mentionsIds = createTicketRequestDTO.mentions().userIds()
			   .stream()
			   .map(x -> userProfileIdOnly(x.value()))
			   .toList();
		var queue = queueIdOnly(createTicketRequestDTO.queueId().id().value());
		var mentions = new Mentions(mentionsIds);

		return new Ticket(
			   createTicketRequestDTO.title(),
			   history,
			   queue,
			   mentions,
			   openedBy,
			   createTicketRequestDTO.deadline()
		);
	}

	public CreateTicketResponseDTO toCreateResponse(Ticket t) {
		var mentions = mapMentions(t.mentions());
		var queueIdDto = mapQueueId(t.queue().getDomainId());
		var openedBy = mapUserProfileId(t.openedBy());
		var lastUpdatedBy = mapUserProfileId(t.lastUpdatedBy());

		return new CreateTicketResponseDTO(
			   t.number().toString(),
			   t.title(),
			   queueIdDto,
			   mentions,
			   t.deadline(),
			   openedBy,
			   t.openedOn(),
			   lastUpdatedBy,
			   t.lastUpdatedOn()
		);
	}
	public TicketQueryResponseDTO toQueryResponse(Ticket t) {
		return new TicketQueryResponseDTO(
			   t.number().toString(),
			   t.title(),
			   mapQueueId(t.queue().getDomainId()),
			   mapMentions(t.mentions()),
			   t.deadline(),
			   mapUserProfileId(t.openedBy()),
			   t.openedOn(),
			   mapUserProfileId(t.lastUpdatedBy()),
			   t.lastUpdatedOn()
		);
	}
	public CollectionTicketQueryResponseDTO toQueryCollectionResponse(List<Ticket> t) {
		var responses = t.stream().map(this::toQueryResponse).toList();
		return new CollectionTicketQueryResponseDTO(responses);
	}
}
