package io.github.jvondoellinger.api_gerenciamento_de_protocolo.modules.protocol_module.application.mappers;

import io.github.jvondoellinger.api_gerenciamento_de_protocolo.modules.protocol_module.application.commands.CreateProtocolCommand;
import io.github.jvondoellinger.api_gerenciamento_de_protocolo.modules.protocol_module.domain.events.CreateProtocolEvent;
import io.github.jvondoellinger.api_gerenciamento_de_protocolo.modules.protocol_module.domain.Protocolo;

public class CreateProtocolEventMapper {
    private CreateProtocolEventMapper() {}

    public static CreateProtocolEvent map(CreateProtocolCommand command) {
        var protocolo = new Protocolo(command.getDescription(),
                command.getCreatedBy(),
                null,
                null);
        return new CreateProtocolEvent(protocolo);
    }
}
