package io.github.jvondoellinger.api_gerenciamento_de_protocolo.modules.protocol_module.domain.contracts.persistence;

import io.github.jvondoellinger.api_gerenciamento_de_protocolo.modules.protocol_module.domain.UserProfile;
import io.github.jvondoellinger.api_gerenciamento_de_protocolo.modules.protocol_module.domain.profiles.Profile;
import reactor.core.publisher.Mono;

public interface UserProfileWriteRepository {
	Mono<Void> save(UserProfile profile);
	Mono<Void> remove(UserProfile profile);

}
