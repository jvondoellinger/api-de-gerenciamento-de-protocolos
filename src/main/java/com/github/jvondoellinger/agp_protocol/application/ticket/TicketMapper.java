package com.github.jvondoellinger.agp_protocol.application.ticket;

import com.github.jvondoellinger.agp_protocol.application.DomainIdDTO;
import com.github.jvondoellinger.agp_protocol.application.MentionsDTO;
import com.github.jvondoellinger.agp_protocol.application.shared.Mapper;
import com.github.jvondoellinger.agp_protocol.domain.DomainId;
import com.github.jvondoellinger.agp_protocol.domain.interaction.InteractionsHistory;
import com.github.jvondoellinger.agp_protocol.domain.mention.Mentions;
import com.github.jvondoellinger.agp_protocol.domain.profile.UserProfile;
import com.github.jvondoellinger.agp_protocol.domain.queue.Queue;
import com.github.jvondoellinger.agp_protocol.domain.ticket.Ticket;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class TicketMapper implements Mapper<Ticket, CreateTicketRequestDTO, CreateTicketResponseDTO> {
	public TicketMapper() {}

	/**
	 *
	 * @param createTicketRequestDTO
	 * @return Return a mapped ticket
	 * @apiNote UserProfile have only value loaded
	 */
	@Override
	public Ticket mapToEntity(CreateTicketRequestDTO createTicketRequestDTO) {
		var openedBy = userProfileIdOnly(createTicketRequestDTO.openedBy().value());
		var history = new InteractionsHistory();
		var mentionsIds = createTicketRequestDTO.mentions().userIds()
			   .stream()
			   .map(this::userProfileIdOnly)
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

	@Override
	public CreateTicketResponseDTO mapToResponse(Ticket ticket) {
		var mentions = new ArrayList<>(ticket.mentions().readonlyList())
			   .stream()
			   .map(x -> x.getDomainId().value())
			   .toList();

		var queueIdDto = new QueueIdDTO(new DomainIdDTO(ticket.queue().getDomainId().value()));
		var mentionsDto = new MentionsDTO(mentions);
		var openedBy = new DomainIdDTO(ticket.openedBy().getDomainId().value());
		var lastUpdatedBy = new DomainIdDTO(ticket.lastUpdatedBy().getDomainId().value());

		return new CreateTicketResponseDTO(
			   ticket.number().toString(),
			   ticket.title(),
			   queueIdDto,
			   mentionsDto,
			   ticket.deadline(),
			   openedBy,
			   ticket.openedOn(),
			   lastUpdatedBy,
			   ticket.lastUpdatedOn()
		);
	}

	private UserProfile userProfileIdOnly(String id) {
		return new UserProfile(DomainId.parse(id), null, null, null);
	}

	private Queue queueIdOnly(String queueId) {
		return new Queue(
			DomainId.parse(queueId),
			   null,
			   null,
			   null,
			   null,
			   null,
			   null
		);
	}
}
