package com.github.jvondoellinger.agp_protocol.application.shared;

import com.github.jvondoellinger.agp_protocol.application.DomainIdDTO;
import com.github.jvondoellinger.agp_protocol.application.MentionsDTO;
import com.github.jvondoellinger.agp_protocol.application.ticket.valueObjects.QueueIdDTO;
import com.github.jvondoellinger.agp_protocol.domain.DomainId;
import com.github.jvondoellinger.agp_protocol.domain.mention.Mentions;
import com.github.jvondoellinger.agp_protocol.domain.profile.UserProfile;
import com.github.jvondoellinger.agp_protocol.domain.queue.Queue;

import java.util.ArrayList;

public class DtoSharedMapper {
	private DtoSharedMapper() {}

	public static DomainIdDTO mapUserProfileId(UserProfile profile) {
		return profile == null ? null : mapId(profile.getUserId());
	}
	public static  DomainIdDTO mapId(DomainId id) {
		return new DomainIdDTO(id.value());
	}
	public static  QueueIdDTO mapQueueId(DomainId id) {
		return new QueueIdDTO(mapId(id));
	}
	public static  MentionsDTO mapMentions(Mentions mentions) {
		var userIds = new ArrayList<>(mentions.readonlyList())
			   .stream()
			   .map(x -> x.getUserId().value())
			   .map(DomainIdDTO::new)
			   .toList();
		return new MentionsDTO(userIds);
	}
	public static  UserProfile userProfileIdOnly(String id) {
		return new UserProfile(DomainId.parse(id), null, null, null);
	}
	public static  Queue queueIdOnly(String queueId) {
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
