package io.github.jvondoellinger.api_gerenciamento_de_protocolo.modules.protocol_module.application.commands;

import io.github.jvondoellinger.api_gerenciamento_de_protocolo.modules.protocol_module.application.dtos.DomainIdDto;
import io.github.jvondoellinger.api_gerenciamento_de_protocolo.modules.protocol_module.application.dtos.ProtocolNumberDto;
import io.github.jvondoellinger.api_gerenciamento_de_protocolo.modules.protocol_module.domain.valueObjects.DomainId;
import io.github.jvondoellinger.api_gerenciamento_de_protocolo.modules.protocol_module.domain.valueObjects.ProtocolNumber;

public record AddInteractionProtocolCommand(
        DomainIdDto agentId,
        String agent,
        ProtocolNumberDto protocolNumber,
        String text) {
}
