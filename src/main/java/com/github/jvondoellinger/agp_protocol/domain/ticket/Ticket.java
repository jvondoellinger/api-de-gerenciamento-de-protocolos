package com.github.jvondoellinger.agp_protocol.domain.ticket;

import com.github.jvondoellinger.agp_protocol.domain.mention.Mentions;
import com.github.jvondoellinger.agp_protocol.domain.interaction.InteractionsHistory;
import com.github.jvondoellinger.agp_protocol.domain.profile.UserProfile;
import com.github.jvondoellinger.agp_protocol.domain.queue.Queue;

import java.time.LocalDateTime;

public class Ticket {
	public Ticket(TicketNumber number,
			    String title,
			    InteractionsHistory history, Queue queue,
			    Mentions mentions,
			    LocalDateTime deadline,
			    UserProfile openedBy,
			    LocalDateTime openedOn,
			    UserProfile lastUpdatedBy,
			    LocalDateTime lastUpdatedOn) {
		this.number = number;
		this.title = title;
		this.history = history;
		this.queue = queue;
		this.openedBy = openedBy;
		this.lastUpdatedBy = lastUpdatedBy;
		this.deadline = deadline;
		this.mentions = mentions;
		this.openedOn = openedOn;
		this.lastUpdatedOn = lastUpdatedOn;
	}

	public Ticket(String title,
			    InteractionsHistory history,
			    Queue queue,
			    Mentions mentions,
			    UserProfile openedBy,
			    LocalDateTime deadline) {
		this.number = TicketNumber.create();
		this.title = title;
		this.queue = queue;
		this.openedBy = openedBy;
		this.deadline = deadline;
		this.mentions = mentions;
		this.history = history;
		this.openedOn = LocalDateTime.now();
		this.lastUpdatedOn = null;
		this.lastUpdatedBy = null;
	}

	private final TicketNumber number;
	private final String title;
	private final InteractionsHistory history;
	private final Queue queue;
	private final LocalDateTime deadline;
	private final Mentions mentions;

	private final LocalDateTime openedOn;
	private final UserProfile openedBy;
	private final LocalDateTime lastUpdatedOn;
	private final UserProfile lastUpdatedBy;

	public TicketNumber number() {
		return number;
	}
	public String title() {
		return title;
	}
	public InteractionsHistory history() {
		return history;
	}
	public Queue queue() {
		return queue;
	}
	public LocalDateTime deadline() {
		return deadline;
	}
	public Mentions mentions() {
		return mentions;
	}
	public LocalDateTime openedOn() {
		return openedOn;
	}
	public UserProfile openedBy() {
		return openedBy;
	}
	public LocalDateTime lastUpdatedOn() {
		return lastUpdatedOn;
	}
	public UserProfile lastUpdatedBy() {
		return lastUpdatedBy;
	}
}
