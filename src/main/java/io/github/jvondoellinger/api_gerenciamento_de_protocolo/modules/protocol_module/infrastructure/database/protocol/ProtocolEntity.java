package io.github.jvondoellinger.api_gerenciamento_de_protocolo.modules.protocol_module.infrastructure.database.protocol;

import io.github.jvondoellinger.api_gerenciamento_de_protocolo.modules.protocol_module.domain.Interaction;
import io.github.jvondoellinger.api_gerenciamento_de_protocolo.modules.protocol_module.domain.InteractionHistory;
import io.github.jvondoellinger.api_gerenciamento_de_protocolo.modules.protocol_module.domain.Protocol;
import io.github.jvondoellinger.api_gerenciamento_de_protocolo.modules.protocol_module.domain.Queue;
import io.github.jvondoellinger.api_gerenciamento_de_protocolo.modules.protocol_module.domain.valueObjects.DomainId;
import io.github.jvondoellinger.api_gerenciamento_de_protocolo.modules.protocol_module.domain.status.ProtocoloStatusResolver;
import io.github.jvondoellinger.api_gerenciamento_de_protocolo.modules.protocol_module.domain.status.state.ProtocoloStateResolver;
import io.github.jvondoellinger.api_gerenciamento_de_protocolo.modules.protocol_module.domain.valueObjects.ProtocolNumber;
import io.github.jvondoellinger.api_gerenciamento_de_protocolo.modules.protocol_module.infrastructure.database.ObjectEntity;
import org.springframework.data.annotation.PersistenceCreator;
import org.springframework.data.cassandra.core.cql.Ordering;
import org.springframework.data.cassandra.core.cql.PrimaryKeyType;
import org.springframework.data.cassandra.core.mapping.CassandraType;
import org.springframework.data.cassandra.core.mapping.Column;
import org.springframework.data.cassandra.core.mapping.PrimaryKeyColumn;
import org.springframework.data.cassandra.core.mapping.Table;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;
import java.util.stream.Collectors;


@Table("protocol")
public class ProtocolEntity implements ObjectEntity<Protocol> {
	@PrimaryKeyColumn(name = "id", type = PrimaryKeyType.CLUSTERED, ordering = Ordering.DESCENDING)
	private UUID id;
	@PrimaryKeyColumn(name = "protocolNumber", type = PrimaryKeyType.PARTITIONED)
	private String protocolNumber;

	private LocalDateTime createdAt;
	private LocalDateTime updatedAt;

	private String description;
	private String createdByUser;
	private String createdByUserId;
	private String state;

	private UUID queueId;

	@Column("interactions")
	private List<InteractionColumn> interactions;

	@CassandraType(type = CassandraType.Name.LIST, typeArguments = CassandraType.Name.BLOB)
	private List<byte[]> attachments;

	public ProtocolEntity(Protocol protocol) {
		id = protocol.getId().getValue();
		createdAt = protocol.getCreatedAt();
		updatedAt = protocol.getUpdatedAt();
		description = protocol.getDescription();
		createdByUser = protocol.getCreatedBy();
		createdByUserId = protocol.getCreatedBy();
		state = protocol.getState().getStatus().getValue();
		attachments = protocol.getAttachments();
	}

	@PersistenceCreator
	public ProtocolEntity(UUID id,
					  String protocolNumber,
					  List<InteractionColumn> interactions,
					  LocalDateTime createdAt,
					  LocalDateTime updatedAt,
					  String description,
					  String createdByUser,
					  String createdByUserId,
					  String state,
					  UUID queueId,
					  List<byte[]> attachments) {
		this.id = id;
		this.protocolNumber = protocolNumber;
		this.interactions = interactions;
		this.createdAt = createdAt;
		this.updatedAt = updatedAt;
		this.description = description;
		this.createdByUser = createdByUser;
		this.createdByUserId = createdByUserId;
		this.state = state;
		this.attachments = attachments;
		this.queueId = queueId;
	}

	public Protocol parse() {
		var status = ProtocoloStatusResolver.resolve(state); // Dynamic method to get status
		var state = ProtocoloStateResolver.resolve(status); // Dynamic method to get state
		var queue = Queue.createWithIdOnly(DomainId.from(queueId));
		List<Interaction> interactions = Objects.isNull(this.interactions) ?
			   new ArrayList<>() :
			   this.interactions
					 .stream()
					 .map(this::parse)
					 .collect(Collectors.toCollection(ArrayList::new));

		return new Protocol(
			   DomainId.from(id),
			   ProtocolNumber.parse(protocolNumber),
			   queue,
			   description,
			   createdByUser,
			   DomainId.from(createdByUserId),
			   state,
			   InteractionHistory.from(interactions),
			   attachments,
			   createdAt,
			   updatedAt
		);
	}

	public static ProtocolEntity create(Protocol protocol) {
		var interactions = protocol
			   .getInteractionHistory()
			   .getInteractions()
			   .stream()
			   .map(InteractionColumn::create)
			   .toList();

		return new ProtocolEntity(
			   protocol.getId().getValue(),
			   protocol.getProtocolNumber().getValue(),
			   interactions,
			   protocol.getCreatedAt(),
			   protocol.getUpdatedAt(),
			   protocol.getDescription(),
			   protocol.getCreatedBy(),
			   protocol.getCreatedById().toString(),
			   protocol.getState().getStatus().getValue(),
			   protocol.getQueue().getId().getValue(),
			   protocol.getAttachments()
		);
	}

	protected Interaction parse(InteractionColumn interactionColumn) {
		return Interaction.initialize(
			   DomainId.from(interactionColumn.getAgentId()),
			   interactionColumn.getAgent(),
			   interactionColumn.getText(),
			   interactionColumn.getInteractedOn()
		);
	}

	public UUID getId() {
		return id;
	}

	public void setId(UUID id) {
		this.id = id;
	}

	public String getProtocolNumber() {
		return protocolNumber;
	}

	public void setProtocolNumber(String protocolNumber) {
		this.protocolNumber = protocolNumber;
	}

	public LocalDateTime getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}

	public LocalDateTime getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(LocalDateTime updatedAt) {
		this.updatedAt = updatedAt;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getCreatedByUser() {
		return createdByUser;
	}

	public void setCreatedByUser(String createdByUser) {
		this.createdByUser = createdByUser;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public UUID getQueueId() {
		return queueId;
	}

	public void setQueueId(UUID queueId) {
		this.queueId = queueId;
	}

	public List<InteractionColumn> getInteractions() {
		return interactions;
	}

	public void setInteractions(List<InteractionColumn> interactions) {
		this.interactions = interactions;
	}

	public List<byte[]> getAttachments() {
		return attachments;
	}

	public void setAttachments(List<byte[]> attachments) {
		this.attachments = attachments;
	}
}
