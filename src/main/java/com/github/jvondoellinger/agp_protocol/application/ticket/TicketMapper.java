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
import java.util.List;

@Service
public class TicketMapper implements Mapper<Ticket, CreateTicketRequestDTO, CreateTicketResponseDTO> {
	public TicketMapper() {
	}

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

	@Override
	public CreateTicketResponseDTO mapToResponse(Ticket ticket) {
		var mentions = mapMentions(ticket.mentions());
		var queueIdDto = mapQueueId(ticket.queue().getDomainId());
		var openedBy = new DomainIdDTO(ticket.openedBy().getUserId().value());
		var lastUpdatedBy = mapLastUpdatedBy(ticket.lastUpdatedBy());

		return new CreateTicketResponseDTO(
			   ticket.number().toString(),
			   ticket.title(),
			   queueIdDto,
			   mentions,
			   ticket.deadline(),
			   openedBy,
			   ticket.openedOn(),
			   lastUpdatedBy,
			   ticket.lastUpdatedOn()
		);
	}

	public List<QueryTicketResponseDTO> mapToResponse(List<Ticket> tickets) {
		if (tickets == null || tickets.isEmpty()) {
			return new ArrayList<>(0);
		}

		return tickets
			   .stream()
			   .map(ticket -> {
				   var mentions = mapMentions(ticket.mentions());
				   var queueIdDto = mapQueueId(ticket.queue().getDomainId());
				   var openedBy = new DomainIdDTO(ticket.openedBy().getUserId().value());
				   var lastUpdatedBy = mapLastUpdatedBy(ticket.lastUpdatedBy());

				   return new QueryTicketResponseDTO(
						 ticket.number().toString(),
						 ticket.title(),
						 queueIdDto,
						 mentions,
						 ticket.deadline(),
						 openedBy,
						 ticket.openedOn(),
						 lastUpdatedBy,
						 ticket.lastUpdatedOn()
				   );
			   })
			   .toList();
	}

	private DomainIdDTO mapLastUpdatedBy(UserProfile profile) {
		return profile == null ? null : mapId(profile.getUserId());
	}

	private DomainIdDTO mapId(DomainId id) {
		return new DomainIdDTO(id.value());
	}

	private QueueIdDTO mapQueueId(DomainId id) {
		return new QueueIdDTO(mapId(id));
	}

	private MentionsDTO mapMentions(Mentions mentions) {
		var userIds = new ArrayList<>(mentions.readonlyList())
			   .stream()
			   .map(x -> x.getUserId().value())
			   .map(DomainIdDTO::new)
			   .toList();
		return new MentionsDTO(userIds);
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
