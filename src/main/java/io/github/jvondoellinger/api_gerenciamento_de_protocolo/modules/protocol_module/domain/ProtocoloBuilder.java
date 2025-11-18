package io.github.jvondoellinger.api_gerenciamento_de_protocolo.modules.protocol_module.domain;

import io.github.jvondoellinger.api_gerenciamento_de_protocolo.modules.protocol_module.domain.exception.BuildingException;
import io.github.jvondoellinger.api_gerenciamento_de_protocolo.modules.protocol_module.domain.status.state.ProtocoloState;
import io.github.jvondoellinger.api_gerenciamento_de_protocolo.modules.protocol_module.domain.valueObjects.DomainId;
import io.github.jvondoellinger.api_gerenciamento_de_protocolo.modules.protocol_module.domain.valueObjects.ProtocolNumber;
import io.micrometer.common.util.StringUtils;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

public final class ProtocoloBuilder {
      private DomainId id = new DomainId();
      private ProtocolNumber protocolNumber = ProtocolNumber.generate();
      private String description;
      private String createdBy;
      private InteractionHistory interactions;
      private Queue queue;
      private ProtocoloState state;
      private List<byte[]> attachments;
      private LocalDateTime createdAt = LocalDateTime.now();
      private LocalDateTime updatedAt = LocalDateTime.now();

      private ProtocoloBuilder() {
      }

      public static ProtocoloBuilder builder() {
            return new ProtocoloBuilder();
      }

      public ProtocoloBuilder withId(DomainId id) {
            this.id = id;
            return this;
      }

      public ProtocoloBuilder withProtocolNumber(ProtocolNumber protocolNumber) {
            this.protocolNumber = protocolNumber;
            return this;
      }

      public ProtocoloBuilder withDescription(String description) {
            this.description = description;
            return this;
      }

      public ProtocoloBuilder withCreatedBy(String createdBy) {
            this.createdBy = createdBy;
            return this;
      }

      public ProtocoloBuilder withInteractions(InteractionHistory interactions) {
            this.interactions = interactions;
            return this;
      }

      public ProtocoloBuilder withQueue(Queue queue) {
            this.queue = queue;
            return this;
      }

      public ProtocoloBuilder withState(ProtocoloState state) {
            this.state = state;
            return this;
      }

      public ProtocoloBuilder withAttachments(List<byte[]> attachments) {
            this.attachments = attachments;
            return this;
      }

      public ProtocoloBuilder withCreatedAt(LocalDateTime createdAt) {
            this.createdAt = createdAt;
            return this;
      }

      public ProtocoloBuilder withUpdatedAt(LocalDateTime updatedAt) {
            this.updatedAt = updatedAt;
            return this;
      }

      public Protocolo build() {
            if (description == null) {
                  throw new BuildingException("'Description' cannot be null.");
            }
            if (createdBy == null) {
                  throw new BuildingException("'CreatedBy' cannot be null.");
            }
            return new Protocolo(id,
                    protocolNumber,
                    queue,
                    description,
                    createdBy,
                    state,
                    interactions,
                    attachments,
                    createdAt,
                    updatedAt);
      }
}
