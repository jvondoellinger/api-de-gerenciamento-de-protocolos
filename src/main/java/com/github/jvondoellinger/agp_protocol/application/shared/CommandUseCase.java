package com.github.jvondoellinger.agp_protocol.application.shared;

public interface CommandUseCase<I, O> {
	O execute(I request);
}
