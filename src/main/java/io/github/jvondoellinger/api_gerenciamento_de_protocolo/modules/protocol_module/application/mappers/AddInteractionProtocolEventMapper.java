package io.github.jvondoellinger.api_gerenciamento_de_protocolo.modules.protocol_module.application.mappers;

import io.github.jvondoellinger.api_gerenciamento_de_protocolo.modules.protocol_module.application.commands.AddInteractionProtocolCommand;
import io.github.jvondoellinger.api_gerenciamento_de_protocolo.modules.protocol_module.domain.Interaction;
import io.github.jvondoellinger.api_gerenciamento_de_protocolo.modules.protocol_module.domain.events.AddInteractionProtocolEvent;
import io.github.jvondoellinger.api_gerenciamento_de_protocolo.modules.protocol_module.domain.valueObjects.DomainId;
import io.github.jvondoellinger.api_gerenciamento_de_protocolo.modules.protocol_module.domain.valueObjects.ProtocolNumber;

public class AddInteractionProtocolEventMapper {
    private AddInteractionProtocolEventMapper() {}

    public static AddInteractionProtocolEvent map(AddInteractionProtocolCommand command) {
        // Fields
        var userId = DomainId.from(command.userId());
        var user = command.user();
        var description = command.text();
        var protocolNumber = ProtocolNumber.parse(command.protocolNumber().value());

        // Interaction
        var interaction = new Interaction(
            userId,
            user,
            description
        );

        // Event
        return new AddInteractionProtocolEvent(interaction, protocolNumber);
    }
}
