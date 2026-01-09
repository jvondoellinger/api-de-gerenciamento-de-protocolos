package com.github.jvondoellinger.agp_protocol.domain;

import java.util.ArrayList;
import java.util.List;

public abstract class DomainCollection<T> {
	private final List<T> values;

	public DomainCollection(List<T> values) {
		this.values = new ArrayList<>(values);
	}

	public DomainCollection() {
		this.values = new ArrayList<>();
	}

	public void add(T value) {
		values.add(value);
	}
	public List<T> readonlyList() {
		return List.copyOf(values);
	}
}
