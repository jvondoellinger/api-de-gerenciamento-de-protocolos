package io.github.jvondoellinger.api_gerenciamento_de_protocolo.modules.protocol_module.application.commands;

import java.util.List;

public record CreatePermissionRelationshipCommand(String userId, List<String> permissions) {
}
