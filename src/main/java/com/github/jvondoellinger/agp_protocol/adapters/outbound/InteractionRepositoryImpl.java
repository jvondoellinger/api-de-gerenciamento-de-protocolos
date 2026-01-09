package com.github.jvondoellinger.agp_protocol.adapters.outbound;

import com.github.jvondoellinger.agp_protocol.domain.DomainId;
import com.github.jvondoellinger.agp_protocol.domain.interaction.Interaction;
import com.github.jvondoellinger.agp_protocol.domain.interaction.InteractionRepository;
import com.github.jvondoellinger.agp_protocol.domain.shared.QueryFilter;
import com.github.jvondoellinger.agp_protocol.infrastructure.entity.DbEntity;
import com.github.jvondoellinger.agp_protocol.infrastructure.entity.InteractionDbEntity;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@AllArgsConstructor
public class InteractionRepositoryImpl implements InteractionRepository {
	private final JpaInteractionRepository jpaInteractionRepository;

	@Override
	public Interaction save(Interaction entity) {
		return JpaCrudsBridge.save(jpaInteractionRepository, new InteractionDbEntity(entity), DbEntity::toDomainEntity);
	}

	@Override
	public Interaction update(Interaction entity) {
		return JpaCrudsBridge.save(jpaInteractionRepository, new InteractionDbEntity(entity), DbEntity::toDomainEntity);
	}

	@Override
	public void delete(Interaction entity) {
		JpaCrudsBridge.delete(jpaInteractionRepository, new InteractionDbEntity(entity));
	}

	@Override
	public Interaction queryById(DomainId id) {
		return JpaCrudsBridge.findById(jpaInteractionRepository, id.value(), DbEntity::toDomainEntity);
	}

	@Override
	public List<Interaction> query(QueryFilter filter) {
		return JpaCrudsBridge.findBy(jpaInteractionRepository, filter, DbEntity::toDomainEntity);
	}
}
