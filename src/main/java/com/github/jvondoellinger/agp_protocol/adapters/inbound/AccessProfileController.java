package com.github.jvondoellinger.agp_protocol.adapters.inbound;

import com.github.jvondoellinger.agp_protocol.application.accessProfile.CreateAccessProfileRequestDTO;
import com.github.jvondoellinger.agp_protocol.application.accessProfile.CreateAccessProfileResponseDTO;
import com.github.jvondoellinger.agp_protocol.application.accessProfile.useCases.AccessProfileUseCase;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/access-profile")
@AllArgsConstructor
public class AccessProfileController {
	private final AccessProfileUseCase useCase;

	@PostMapping
	public CreateAccessProfileResponseDTO create(CreateAccessProfileRequestDTO requestDTO) {
		return useCase.create(requestDTO);
	}
}
