package com.github.jvondoellinger.agp_protocol.application.userProfile.useCases;

import com.github.jvondoellinger.agp_protocol.application.shared.UseCase;
import com.github.jvondoellinger.agp_protocol.application.userProfile.CreateUserProfileRequestDTO;
import com.github.jvondoellinger.agp_protocol.application.userProfile.CreateUserProfileResponseDTO;

public interface CreateUserProfileUseCase extends UseCase<CreateUserProfileRequestDTO, CreateUserProfileResponseDTO> {
}
