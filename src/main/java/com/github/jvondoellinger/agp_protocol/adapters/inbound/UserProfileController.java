package com.github.jvondoellinger.agp_protocol.adapters.inbound;

import com.github.jvondoellinger.agp_protocol.application.userProfile.CreateUserProfileRequestDTO;
import com.github.jvondoellinger.agp_protocol.application.userProfile.CreateUserProfileResponseDTO;
import com.github.jvondoellinger.agp_protocol.application.userProfile.useCases.CreateUserProfileUseCase;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/user-profile")
@AllArgsConstructor
public class UserProfileController {
	private final CreateUserProfileUseCase useCase;

	@PostMapping
	public CreateUserProfileResponseDTO create(@RequestBody CreateUserProfileRequestDTO requestDTO) {
		return useCase.execute(requestDTO);
	}
}
