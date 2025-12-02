package io.github.jvondoellinger.api_gerenciamento_de_protocolo.modules.protocol_module.application.commands;

import io.github.jvondoellinger.api_gerenciamento_de_protocolo.modules.protocol_module.application.dtos.ProtocolNumberDto;

public record AddInteractionProtocolCommand(
        String userId,
        String user,
        ProtocolNumberDto protocolNumber,
        String text) {
}
