package com.github.jvondoellinger.agp_protocol.application.queue.useCases;

import com.github.jvondoellinger.agp_protocol.application.queue.CreateQueueRequestDTO;
import com.github.jvondoellinger.agp_protocol.application.queue.CreateQueueResponseDTO;
import com.github.jvondoellinger.agp_protocol.application.shared.CommandUseCase;

public interface CreateQueueCommandUseCase extends CommandUseCase<CreateQueueRequestDTO, CreateQueueResponseDTO> {
}
