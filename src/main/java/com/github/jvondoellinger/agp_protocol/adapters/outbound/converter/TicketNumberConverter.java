package com.github.jvondoellinger.agp_protocol.adapters.outbound.converter;

import com.github.jvondoellinger.agp_protocol.domain.ticket.TicketNumber;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter
public class TicketNumberConverter implements AttributeConverter<TicketNumber, String> {
	@Override
	public String convertToDatabaseColumn(TicketNumber ticketNumber) {
		return ticketNumber.toString();
	}

	@Override
	public TicketNumber convertToEntityAttribute(String s) {
		return TicketNumber.parse(s);
	}
}
