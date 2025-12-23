package io.github.jvondoellinger.api_gerenciamento_de_protocolo.modules.protocol_module.infrastructure.permission.checker;

import io.github.jvondoellinger.api_gerenciamento_de_protocolo.modules.protocol_module.domain.UserProfile;
import io.github.jvondoellinger.api_gerenciamento_de_protocolo.modules.protocol_module.domain.contracts.persistence.UserProfileReadRepository;
import io.github.jvondoellinger.api_gerenciamento_de_protocolo.modules.protocol_module.domain.profiles.permissions.Permissions;
import io.github.jvondoellinger.api_gerenciamento_de_protocolo.modules.protocol_module.domain.valueObjects.DomainId;
import io.github.jvondoellinger.api_gerenciamento_de_protocolo.modules.protocol_module.domain.profiles.permissions.Permission;
import io.github.jvondoellinger.api_gerenciamento_de_protocolo.modules.protocol_module.infrastructure.persistence.database.exception.AnyResultOnQueryException;
import io.github.jvondoellinger.api_gerenciamento_de_protocolo.modules.protocol_module.infrastructure.permission.exception.MissingPermissionException;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public class PermissionCheckerImpl implements PermissionChecker {
	private final UserProfileReadRepository repository;

	public PermissionCheckerImpl(UserProfileReadRepository repository) {
		this.repository = repository;
	}

	@Override
	public Mono<Boolean> hasPermission(Permission permission, DomainId userId) {
		return findProfile(userId)
			   .map(x -> compare(x, permission));

	}
	@Override
	public Mono<Boolean> hasPermission(Permissions permissions, DomainId userId) {
		return findProfile(userId)
			   .map(x -> compare(x, permissions));
	}
	@Override
	public Mono<Void> permittedOrThrow(Permission permission, DomainId userId) throws MissingPermissionException {
		return findProfile(userId)
			   .doOnNext(x -> this.throwIfNotHavePermission(x, permission))
			   .then();
	}
	@Override
	public Mono<Void> permittedOrThrow(Permissions permissions, DomainId userId) throws MissingPermissionException {
		return findProfile(userId)
			   .doOnNext(x -> this.throwIfNotHavePermission(x, permissions))
			   .then();
	}

	private boolean compare(UserProfile profile, Permission permission) {
		return profile
			   .getProfile()
			   .getPermissions()
			   .contains(permission);
	}
	private boolean compare(UserProfile profile, Permissions permissions) {
		return profile
			   .getProfile()
			   .getPermissions()
			   .contains(permissions);
	}
	private void throwIfNotHavePermission(UserProfile profile, Permission permission) {
		if (!compare(profile, permission)) {
			throw new MissingPermissionException("This profile does not have the necessary permissions.");
		}
	}
	private void throwIfNotHavePermission(UserProfile profile, Permissions permission) {
		if (!compare(profile, permission)) {
			throw new MissingPermissionException("This profile does not have the necessary permissions.");
		}
	}
	private Mono<UserProfile> findProfile(DomainId userId) {
		return repository
			   .queryByUserId(userId)
			   .switchIfEmpty(Mono.error(new AnyResultOnQueryException("No profiles found to this user.")));
	}
}
