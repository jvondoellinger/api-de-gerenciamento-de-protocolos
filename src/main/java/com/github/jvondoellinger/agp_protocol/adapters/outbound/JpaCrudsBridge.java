package com.github.jvondoellinger.agp_protocol.adapters.outbound;

import com.aventrix.jnanoid.jnanoid.NanoIdUtils;
import com.github.jvondoellinger.agp_protocol.domain.shared.QueryFilter;
import com.github.jvondoellinger.agp_protocol.infrastructure.entity.DbEntity;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.util.List;
import java.util.UUID;
import java.util.function.Function;
import java.util.function.Supplier;

@Service
@Log4j2
public class JpaCrudsBridge {
	private static final SecureRandom RANDOM = new SecureRandom();
	private JpaCrudsBridge() {
	}

	public static <
		   T,
		   Id,
		   E extends DbEntity<T>,
		   R extends JpaRepository<E, Id>
		   >
	T save(
		   R repository, E dbEntity, Function<E, T> map
	) {
		String id = generateLogId();
		String entityName = dbEntity.getClass().getSimpleName();
		T result;
		log.info("LOG_ID: {} |  Initializing JPA bridge to save entity {} into the database", id, entityName);
		try {
			result = repository.save(dbEntity).toDomainEntity();
			log.info("LOG_ID: {} | Transaction completed successfully for entity {}", id, entityName);
		}
		catch (Throwable exception) {
			log.error("LOG_ID: {} | Transaction failed for entity {}. Rolling back.", id, entityName, exception);
			throw exception;
		}
		finally {
			log.info("LOG_ID: {} | Exiting JPA bridge after saving entity {} into the database", id, entityName);
		}
		return result;
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
		return runQueryFunc(() -> {
			return jpaRepository.findById(id)
				   .map(DbEntity::toDomainEntity)
				   .orElse(null);
		});
	}

	public static <
		   T,
		   Id,
		   E extends DbEntity<T>,
		   R extends JpaRepository<E, Id>
		   >
	List<T> findBy(R jpaRepository, QueryFilter filter, Function<E, T> map) {
		return runQueryFunc(() -> {
			var pageable = PageRequest.of(filter.page(), filter.size());
			return jpaRepository
				   .findAll(pageable)
				   .map(DbEntity::toDomainEntity)
				   .toList();
		});
	}

	private static <TReturn> TReturn runQueryFunc(Supplier<TReturn> function) {
		var id = generateLogId();
		TReturn result = null;
		log.info("LOG_ID: {} | Initializing JPA query execution", id);
		try {
			result = function.get();
			if (result instanceof List<?> resultList)
				log.info("LOG_ID: {} | JPA query executed successfully. Returned {} records", id, resultList.size());
			else if(result != null)
				log.info("LOG_ID: {} | JPA query executed successfully. Returned 1 record", id);
			else
				log.info("LOG_ID: {} | JPA query executed successfully. Returned 0 records", id);
		}
		catch (Throwable exception) {
			log.error("LOG_ID: {} | JPA query execution failed: {}", id, exception);
		}
		finally {
			log.info("LOG_ID: {} | Exiting JPA bridge after query execution", id);
		}
		return result;
	}

	private static String generateLogId() {
		return NanoIdUtils.randomNanoId(RANDOM, NanoIdUtils.DEFAULT_ALPHABET, 8);
	}
}
