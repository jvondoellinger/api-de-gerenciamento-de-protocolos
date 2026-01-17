package com.github.jvondoellinger.agp_protocol.application.accessProfile.useCases;

import com.github.jvondoellinger.agp_protocol.application.accessProfile.DeleteAccessProfileRequestDTO;
import com.github.jvondoellinger.agp_protocol.application.accessProfile.DeleteAccessProfileResponseDTO;
import com.github.jvondoellinger.agp_protocol.application.shared.CommandUseCase;

public interface DeleteAccessProfileCommandUseCase extends CommandUseCase<DeleteAccessProfileRequestDTO, DeleteAccessProfileResponseDTO> {

}
