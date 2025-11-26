package io.github.jvondoellinger.api_gerenciamento_de_protocolo.modules.protocol_module.domain.events;

import io.github.jvondoellinger.api_gerenciamento_de_protocolo.modules.protocol_module.domain.Interaction;

public class AddInteractionProtocolEvent extends UserActivityEvent {
    private final Interaction interaction;

    public AddInteractionProtocolEvent(Interaction interaction) {
        super(interaction.getUserId());
        this.interaction = interaction;
    }

    public Interaction getInteraction() {
        return interaction;
    }

}
