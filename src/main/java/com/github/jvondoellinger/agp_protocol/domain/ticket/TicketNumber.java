package com.github.jvondoellinger.agp_protocol.domain.ticket;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class TicketNumber {
	private String value;

	private TicketNumber() {
		gen();
	}

	private void gen() {
		String timestamp_pattern = "yyMMddHHmmssSSS";
		LocalDateTime now = LocalDateTime.now();
		DateTimeFormatter format = DateTimeFormatter.ofPattern(timestamp_pattern);

		this.value = now.format(format);
	}

	public static TicketNumber create() {
		return new TicketNumber();
	}

	@Override
	public String toString() {
		return value;
	}
}
