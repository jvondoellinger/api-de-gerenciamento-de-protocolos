package io.github.jvondoellinger.api_gerenciamento_de_protocolo.modules.protocol_module.application.mappers;

import io.github.jvondoellinger.api_gerenciamento_de_protocolo.modules.protocol_module.application.dtos.QueueDto;
import io.github.jvondoellinger.api_gerenciamento_de_protocolo.modules.protocol_module.domain.Queue;

public class QueueMapper {
      public static Queue map(QueueDto dto) {
            return new Queue(
                    dto.area(),
                    dto.subarea(),
                    dto.directedBy(),
                    dto.directedById()
            );
      }
}
