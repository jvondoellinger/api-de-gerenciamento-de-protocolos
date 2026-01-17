package com.github.jvondoellinger.agp_protocol.application.shared;

import com.github.jvondoellinger.agp_protocol.application.ticket.valueObjects.MentionsDTO;
import com.github.jvondoellinger.agp_protocol.application.shared.id.QueueIdDTO;
import com.github.jvondoellinger.agp_protocol.application.shared.id.UserProfileIdDTO;
import com.github.jvondoellinger.agp_protocol.domain.DomainId;

import java.util.List;

public class DtoSharedMapper {
	private DtoSharedMapper() {}

	public static QueueIdDTO mapQueueId(DomainId id) {
		return new QueueIdDTO(id.value());
	}
	public static UserProfileIdDTO mapUserProfileIdDTO(String userId) {
		return new UserProfileIdDTO(userId);
	}
}
