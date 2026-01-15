package com.github.jvondoellinger.agp_protocol.adapters.outbound;

import com.github.jvondoellinger.agp_protocol.domain.shared.QueryFilter;
import com.github.jvondoellinger.agp_protocol.infrastructure.entity.DbEntity;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.function.Function;

@Service
@Log4j2
public class JpaCrudsBridge {
	private JpaCrudsBridge() {}

	public static <
		   T,
		   Id,
		   E extends DbEntity<T>,
		   R extends JpaRepository<E, Id>
		   >
	T save(
		   R repository, E dbEntity, Function<E, T> map
	) {
		return repository.save(dbEntity)
			   .toDomainEntity();
	}

	public static <
		   T,
		   Id,
		   E extends DbEntity<T>,
		   R extends JpaRepository<E, Id>
		   >
	void delete(R jpaRepository, E entity) {
		jpaRepository.delete(entity);
	}

	public static <
		   T,
		   Id,
		   E extends DbEntity<T>,
		   R extends JpaRepository<E, Id>
		   >
	T findById(R jpaRepository, Id id, Function<E, T> map) {
		return jpaRepository.findById(id)
			   .map(DbEntity::toDomainEntity)
			   .orElse(null);
	}

	public static <
		   T,
		   Id,
		   E extends DbEntity<T>,
		   R extends JpaRepository<E, Id>
		   >
	List<T> findBy(R jpaRepository, QueryFilter filter, Function<E, T> map) {
		var pageable = PageRequest.of(filter.page(), filter.size());
		return jpaRepository
			   .findAll(pageable)
			   .map(DbEntity::toDomainEntity)
			   .toList();
	}
}
