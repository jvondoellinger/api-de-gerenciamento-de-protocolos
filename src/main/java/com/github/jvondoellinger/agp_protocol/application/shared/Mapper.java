package com.github.jvondoellinger.agp_protocol.application.shared;

public interface Mapper<TEntity, TRequest, TResponse> {
	TResponse mapToResponse(TEntity entity);
	TEntity mapToEntity(TRequest entity);
}
