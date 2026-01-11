package com.github.jvondoellinger.agp_protocol.domain.ticket;

import com.github.jvondoellinger.agp_protocol.domain.DomainException;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class TicketNumber {
	private static final String TIMESTAMP_PATTERN = "yyMMddHHmmssSSS";
	protected static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern(TIMESTAMP_PATTERN);

	private String value;

	private TicketNumber() {
		gen();
	}
	private TicketNumber(String value) {
		this.value = value;
	}

	private void gen() {
		LocalDateTime now = LocalDateTime.now();
		this.value = now.format(formatter);
	}

	public static TicketNumber create() {
		return new TicketNumber();
	}

	public static TicketNumber parse(String n) {
		LocalDateTime time = LocalDateTime.parse(n, formatter);

		if (time.isAfter(LocalDateTime.now())) throw new DomainException("The ticket number received is invalid and could not be analyzed!");

		return new TicketNumber(n);
	}

	@Override
	public String toString() {
		return value;
	}
}
