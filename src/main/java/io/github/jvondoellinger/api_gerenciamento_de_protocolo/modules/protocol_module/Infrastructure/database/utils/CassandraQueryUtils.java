package io.github.jvondoellinger.api_gerenciamento_de_protocolo.modules.protocol_module.Infrastructure.database.utils;

import io.github.jvondoellinger.api_gerenciamento_de_protocolo.modules.protocol_module.domain.contracts.persistence.filters.PaginationFilter;
import org.springframework.data.cassandra.core.query.Query;
import org.springframework.data.domain.PageRequest;

public class CassandraQueryUtils {
    private CassandraQueryUtils() {}

    public static Query convert(PaginationFilter filter) {
        var page = PageRequest
                .of((int) filter.getOffset(), filter.getLimit());
        var request = Query
                .empty()
                .pageRequest(page);
        return request;
    }
}
