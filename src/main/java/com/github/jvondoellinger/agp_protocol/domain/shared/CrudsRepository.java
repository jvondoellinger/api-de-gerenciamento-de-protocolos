package com.github.jvondoellinger.agp_protocol.domain.shared;

import com.github.jvondoellinger.agp_protocol.domain.DomainId;

import java.util.List;

public interface CrudsRepository<T> {
	T save(T entity);
	T update(T entity);
	void delete(T entity);
	T queryById(DomainId id);

	/**
	 *
	 * @param filter
	 * @return Readonly list
	 */
	List<T> query(QueryFilter filter);
}
