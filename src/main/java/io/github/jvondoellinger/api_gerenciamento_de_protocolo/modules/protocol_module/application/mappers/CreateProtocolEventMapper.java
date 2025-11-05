package io.github.jvondoellinger.api_gerenciamento_de_protocolo.modules.protocol_module.application.mappers;

import io.github.jvondoellinger.api_gerenciamento_de_protocolo.modules.protocol_module.application.commands.CreateProtocolCommand;
import io.github.jvondoellinger.api_gerenciamento_de_protocolo.modules.protocol_module.domain.ProtocoloBuilder;
import io.github.jvondoellinger.api_gerenciamento_de_protocolo.modules.protocol_module.domain.Queue;
import io.github.jvondoellinger.api_gerenciamento_de_protocolo.modules.protocol_module.domain.events.CreateProtocolEvent;
import io.github.jvondoellinger.api_gerenciamento_de_protocolo.modules.protocol_module.domain.Protocolo;
import io.github.jvondoellinger.api_gerenciamento_de_protocolo.modules.protocol_module.domain.valueObjects.DomainId;

public class CreateProtocolEventMapper {
    private CreateProtocolEventMapper() {}

    public static CreateProtocolEvent map(CreateProtocolCommand command) {
        var domainid = DomainId.from(command.queueId());
        var protocolo = ProtocoloBuilder
                .builder()
                .withDescription(command.description())
                .withCreatedBy(command.createdBy())
                .build();
        return new CreateProtocolEvent(protocolo, domainid);
    }
}
