package io.github.jvondoellinger.api_gerenciamento_de_protocolo.modules.protocol_module.infrastructure.resolvers;

import io.github.jvondoellinger.api_gerenciamento_de_protocolo.modules.protocol_module.domain.events.UserActivityEvent;
import io.github.jvondoellinger.api_gerenciamento_de_protocolo.modules.protocol_module.domain.profiles.permissions.Permissions;
import io.github.jvondoellinger.api_gerenciamento_de_protocolo.modules.protocol_module.infrastructure.resolvers.strategy.EventPermissionResolver;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class PermissionsResolver {
	private final List<EventPermissionResolver> strategies;

	public PermissionsResolver(List<EventPermissionResolver> strategies) {
		this.strategies = strategies;
	}

	public Permissions resolve(UserActivityEvent obj) {
		var list = strategies
			   .stream()
			   .map(x -> x.resolve(obj) )
			   .filter(x -> !Objects.isNull(x))
			   .toList();
		return Permissions.create(list);
	}
}
