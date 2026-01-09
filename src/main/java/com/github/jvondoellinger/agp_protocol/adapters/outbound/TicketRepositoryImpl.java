package com.github.jvondoellinger.agp_protocol.adapters.outbound;

import com.github.jvondoellinger.agp_protocol.domain.DomainId;
import com.github.jvondoellinger.agp_protocol.domain.shared.QueryFilter;
import com.github.jvondoellinger.agp_protocol.domain.ticket.Ticket;
import com.github.jvondoellinger.agp_protocol.domain.ticket.TicketRepository;
import com.github.jvondoellinger.agp_protocol.infrastructure.entity.DbEntity;
import com.github.jvondoellinger.agp_protocol.infrastructure.entity.TicketDbEntity;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@AllArgsConstructor
public class TicketRepositoryImpl implements TicketRepository {
	private final JpaTicketRepository jpaTicketRepository;

	@Override
	public Ticket save(Ticket entity) {
		return JpaCrudsBridge.save(jpaTicketRepository, new TicketDbEntity(entity), DbEntity::toDomainEntity);
	}

	@Override
	public Ticket update(Ticket entity) {
		return JpaCrudsBridge.save(jpaTicketRepository, new TicketDbEntity(entity), DbEntity::toDomainEntity);

	}

	@Override
	public void delete(Ticket entity) {
		JpaCrudsBridge.delete(jpaTicketRepository, new TicketDbEntity(entity));
	}

	@Override
	public Ticket queryById(DomainId id) {
		return JpaCrudsBridge.findById(jpaTicketRepository, id.value(), DbEntity::toDomainEntity);
	}

	@Override
	public List<Ticket> query(QueryFilter filter) {
		return JpaCrudsBridge.findBy(jpaTicketRepository, filter, DbEntity::toDomainEntity);

	}
}
