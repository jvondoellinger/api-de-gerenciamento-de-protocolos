package com.github.jvondoellinger.agp_protocol.application.shared;

import com.github.jvondoellinger.agp_protocol.application.shared.id.DomainIdDTO;
import com.github.jvondoellinger.agp_protocol.domain.shared.QueryFilter;

import java.util.List;

public interface QueryUseCase<O> {
	O queryById(DomainIdDTO domainIdDTO);
	List<O> queryByPagination(QueryFilter filter);
}
