package com.github.jvondoellinger.agp_protocol.domain.interaction;

import com.github.jvondoellinger.agp_protocol.domain.DomainId;
import com.github.jvondoellinger.agp_protocol.domain.profile.UserProfile;

import java.time.LocalDateTime;

public class Interaction {
	private final DomainId id;
	private final String text;

	private final boolean visible;

	private final UserProfile interactedBy;
	private final LocalDateTime interactedOn;

	public Interaction(DomainId id, String text, boolean visible, UserProfile interactedBy, LocalDateTime interactedOn) {
		this.id = id;
		this.text = text;
		this.visible = visible;
		this.interactedBy = interactedBy;
		this.interactedOn = interactedOn;
	}

	public Interaction(String text, boolean visible, UserProfile interactedBy) {
		this.id = DomainId.create();
		this.text = text;
		this.visible = visible;
		this.interactedBy = interactedBy;
		this.interactedOn = LocalDateTime.now();
	}
	public Interaction(String text, UserProfile interactedBy) {
		this.id = DomainId.create();
		this.text = text;
		this.visible = true;
		this.interactedBy = interactedBy;
		this.interactedOn = LocalDateTime.now();
	}

	public DomainId getId() {
		return id;
	}

	public String getText() {
		return text;
	}

	public boolean isVisible() {
		return visible;
	}

	public UserProfile getInteractedBy() {
		return interactedBy;
	}

	public LocalDateTime getInteractedOn() {
		return interactedOn;
	}
}
