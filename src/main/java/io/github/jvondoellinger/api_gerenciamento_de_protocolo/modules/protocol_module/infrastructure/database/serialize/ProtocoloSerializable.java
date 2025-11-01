package io.github.jvondoellinger.api_gerenciamento_de_protocolo.modules.protocol_module.infrastructure.database.serialize;

import io.github.jvondoellinger.api_gerenciamento_de_protocolo.modules.protocol_module.domain.Queue;
import io.github.jvondoellinger.api_gerenciamento_de_protocolo.modules.protocol_module.domain.valueObjects.DomainId;
import io.github.jvondoellinger.api_gerenciamento_de_protocolo.modules.protocol_module.domain.Protocolo;
import io.github.jvondoellinger.api_gerenciamento_de_protocolo.modules.protocol_module.domain.status.ProtocoloStatusResolver;
import io.github.jvondoellinger.api_gerenciamento_de_protocolo.modules.protocol_module.domain.status.state.ProtocoloStateResolver;
import io.github.jvondoellinger.api_gerenciamento_de_protocolo.modules.protocol_module.domain.valueObjects.ProtocolNumber;
import org.springframework.data.annotation.PersistenceCreator;
import org.springframework.data.cassandra.core.cql.Ordering;
import org.springframework.data.cassandra.core.cql.PrimaryKeyType;
import org.springframework.data.cassandra.core.mapping.CassandraType;
import org.springframework.data.cassandra.core.mapping.PrimaryKeyColumn;
import org.springframework.data.cassandra.core.mapping.Table;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;


@Table("protocolo")
public class ProtocoloSerializable implements ObjectSerialize<Protocolo> {
    @PrimaryKeyColumn(name = "id", type = PrimaryKeyType.CLUSTERED, ordering = Ordering.DESCENDING)
    private UUID id;
    @PrimaryKeyColumn(name = "protocolNumber", type = PrimaryKeyType.PARTITIONED)
    private String protocolNumber;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    private String description;
    private String createdBy;
    private String state;

    @CassandraType(type = CassandraType.Name.LIST, typeArguments = CassandraType.Name.BLOB)
    private List<byte[]> attachments;
    @CassandraType(type = CassandraType.Name.UDT, userTypeName = "queue")
    private UUID queueId;

    public ProtocoloSerializable(Protocolo protocolo) {
        id = protocolo.getId().getValue();
        createdAt = protocolo.getCreatedAt();
        updatedAt = protocolo.getUpdatedAt();
        description = protocolo.getDescription();
        createdBy = protocolo.getCreatedBy();
        state = protocolo.getState().getStatus().getValue();
        attachments = protocolo.getAttachments();
    }

    @PersistenceCreator
    public ProtocoloSerializable(UUID id,
                                 String protocolNumber,
                                 LocalDateTime createdAt,
                                 LocalDateTime updatedAt,
                                 String description,
                                 String createdBy,
                                 String state,
                                 UUID queueId,
                                 List<byte[]> attachments) {
        this.id = id;
        this.protocolNumber = protocolNumber;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.description = description;
        this.createdBy = createdBy;
        this.state = state;
        this.attachments = attachments;
        this.queueId = queueId;
    }

    public Protocolo parse() {
        var status = ProtocoloStatusResolver.resolve(state); // Dynamic method to get status
        var state = ProtocoloStateResolver.resolve(status); // Dynamic method to get state
        return new Protocolo(
                DomainId.from(id),
                ProtocolNumber.parse(protocolNumber),
                null, // Como retornar....?
                description,
                createdBy,
                state,
                null,
                attachments,
                createdAt,
                updatedAt
        );
    }

    public static ProtocoloSerializable create(Protocolo interaction) {
        return new ProtocoloSerializable(
            interaction.getId().getValue(),
                interaction.getProtocolNumber().getValue(),
                interaction.getCreatedAt(),
                interaction.getUpdatedAt(),
                interaction.getDescription(),
                interaction.getCreatedBy(),
                interaction.getState().getStatus().getValue(),
                new QueueSerialize(interaction.getQueue()),
                interaction.getAttachments()
        );
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
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

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public List<byte[]> getAttachments() {
        return attachments;
    }

    public void setAttachments(List<byte[]> attachments) {
        this.attachments = attachments;
    }

    public String getProtocolNumber() {
        return protocolNumber;
    }

    public void setProtocolNumber(String protocolNumber) {
        this.protocolNumber = protocolNumber;
    }

    public QueueSerialize getQueue() {
        return queue;
    }

    public void setQueue(QueueSerialize queue) {
        this.queue = queue;
    }
}
