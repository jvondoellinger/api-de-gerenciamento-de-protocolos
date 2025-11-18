package io.github.jvondoellinger.api_gerenciamento_de_protocolo.modules.protocol_module.application.commands;

import java.util.UUID;

public record CreateProtocolCommand(
        String description,
        String createdBy,
        String userId,
        UUID queueId) {
}
