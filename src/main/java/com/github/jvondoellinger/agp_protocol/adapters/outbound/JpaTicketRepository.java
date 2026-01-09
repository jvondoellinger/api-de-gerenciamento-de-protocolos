package com.github.jvondoellinger.agp_protocol.adapters.outbound;

import com.github.jvondoellinger.agp_protocol.infrastructure.entity.QueueDbEntity;
import com.github.jvondoellinger.agp_protocol.infrastructure.entity.TicketDbEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JpaTicketRepository extends JpaRepository<TicketDbEntity, String> {
}
