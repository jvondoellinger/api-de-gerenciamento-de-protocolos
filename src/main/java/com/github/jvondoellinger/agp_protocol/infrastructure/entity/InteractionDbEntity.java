package com.github.jvondoellinger.agp_protocol.infrastructure.entity;

import com.github.jvondoellinger.agp_protocol.domain.DomainId;
import com.github.jvondoellinger.agp_protocol.domain.interaction.Interaction;
import com.github.jvondoellinger.agp_protocol.domain.interaction.InteractionsHistory;
import com.github.jvondoellinger.agp_protocol.domain.mention.Mentions;
import com.github.jvondoellinger.agp_protocol.domain.profile.UserProfile;
import com.github.jvondoellinger.agp_protocol.domain.ticket.Ticket;
import com.github.jvondoellinger.agp_protocol.domain.ticket.TicketNumber;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Entity
@Table(name = "tb_interaction")
public class InteractionDbEntity implements DbEntity<Interaction> {
	@Id
	private DomainId id;
	private String text;

	private boolean visible;

	@ManyToOne
	private UserProfileDbEntity interactedBy;
	@CreationTimestamp
	private LocalDateTime interactedOn;

	public InteractionDbEntity(Interaction interaction) {
		this.id = interaction.getId();
		this.text = interaction.getText();
		this.visible = interaction.isVisible();
		this.interactedBy = new UserProfileDbEntity(interaction.getInteractedBy());
		this.interactedOn = interaction.getInteractedOn();
	}

	public void setVisible(boolean visible) {
		this.visible = visible;
	}
	public void setId(DomainId id) {
		this.id = id;
	}
	public void setText(String text) {
		this.text = text;
	}
	public void setInteractedBy(UserProfileDbEntity interactedBy) {
		this.interactedBy = interactedBy;
	}
	public void setInteractedOn(LocalDateTime interactedOn) {
		this.interactedOn = interactedOn;
	}

	public boolean isVisible() {
		return visible;
	}
	public DomainId getId() {
		return id;
	}
	public String getText() {
		return text;
	}
	public UserProfileDbEntity getInteractedBy() {
		return interactedBy;
	}
	public LocalDateTime getInteractedOn() {
		return interactedOn;
	}

	@Override
	public Interaction toDomainEntity() {
		return new Interaction(
			   id,
			   text,
			   visible,
			   interactedBy.toDomainEntity(),
			   interactedOn
		);
	}
}
