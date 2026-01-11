package com.github.jvondoellinger.agp_protocol.domain.valueObjects;

import com.github.jvondoellinger.agp_protocol.domain.DomainException;

public class Permission {
	private final String code;

	private Permission(String code) {
		this.code = code;
	}

	public static Permission of(String code) {
		if (code == null) {
			throw new DomainException("Code is null.");
		}
		if (code.isBlank()) {
			throw new DomainException("Code is blank.");
		}

		return new Permission(code.toUpperCase());
	}

	public String code() {
		return code;
	}
}
