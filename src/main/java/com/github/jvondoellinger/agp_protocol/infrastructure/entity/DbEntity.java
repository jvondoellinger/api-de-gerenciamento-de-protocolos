package com.github.jvondoellinger.agp_protocol.infrastructure.entity;

@FunctionalInterface
public interface DbEntity<DomainEntity> {
	DomainEntity toDomainEntity();
}
