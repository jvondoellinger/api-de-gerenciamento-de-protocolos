package io.github.jvondoellinger.api_gerenciamento_de_protocolo.modules.protocol_module.application.commands;

import io.github.jvondoellinger.api_gerenciamento_de_protocolo.modules.protocol_module.application.dtos.DomainIdDto;
import io.github.jvondoellinger.api_gerenciamento_de_protocolo.modules.protocol_module.application.dtos.ProtocolNumberDto;

public record AddInteractionProtocolCommand(
        DomainIdDto userId,
        String user,
        ProtocolNumberDto protocolNumber,
        String text) {
}
