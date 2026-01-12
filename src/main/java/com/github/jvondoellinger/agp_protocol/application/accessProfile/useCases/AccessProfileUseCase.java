package com.github.jvondoellinger.agp_protocol.application.accessProfile.useCases;

import com.github.jvondoellinger.agp_protocol.application.accessProfile.CreateAccessProfileRequestDTO;
import com.github.jvondoellinger.agp_protocol.application.accessProfile.CreateAccessProfileResponseDTO;
import com.github.jvondoellinger.agp_protocol.application.queue.CreateQueueRequestDTO;
import com.github.jvondoellinger.agp_protocol.application.queue.CreateQueueResponseDTO;
import com.github.jvondoellinger.agp_protocol.application.shared.UseCase;

public interface AccessProfileUseCase extends UseCase<CreateAccessProfileRequestDTO, CreateAccessProfileResponseDTO> {
}
