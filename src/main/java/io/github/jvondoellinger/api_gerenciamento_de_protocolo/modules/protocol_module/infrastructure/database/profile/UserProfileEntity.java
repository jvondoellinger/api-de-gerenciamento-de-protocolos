package io.github.jvondoellinger.api_gerenciamento_de_protocolo.modules.protocol_module.infrastructure.database.profile;

import io.github.jvondoellinger.api_gerenciamento_de_protocolo.modules.protocol_module.domain.profiles.permissions.Permissions;
import io.github.jvondoellinger.api_gerenciamento_de_protocolo.modules.protocol_module.domain.valueObjects.DomainId;
import io.github.jvondoellinger.api_gerenciamento_de_protocolo.modules.protocol_module.domain.UserProfile;
import io.github.jvondoellinger.api_gerenciamento_de_protocolo.modules.protocol_module.domain.profiles.Profile;
import io.github.jvondoellinger.api_gerenciamento_de_protocolo.modules.protocol_module.infrastructure.database.ObjectEntity;
import io.github.jvondoellinger.api_gerenciamento_de_protocolo.modules.protocol_module.domain.profiles.PermissionFactory;
import org.springframework.data.annotation.PersistenceCreator;
import org.springframework.data.cassandra.core.cql.Ordering;
import org.springframework.data.cassandra.core.cql.PrimaryKeyType;
import org.springframework.data.cassandra.core.mapping.CassandraType;
import org.springframework.data.cassandra.core.mapping.PrimaryKeyColumn;
import org.springframework.data.cassandra.core.mapping.Table;

import java.util.HashSet;
import java.util.Set;

@Table("user_profile")
public class UserProfileEntity implements ObjectEntity<UserProfile> {
	@PersistenceCreator
	public UserProfileEntity(String id, String userId, String profileName, Set<String> permissions) {
		this.id = id;
		this.userId = userId;
		this.permissions = permissions;
		this.profileName = profileName;
	}

	// Columns
	@PrimaryKeyColumn(name = "id", type = PrimaryKeyType.CLUSTERED, ordering = Ordering.DESCENDING, ordinal = 2)
	private String id;

	@PrimaryKeyColumn(name = "userId", type = PrimaryKeyType.PARTITIONED, ordinal = 0)
	private String userId;

	@PrimaryKeyColumn(name = "profileName", type = PrimaryKeyType.CLUSTERED, ordinal = 1)
	private String profileName;

	@CassandraType(type = CassandraType.Name.SET, typeArguments = CassandraType.Name.TEXT)
	private Set<String> permissions;



	// Utils methods
	@Override
	public UserProfile parse() {
		// Fields
		var id = DomainId.from(this.id);
		var userId = DomainId.from(this.userId);
		var permissionsList = this.permissions
			   .stream()
			   .map(PermissionFactory::getInstance)
			   .toList();

		var permissions = Permissions.create(permissionsList);

		// Returning entity when explicit fields
		var profileObj = new Profile(profileName, permissions);

		return new UserProfile(id, userId, profileObj);
	}

	public static UserProfileEntity create(UserProfile profile) {
		// Fields
		var permissionList = profile
			   .getProfile()
			   .getPermissions()
			   .getListString();
		Set<String> permissions = new HashSet<>(permissionList);
		var id = profile
			   .getId()
			   .getValue()
			   .toString();
		var userId = profile
			   .getUserId()
			   .getValue()
			   .toString();
		var name = profile
			   .getProfile()
			   .getName();

		// Returning entity when explicit fields
		return new UserProfileEntity(id, userId, name, permissions);
	}



	// Getter and setters (ignore)
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public Set<String> getPermissions() {
		return permissions;
	}

	public void setPermissions(Set<String> permissions) {
		this.permissions = permissions;
	}

	public String getProfileName() {
		return profileName;
	}

	public void setProfileName(String profileName) {
		this.profileName = profileName;
	}
}
