package io.github.jvondoellinger.api_gerenciamento_de_protocolo.modules.protocol_module.infrastructure.database.protocol;

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
import org.springframework.data.cassandra.core.mapping.PrimaryKeyColumn;
import org.springframework.data.cassandra.core.mapping.Table;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;


@Table("protocol")
public class ProtocolEntity implements ObjectEntity<Protocol> {
    @PrimaryKeyColumn(name = "id", type = PrimaryKeyType.CLUSTERED, ordering = Ordering.DESCENDING)
    private UUID id;
    @PrimaryKeyColumn(name = "protocolNumber", type = PrimaryKeyType.PARTITIONED)
    private String protocolNumber;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    private String description;
    private String createdBy;
    private String state;

    private UUID queueId;

    @CassandraType(type = CassandraType.Name.LIST, typeArguments = CassandraType.Name.BLOB)
    private List<byte[]> attachments;

    public ProtocolEntity(Protocol protocol) {
        id = protocol.getId().getValue();
        createdAt = protocol.getCreatedAt();
        updatedAt = protocol.getUpdatedAt();
        description = protocol.getDescription();
        createdBy = protocol.getCreatedBy();
        state = protocol.getState().getStatus().getValue();
        attachments = protocol.getAttachments();
    }

    @PersistenceCreator
    public ProtocolEntity(UUID id,
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

    public Protocol parse() {
        var status = ProtocoloStatusResolver.resolve(state); // Dynamic method to get status
        var state = ProtocoloStateResolver.resolve(status); // Dynamic method to get state
        var queue = Queue.createWithIdOnly(DomainId.from(queueId));

        return new Protocol(
                DomainId.from(id),
                ProtocolNumber.parse(protocolNumber),
                queue,
                description,
                createdBy,
                state,
                null,
                attachments,
                createdAt,
                updatedAt
        );
    }

    public static ProtocolEntity create(Protocol protocol) {
        return new ProtocolEntity(
            protocol.getId().getValue(),
                protocol.getProtocolNumber().getValue(),
                protocol.getCreatedAt(),
                protocol.getUpdatedAt(),
                protocol.getDescription(),
                protocol.getCreatedBy(),
                protocol.getState().getStatus().getValue(),
                protocol.getQueue().getId().getValue(),
                protocol.getAttachments()
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

    public UUID getQueueId() {
        return queueId;
    }

    public void setQueueId(UUID queueId) {
        this.queueId = queueId;
    }
}
