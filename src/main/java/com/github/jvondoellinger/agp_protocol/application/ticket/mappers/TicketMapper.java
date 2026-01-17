package com.github.jvondoellinger.agp_protocol.application.ticket.mappers;

import com.github.jvondoellinger.agp_protocol.application.shared.id.QueueIdDTO;
import com.github.jvondoellinger.agp_protocol.application.ticket.valueObjects.MentionsDTO;
import com.github.jvondoellinger.agp_protocol.application.shared.id.UserProfileIdDTO;
import com.github.jvondoellinger.agp_protocol.application.ticket.CreateTicketRequestDTO;
import com.github.jvondoellinger.agp_protocol.application.ticket.CreateTicketResponseDTO;
import com.github.jvondoellinger.agp_protocol.application.ticket.CollectionTicketQueryResponseDTO;
import com.github.jvondoellinger.agp_protocol.application.ticket.TicketQueryResponseDTO;
import com.github.jvondoellinger.agp_protocol.domain.DomainId;
import com.github.jvondoellinger.agp_protocol.domain.interaction.InteractionsHistory;
import com.github.jvondoellinger.agp_protocol.domain.mention.Mentions;
import com.github.jvondoellinger.agp_protocol.domain.profile.UserProfile;
import com.github.jvondoellinger.agp_protocol.domain.queue.Queue;
import com.github.jvondoellinger.agp_protocol.domain.ticket.Ticket;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import static com.github.jvondoellinger.agp_protocol.application.shared.DtoSharedMapper.*;

@Service
@AllArgsConstructor
public class TicketMapper {
	public Ticket from(CreateTicketRequestDTO createTicketRequestDTO) {
		return new Ticket(
			   createTicketRequestDTO.title(),
			   new InteractionsHistory(),
			   queueIdOnly(createTicketRequestDTO.queueId()),
			   mentions(createTicketRequestDTO),
			   userProfileIdOnly(createTicketRequestDTO.openedBy()),
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





	// Utilities
	private Mentions mentions(CreateTicketRequestDTO createTicketRequestDTO) {
		var profiles = createTicketRequestDTO.mentions()
			   .userIds()
			   .stream()
			   .map(UserProfileIdDTO::new)
			   .map(this::userProfileIdOnly)
			   .toList();
		return new Mentions(profiles);
	}
	private UserProfile userProfileIdOnly(UserProfileIdDTO id) {
		return new UserProfile(DomainId.parse(id.userId()), null, null, null);
	}
	private Queue queueIdOnly(QueueIdDTO queueId) {
		return new Queue(
			   DomainId.parse(queueId.queueId()),
			   null,
			   null,
			   null,
			   null,
			   null,
			   null
		);
	}



	private MentionsDTO mapMentions(Mentions mentions) {
		var userIds = new ArrayList<>(mentions.readonlyList())
			   .stream()
			   .map(x -> x.getUserId().value())
			   .toList();
		return new MentionsDTO(userIds);
	}
	private UserProfileIdDTO mapUserProfileId(UserProfile profile) {
		return profile == null ? null : mapUserProfileIdDTO(profile.getUserId().value());
	}
}
