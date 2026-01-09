package com.github.jvondoellinger.agp_protocol.infrastructure.entity;

import com.github.jvondoellinger.agp_protocol.domain.interaction.InteractionsHistory;
import com.github.jvondoellinger.agp_protocol.domain.mention.Mentions;
import com.github.jvondoellinger.agp_protocol.domain.ticket.Ticket;
import com.github.jvondoellinger.agp_protocol.domain.ticket.TicketNumber;
import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "tb_tickets")
public class TicketDbEntity implements DbEntity<Ticket> {
	@Id
	private TicketNumber number;

	private String title;
	private InteractionsHistory history;
	private LocalDateTime deadline;

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
		var mentions = (new ArrayList<>(ticket.getMentions().readonlyList()))
			   .stream()
			   .map(UserProfileDbEntity::new)
			   .toList();

		this.number = ticket.getNumber();
		this.title = ticket.getTitle();
		this.history = ticket.getHistory();
		this.deadline = ticket.getDeadline();
		this.mentions = mentions;
		this.openedBy = new UserProfileDbEntity(ticket.getOpenedBy());
		this.openedOn = ticket.getOpenedOn();
		this.lastUpdatedBy = new UserProfileDbEntity(ticket.getLastUpdatedBy());
		this.lastUpdatedOn = ticket.getLastUpdatedOn();
	}

	public TicketNumber getNumber() {
		return number;
	}
	public String getTitle() {
		return title;
	}
	public InteractionsHistory getHistory() {
		return history;
	}
	public LocalDateTime getDeadline() {
		return deadline;
	}
	public List<UserProfileDbEntity> getMentions() {
		return mentions;
	}
	public LocalDateTime getOpenedOn() {
		return openedOn;
	}
	public UserProfileDbEntity getOpenedBy() {
		return openedBy;
	}
	public LocalDateTime getLastUpdatedOn() {
		return lastUpdatedOn;
	}
	public UserProfileDbEntity getLastUpdatedBy() {
		return lastUpdatedBy;
	}

	public void setNumber(TicketNumber number) {
		this.number = number;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public void setHistory(InteractionsHistory history) {
		this.history = history;
	}
	public void setDeadline(LocalDateTime deadline) {
		this.deadline = deadline;
	}
	public void setMentions(List<UserProfileDbEntity> mentions) {
		this.mentions = mentions;
	}
	public void setOpenedOn(LocalDateTime openedOn) {
		this.openedOn = openedOn;
	}
	public void setOpenedBy(UserProfileDbEntity openedBy) {
		this.openedBy = openedBy;
	}
	public void setLastUpdatedOn(LocalDateTime lastUpdatedOn) {
		this.lastUpdatedOn = lastUpdatedOn;
	}
	public void setLastUpdatedBy(UserProfileDbEntity lastUpdatedBy) {
		this.lastUpdatedBy = lastUpdatedBy;
	}

	@Override
	public Ticket toDomainEntity() {
		var mentionsEntity = mentions.stream().map(DbEntity::toDomainEntity).toList();
		return new Ticket(
			   number,
			   title,
			   history,
			   new Mentions(mentionsEntity),
			   deadline,
			   openedBy.toDomainEntity(),
			   openedOn,
			   lastUpdatedBy.toDomainEntity(),
			   lastUpdatedOn

		);
	}
}
