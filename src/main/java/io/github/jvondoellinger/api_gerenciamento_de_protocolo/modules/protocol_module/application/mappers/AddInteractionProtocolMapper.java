package io.github.jvondoellinger.api_gerenciamento_de_protocolo.modules.protocol_module.application.mappers;

import io.github.jvondoellinger.api_gerenciamento_de_protocolo.modules.protocol_module.application.commands.AddInteractionProtocolCommand;
import io.github.jvondoellinger.api_gerenciamento_de_protocolo.modules.protocol_module.domain.Interaction;
import io.github.jvondoellinger.api_gerenciamento_de_protocolo.modules.protocol_module.domain.events.AddInteractionProtocolEvent;

public class AddInteractionProtocolMapper {
    private AddInteractionProtocolMapper() {}

    public static AddInteractionProtocolEvent map(AddInteractionProtocolCommand command) {
        var interaction = new Interaction(command.getAgentId(),
                command.getAgent(),
                command.getProtocolNumber(),
                command.getText());
        return new AddInteractionProtocolEvent(interaction);
    }
}
