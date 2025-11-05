package io.github.jvondoellinger.api_gerenciamento_de_protocolo.modules.protocol_module.application.mappers;

import io.github.jvondoellinger.api_gerenciamento_de_protocolo.modules.protocol_module.application.commands.AddInteractionProtocolCommand;
import io.github.jvondoellinger.api_gerenciamento_de_protocolo.modules.protocol_module.domain.Interaction;
import io.github.jvondoellinger.api_gerenciamento_de_protocolo.modules.protocol_module.domain.events.AddInteractionProtocolEvent;
import io.github.jvondoellinger.api_gerenciamento_de_protocolo.modules.protocol_module.domain.valueObjects.DomainId;
import io.github.jvondoellinger.api_gerenciamento_de_protocolo.modules.protocol_module.domain.valueObjects.ProtocolNumber;

public class AddInteractionProtocolEventMapper {
    private AddInteractionProtocolEventMapper() {}

    public static AddInteractionProtocolEvent map(AddInteractionProtocolCommand command) {
        var agentId = DomainId.from(command.agentId().value());
        var protocolNumber = ProtocolNumber.parse(command.protocolNumber().value());

        var interaction = new Interaction(
                agentId,
                command.agent(),
                protocolNumber,
                command.text());
        return new AddInteractionProtocolEvent(interaction);
    }
}
