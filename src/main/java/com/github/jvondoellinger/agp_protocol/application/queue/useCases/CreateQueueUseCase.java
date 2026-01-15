package com.github.jvondoellinger.agp_protocol.application.queue.useCases;

import com.github.jvondoellinger.agp_protocol.application.queue.CreateQueueRequestDTO;
import com.github.jvondoellinger.agp_protocol.application.queue.CreateQueueResponseDTO;
import com.github.jvondoellinger.agp_protocol.application.shared.UseCase;

public interface CreateQueueUseCase extends UseCase<CreateQueueRequestDTO, CreateQueueResponseDTO> {
}
