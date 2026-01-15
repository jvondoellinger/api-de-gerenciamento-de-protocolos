package com.github.jvondoellinger.agp_protocol.infrastructure.entity;

import com.github.jvondoellinger.agp_protocol.domain.DomainId;
import com.github.jvondoellinger.agp_protocol.domain.interaction.Interaction;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.data.annotation.PersistenceCreator;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

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
		this.interactedBy = UserProfileDbEntity.foreignKey(interaction.getInteractedBy().getUserId().value());
		this.interactedOn = interaction.getInteractedOn();
	}

	@PersistenceCreator
	public InteractionDbEntity(String domainId, String text, boolean visible, UserProfileDbEntity interactedBy, LocalDateTime interactedOn) {
		this.domainId = domainId;
		this.text = text;
		this.visible = visible;
		this.interactedBy = interactedBy;
		this.interactedOn = interactedOn;
	}

	protected InteractionDbEntity() {}

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

	public static InteractionDbEntity foreignKey(String id) {
		var interactionDbEntity = new InteractionDbEntity();
		interactionDbEntity.setDomainId(id);
		return interactionDbEntity;
	}

	public static List<InteractionDbEntity> foreignKey(List<String> ids) {
		var interactions = new ArrayList<InteractionDbEntity>();

		InteractionDbEntity interactionDbEntity;

		for (var id : ids) {
			interactionDbEntity = new InteractionDbEntity();
			interactionDbEntity.setDomainId(id);
		}

		return interactions;
	}
}
