package com.github.jvondoellinger.agp_protocol.adapters.outbound;

import com.github.jvondoellinger.agp_protocol.infrastructure.entity.QueueDbEntity;
import com.github.jvondoellinger.agp_protocol.infrastructure.entity.UserProfileDbEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JpaUserProfileRepository extends JpaRepository<UserProfileDbEntity, String> {
}
