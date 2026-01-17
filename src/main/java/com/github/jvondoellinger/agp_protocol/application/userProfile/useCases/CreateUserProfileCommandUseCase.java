package com.github.jvondoellinger.agp_protocol.application.userProfile.useCases;

import com.github.jvondoellinger.agp_protocol.application.shared.CommandUseCase;
import com.github.jvondoellinger.agp_protocol.application.userProfile.CreateUserProfileRequestDTO;
import com.github.jvondoellinger.agp_protocol.application.userProfile.CreateUserProfileResponseDTO;

public interface CreateUserProfileCommandUseCase extends CommandUseCase<CreateUserProfileRequestDTO, CreateUserProfileResponseDTO> {
}
