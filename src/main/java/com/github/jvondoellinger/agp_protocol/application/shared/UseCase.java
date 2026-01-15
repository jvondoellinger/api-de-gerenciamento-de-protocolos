package com.github.jvondoellinger.agp_protocol.application.shared;

public interface UseCase<TRequest, TResponse> {
	TResponse execute(TRequest request);
}
