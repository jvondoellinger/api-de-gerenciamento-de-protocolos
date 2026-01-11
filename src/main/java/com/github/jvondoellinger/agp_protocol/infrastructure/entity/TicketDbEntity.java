package com.github.jvondoellinger.agp_protocol.infrastructure.entity;

import com.github.jvondoellinger.agp_protocol.domain.interaction.InteractionsHistory;
import com.github.jvondoellinger.agp_protocol.domain.mention.Mentions;
import com.github.jvondoellinger.agp_protocol.domain.ticket.Ticket;
import com.github.jvondoellinger.agp_protocol.domain.ticket.TicketNumber;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "tb_tickets")
@Getter
@Setter
public class TicketDbEntity implements DbEntity<Ticket> {
	@Id
	private TicketNumber number;

	private String title;
	private InteractionsHistory history;
	private LocalDateTime deadline;

	@ManyToOne
	private QueueDbEntity queue;

	@ManyToMany
	private List<UserProfileDbEntity> mentions;

	@CreationTimestamp
	private LocalDateTime openedOn;
	@ManyToOne
	private UserProfileDbEntity openedBy;

	@UpdateTimestamp
	private LocalDateTime lastUpdatedOn;
	@ManyToOne
	private UserProfileDbEntity lastUpdatedBy;

	public TicketDbEntity(Ticket ticket) {
		var mentions = (new ArrayList<>(ticket.mentions().readonlyList()))
			   .stream()
			   .map(UserProfileDbEntity::new)
			   .toList();

		this.number = ticket.number();
		this.title = ticket.title();
		this.history = ticket.history();
		this.deadline = ticket.deadline();
		this.mentions = mentions;
		this.openedBy = new UserProfileDbEntity(ticket.openedBy());
		this.openedOn = ticket.openedOn();
		this.lastUpdatedBy = new UserProfileDbEntity(ticket.lastUpdatedBy());
		this.lastUpdatedOn = ticket.lastUpdatedOn();
	}

	@Override
	public Ticket toDomainEntity() {
		var mentionsEntity = mentions.stream().map(DbEntity::toDomainEntity).toList();
		return new Ticket(
			   number,
			   title,
			   history,
			   queue.toDomainEntity(),
			   new Mentions(mentionsEntity),
			   deadline,
			   openedBy.toDomainEntity(),
			   openedOn,
			   lastUpdatedBy.toDomainEntity(),
			   lastUpdatedOn
		);
	}
}
