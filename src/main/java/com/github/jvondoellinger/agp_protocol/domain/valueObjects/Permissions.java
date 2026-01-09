package com.github.jvondoellinger.agp_protocol.domain.valueObjects;

import com.github.jvondoellinger.agp_protocol.domain.DomainCollection;

import java.util.List;

public class Permissions extends DomainCollection<Permission> {
	public Permissions(List<Permission> permissions) {
		super(permissions);
	}
	public Permissions() {
	}


}
