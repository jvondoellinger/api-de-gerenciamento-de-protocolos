package io.github.jvondoellinger.api_gerenciamento_de_protocolo.modules.protocol_module.application.dtos;

import io.github.jvondoellinger.api_gerenciamento_de_protocolo.modules.protocol_module.domain.valueObjects.DomainId;

public record QueueDto(String area,
                       String subarea,
                       String directedBy,
                       DomainId directedById) {
}
