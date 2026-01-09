package com.github.jvondoellinger.agp_protocol.adapters.outbound;

import com.github.jvondoellinger.agp_protocol.infrastructure.entity.InteractionDbEntity;
import com.github.jvondoellinger.agp_protocol.infrastructure.entity.QueueDbEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JpaInteractionRepository extends JpaRepository<InteractionDbEntity, String> {
}
