package com.github.jvondoellinger.agp_protocol.adapters.outbound;

import com.github.jvondoellinger.agp_protocol.domain.DomainId;
import com.github.jvondoellinger.agp_protocol.domain.profile.UserProfile;
import com.github.jvondoellinger.agp_protocol.domain.profile.UserProfileRepository;
import com.github.jvondoellinger.agp_protocol.domain.shared.QueryFilter;
import com.github.jvondoellinger.agp_protocol.infrastructure.entity.DbEntity;
import com.github.jvondoellinger.agp_protocol.infrastructure.entity.UserProfileDbEntity;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@AllArgsConstructor
public class UserProfileRepositoryImpl implements UserProfileRepository {
	private final JpaUserProfileRepository jpaUserProfileRepository;

	@Override
	public UserProfile save(UserProfile entity) {
		return JpaCrudsBridge.save(jpaUserProfileRepository, new UserProfileDbEntity(entity), DbEntity::toDomainEntity);
	}

	@Override
	public UserProfile update(UserProfile entity) {
		return JpaCrudsBridge.save(jpaUserProfileRepository, new UserProfileDbEntity(entity), DbEntity::toDomainEntity);
	}

	@Override
	public void delete(UserProfile entity) {
		JpaCrudsBridge.delete(jpaUserProfileRepository, new UserProfileDbEntity(entity));
	}

	@Override
	public UserProfile queryById(DomainId id) {
		return JpaCrudsBridge.findById(jpaUserProfileRepository, id.value(), DbEntity::toDomainEntity);
	}

	@Override
	public List<UserProfile> query(QueryFilter filter) {
		return JpaCrudsBridge.findBy(jpaUserProfileRepository, filter, DbEntity::toDomainEntity);
	}
}
