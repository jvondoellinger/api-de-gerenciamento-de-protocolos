package io.github.jvondoellinger.api_gerenciamento_de_protocolo.modules.protocol_module.domain.contracts.persistence.filters;

public class PaginationFilter {
    private long offset;
    private int limit;

    public PaginationFilter(long offset, int limit) {
        this.offset = offset;
        this.limit = limit;
    }

    public long getOffset() {
        return offset;
    }

    public int getLimit() {
        return limit;
    }
}
