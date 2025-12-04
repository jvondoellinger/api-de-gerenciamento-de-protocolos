package io.github.jvondoellinger.api_gerenciamento_de_protocolo.modules.protocol_module.domain.events;

import io.github.jvondoellinger.api_gerenciamento_de_protocolo.modules.protocol_module.domain.Queue;

public class CreateQueueEvent extends UserActivityEvent {
	private final Queue queue;

	public CreateQueueEvent(Queue queue) {
		super(queue.getCreatedById());
		this.queue = queue;
	}

	public Queue getQueue() {
		return queue;
	}
}
