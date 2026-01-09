package com.github.jvondoellinger.agp_protocol.domain.interaction;

import com.github.jvondoellinger.agp_protocol.domain.DomainCollection;

import java.util.ArrayList;
import java.util.List;

public class InteractionsHistory extends DomainCollection<Interaction> {
	public InteractionsHistory(List<Interaction> values) {
		super(values);
	}
	public InteractionsHistory() {
	}
}
