package com.github.jvondoellinger.agp_protocol.application.shared.id;

public record UserProfileIdDTO(String userId) implements DomainIdDTO {
	@Override
	public String id() {
		return userId;
	}
}
