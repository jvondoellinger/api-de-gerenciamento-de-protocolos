package com.github.jvondoellinger.agp_protocol.domain;

import java.util.UUID;

public class DomainId {
	private String id;

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

	private void gen() {
		this.id = UUID.randomUUID().toString();
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
