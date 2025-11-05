package io.github.jvondoellinger.api_gerenciamento_de_protocolo.modules.protocol_module.application.commands;

import java.util.UUID;

public record CreateQueueCommand(
        String area,
        String subarea,
        String createdBy,
        UUID createdById) {
}
