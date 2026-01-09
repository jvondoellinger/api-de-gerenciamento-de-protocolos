package com.github.jvondoellinger.agp_protocol.domain.ticket;

import com.github.jvondoellinger.agp_protocol.domain.mention.Mentions;
import com.github.jvondoellinger.agp_protocol.domain.interaction.InteractionsHistory;
import com.github.jvondoellinger.agp_protocol.domain.profile.UserProfile;

import java.time.LocalDateTime;

public class Ticket {
	public Ticket(TicketNumber number,
			    String title,
			    InteractionsHistory history,
			    Mentions mentions,
			    LocalDateTime deadline,
			    UserProfile openedBy,
			    LocalDateTime openedOn,
			    UserProfile lastUpdatedBy,
			    LocalDateTime lastUpdatedOn) {
		this.number = number;
		this.title = title;
		this.history = history;
		this.openedBy = openedBy;
		this.lastUpdatedBy = lastUpdatedBy;
		this.deadline = deadline;
		this.mentions = mentions;
		this.openedOn = openedOn;
		this.lastUpdatedOn = lastUpdatedOn;
	}

	public Ticket(String title,
			    InteractionsHistory history,
			    Mentions mentions,
			    UserProfile openedBy,
			    LocalDateTime deadline) {
		this.number = TicketNumber.create();
		this.title = title;
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

	private final LocalDateTime deadline;
	private final Mentions mentions;

	private final LocalDateTime openedOn;
	private final UserProfile openedBy;
	private final LocalDateTime lastUpdatedOn;
	private final UserProfile lastUpdatedBy;

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

	public Mentions getMentions() {
		return mentions;
	}

	public LocalDateTime getOpenedOn() {
		return openedOn;
	}

	public UserProfile getOpenedBy() {
		return openedBy;
	}

	public LocalDateTime getLastUpdatedOn() {
		return lastUpdatedOn;
	}

	public UserProfile getLastUpdatedBy() {
		return lastUpdatedBy;
	}
}
