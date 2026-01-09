package com.github.jvondoellinger.agp_protocol.adapters.outbound;

import com.github.jvondoellinger.agp_protocol.infrastructure.entity.AccessProfileDbEntity;
import com.github.jvondoellinger.agp_protocol.infrastructure.entity.QueueDbEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JpaAccessProfileRepository extends JpaRepository<AccessProfileDbEntity, String> {
}
