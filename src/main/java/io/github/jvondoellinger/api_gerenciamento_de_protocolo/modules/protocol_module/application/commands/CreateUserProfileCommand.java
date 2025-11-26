package io.github.jvondoellinger.api_gerenciamento_de_protocolo.modules.protocol_module.application.commands;

import java.util.Set;

public record CreateUserProfileCommand(String userId,
							    String profileName,
							    Set<String> permissions) {
}
