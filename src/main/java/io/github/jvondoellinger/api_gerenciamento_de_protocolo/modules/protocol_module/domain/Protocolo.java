package io.github.jvondoellinger.api_gerenciamento_de_protocolo.modules.protocol_module.domain;

import io.github.jvondoellinger.api_gerenciamento_de_protocolo.modules.protocol_module.domain.exception.DomainException;
import io.github.jvondoellinger.api_gerenciamento_de_protocolo.modules.protocol_module.domain.valueObjects.ProtocolDomainId;
import io.github.jvondoellinger.api_gerenciamento_de_protocolo.modules.protocol_module.domain.status.PendenteProtocoloStatus;
import io.github.jvondoellinger.api_gerenciamento_de_protocolo.modules.protocol_module.domain.status.state.PendenteProtocoloState;
import io.github.jvondoellinger.api_gerenciamento_de_protocolo.modules.protocol_module.domain.status.state.ProtocoloState;
import io.github.jvondoellinger.api_gerenciamento_de_protocolo.modules.protocol_module.domain.valueObjects.ProtocolNumber;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Protocolo {
    private ProtocolDomainId id;

    private ProtocolNumber protocolNumber;

    private String description;
    private String createdBy;

    private InteractionHistory interactions;
    private Queue queue;

    private ProtocoloState state;

    private List<byte[]> attachments;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    // ? Quando se cria um protocolo, somente há os dados da descrição do chamado e os dados do agente 
    public Protocolo(String description,
                     String createdBy) {
        this.description = description;
        this.createdBy = createdBy;
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
        this.protocolNumber = ProtocolNumber.generate();
        this.interactions = new InteractionHistory();
        this.attachments = new ArrayList<>();
        this.state = new PendenteProtocoloState(new PendenteProtocoloStatus());
        id = new ProtocolDomainId();
    }

    public Protocolo(ProtocolDomainId id,
                     ProtocolNumber protocolNumber,
                     String description,
                     String createdBy,
                     ProtocoloState state,
                     InteractionHistory interactions,
                     List<byte[]> attachments,
                     LocalDateTime createdAt,
                     LocalDateTime updatedAt) {
        this.id = id;
        this.protocolNumber = protocolNumber;
        this.description = description;
        this.createdBy = createdBy;
        this.interactions = interactions;
        this.state = state;
        this.attachments = attachments;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    // ? Getters ===========================================
    public List<byte[]> getAttachments() {
        return attachments;
    }

    public InteractionHistory getInteractions() {
        return interactions;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public String getDescription() {
        return description;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public ProtocoloState getState() {
        return state;
    }

    public ProtocolDomainId getId() {
        return id;
    }

    public ProtocolNumber getProtocolNumber() {
        return protocolNumber;
    }

    // ? Custom ===========================================
    public void updateState(ProtocoloState state) {
        this.state = state;
    }

    public void interact(Interaction interaction) {
        if (Objects.isNull(interaction)){
            throw new DomainException("Adding a null interaction is not allowed");
        }
        if (Objects.isNull(interactions)) {
            interactions = new InteractionHistory();
        }
        interactions.addInteraction(interaction);
    }

    private void init() {}
}
