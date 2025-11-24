package io.github.jvondoellinger.api_gerenciamento_de_protocolo.modules.protocol_module.application.mappers;

import io.github.jvondoellinger.api_gerenciamento_de_protocolo.modules.protocol_module.application.commands.CreateProtocolCommand;
import io.github.jvondoellinger.api_gerenciamento_de_protocolo.modules.protocol_module.domain.Protocol;
import io.github.jvondoellinger.api_gerenciamento_de_protocolo.modules.protocol_module.domain.Queue;
import io.github.jvondoellinger.api_gerenciamento_de_protocolo.modules.protocol_module.domain.events.CreateProtocolEvent;
import io.github.jvondoellinger.api_gerenciamento_de_protocolo.modules.protocol_module.domain.valueObjects.DomainId;

public class CreateProtocolEventMapper {
    private CreateProtocolEventMapper() {}

    public static CreateProtocolEvent map(CreateProtocolCommand command) {
        var queueId = DomainId.from(command.queueId());
        var userId = DomainId.from(command.userId());
        var queue = Queue.createWithIdOnly(queueId);
        var protocolo = new Protocol(
                queue,
                command.description(),
                command.createdBy()
        );
        return new CreateProtocolEvent(protocolo, queueId, userId);
    }
}
