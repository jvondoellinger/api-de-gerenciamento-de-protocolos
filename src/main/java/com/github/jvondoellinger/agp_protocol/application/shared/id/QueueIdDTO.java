package com.github.jvondoellinger.agp_protocol.application.shared.id;

public record QueueIdDTO (String queueId) implements DomainIdDTO {
	@Override
	public String id() {
		return queueId;
	}
}
