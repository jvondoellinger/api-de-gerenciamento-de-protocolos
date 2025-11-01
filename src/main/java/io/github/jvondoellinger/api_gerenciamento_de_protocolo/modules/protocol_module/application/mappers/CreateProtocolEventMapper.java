package io.github.jvondoellinger.api_gerenciamento_de_protocolo.modules.protocol_module.application.mappers;

import io.github.jvondoellinger.api_gerenciamento_de_protocolo.modules.protocol_module.application.commands.CreateProtocolCommand;
import io.github.jvondoellinger.api_gerenciamento_de_protocolo.modules.protocol_module.domain.events.CreateProtocolEvent;
import io.github.jvondoellinger.api_gerenciamento_de_protocolo.modules.protocol_module.domain.Protocolo;
import io.github.jvondoellinger.api_gerenciamento_de_protocolo.modules.protocol_module.domain.valueObjects.DomainId;

public class CreateProtocolEventMapper {
    private CreateProtocolEventMapper() {}

    public static CreateProtocolEvent map(CreateProtocolCommand command) {
        var queue = QueueMapper.map(command.getQueueDto());
        var protocolo = new Protocolo(queue,
                command.getDescription(),
                command.getCreatedBy());
        return new CreateProtocolEvent(protocolo);
    }
}
