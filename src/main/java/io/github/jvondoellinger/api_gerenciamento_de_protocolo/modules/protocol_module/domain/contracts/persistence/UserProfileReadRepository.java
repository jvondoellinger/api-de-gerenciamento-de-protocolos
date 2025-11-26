package io.github.jvondoellinger.api_gerenciamento_de_protocolo.modules.protocol_module.domain.contracts.persistence;

import io.github.jvondoellinger.api_gerenciamento_de_protocolo.modules.protocol_module.domain.UserProfile;
import io.github.jvondoellinger.api_gerenciamento_de_protocolo.modules.protocol_module.domain.valueObjects.DomainId;
import reactor.core.publisher.Mono;

public interface UserProfileReadRepository {
	Mono<UserProfile> query(DomainId id);
	Mono<UserProfile> queryByUserId(DomainId userId);

}
