package com.github.jvondoellinger.agp_protocol.domain;

import java.util.UUID;

public class DomainId {
	private String id;
	private void gen() {
		this.id = UUID.randomUUID().toString();
	}

	private DomainId() {
		gen();
	}
	private DomainId(String id) {
		this.id = id;
	}


	public static DomainId parse(String value) {
		var uuid = UUID.fromString(value);
		return new DomainId(uuid.toString());
	}
	public static DomainId create() {
		return new DomainId();
	}

	public String value() {
		if (id == null)
			throw new NullPointerException("Id value is null.");
		return id;
	}

	@Override
	public String toString() {
		return value();
	}
}
