package com.github.jvondoellinger.agp_protocol.infrastructure.entity;

import com.github.jvondoellinger.agp_protocol.domain.DomainId;
import com.github.jvondoellinger.agp_protocol.domain.interaction.Interaction;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Entity
@Table(name = "tb_interaction")
@Getter
@Setter
public class InteractionDbEntity implements DbEntity<Interaction> {
	@Id
	private String domainId;
	private String text;

	private boolean visible;

	@ManyToOne
	private UserProfileDbEntity interactedBy;
	@CreationTimestamp
	private LocalDateTime interactedOn;

	public InteractionDbEntity(Interaction interaction) {
		this.domainId = interaction.getId().toString();
		this.text = interaction.getText();
		this.visible = interaction.isVisible();
		this.interactedBy = new UserProfileDbEntity(interaction.getInteractedBy());
		this.interactedOn = interaction.getInteractedOn();
	}

	@Override
	public Interaction toDomainEntity() {
		return new Interaction(
			   DomainId.parse(domainId),
			   text,
			   visible,
			   interactedBy.toDomainEntity(),
			   interactedOn
		);
	}
}
