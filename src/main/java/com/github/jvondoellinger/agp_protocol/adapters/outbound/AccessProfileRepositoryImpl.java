package com.github.jvondoellinger.agp_protocol.adapters.outbound;

import com.github.jvondoellinger.agp_protocol.domain.DomainId;
import com.github.jvondoellinger.agp_protocol.domain.profile.AccessProfile;
import com.github.jvondoellinger.agp_protocol.domain.profile.AccessProfileRepository;
import com.github.jvondoellinger.agp_protocol.domain.shared.QueryFilter;
import com.github.jvondoellinger.agp_protocol.infrastructure.entity.AccessProfileDbEntity;
import com.github.jvondoellinger.agp_protocol.infrastructure.entity.DbEntity;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@AllArgsConstructor
public class AccessProfileRepositoryImpl implements AccessProfileRepository {
	private final JpaAccessProfileRepository jpaAccessProfileRepository;

	@Override
	public AccessProfile save(AccessProfile entity) {
		return JpaCrudsBridge.save(jpaAccessProfileRepository, new AccessProfileDbEntity(entity), DbEntity::toDomainEntity);
	}

	@Override
	public AccessProfile update(AccessProfile entity) {
		return JpaCrudsBridge.save(jpaAccessProfileRepository, new AccessProfileDbEntity(entity), DbEntity::toDomainEntity);
	}

	@Override
	public void delete(AccessProfile entity) {
		JpaCrudsBridge.delete(jpaAccessProfileRepository, new AccessProfileDbEntity(entity));
	}

	@Override
	public AccessProfile queryById(DomainId id) {
		return JpaCrudsBridge.findById(jpaAccessProfileRepository, id.value(), DbEntity::toDomainEntity);

	}

	@Override
	public List<AccessProfile> query(QueryFilter filter) {
		return JpaCrudsBridge.findBy(jpaAccessProfileRepository, filter, DbEntity::toDomainEntity);

	}
}
