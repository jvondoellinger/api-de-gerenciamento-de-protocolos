package com.github.jvondoellinger.agp_protocol.application.ticket.valueObjects;

import com.github.jvondoellinger.agp_protocol.application.shared.id.DomainIdCollectionDTO;

import java.util.List;

public record MentionsDTO(List<String> userIds) implements DomainIdCollectionDTO {
	@Override
	public List<String> userIds() {
		return userIds;
	}
}
