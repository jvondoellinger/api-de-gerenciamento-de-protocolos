package io.github.jvondoellinger.api_gerenciamento_de_protocolo.modules.protocol_module.application.mappers;

import io.github.jvondoellinger.api_gerenciamento_de_protocolo.modules.protocol_module.application.commands.CreateQueueCommand;
import io.github.jvondoellinger.api_gerenciamento_de_protocolo.modules.protocol_module.domain.Queue;
import io.github.jvondoellinger.api_gerenciamento_de_protocolo.modules.protocol_module.domain.events.CreateQueueEvent;
import io.github.jvondoellinger.api_gerenciamento_de_protocolo.modules.protocol_module.domain.valueObjects.DomainId;

public class CreateQueueEventMapper {
      public static CreateQueueEvent map(CreateQueueCommand command) {
            var queue = new Queue(
                    command.area(),
                    command.subarea(),
                    command.createdBy(),
                    DomainId.from(command.createdById())
            );
            return new CreateQueueEvent(queue);
      }
}
