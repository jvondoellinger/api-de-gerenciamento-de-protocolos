package io.github.jvondoellinger.api_gerenciamento_de_protocolo.modules.protocol_module.domain.events;

import io.github.jvondoellinger.api_gerenciamento_de_protocolo.modules.protocol_module.domain.Interaction;

public class AddInteractionProtocolEvent extends DomainEvent {
    private Interaction interaction;

    public AddInteractionProtocolEvent(Interaction interaction) {
        this.interaction = interaction;
    }

    public Interaction getInteraction() {
        return interaction;
    }

}
