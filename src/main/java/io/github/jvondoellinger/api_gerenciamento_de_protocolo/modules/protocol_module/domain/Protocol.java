package io.github.jvondoellinger.api_gerenciamento_de_protocolo.modules.protocol_module.domain;

import io.github.jvondoellinger.api_gerenciamento_de_protocolo.modules.protocol_module.domain.exception.DomainException;
import io.github.jvondoellinger.api_gerenciamento_de_protocolo.modules.protocol_module.domain.valueObjects.DomainId;
import io.github.jvondoellinger.api_gerenciamento_de_protocolo.modules.protocol_module.domain.status.PendenteProtocoloStatus;
import io.github.jvondoellinger.api_gerenciamento_de_protocolo.modules.protocol_module.domain.status.state.PendenteProtocoloState;
import io.github.jvondoellinger.api_gerenciamento_de_protocolo.modules.protocol_module.domain.status.state.ProtocoloState;
import io.github.jvondoellinger.api_gerenciamento_de_protocolo.modules.protocol_module.domain.valueObjects.ProtocolNumber;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Protocol {
    private final DomainId id;

    private final ProtocolNumber protocolNumber;

    private final String description;
    private final String createdBy;

    private final InteractionHistory interactionHistory;

    private Queue queue; // A fila pode mudar, então não deve ser final.

    private ProtocoloState state; // O estado pode alterar conforme o atendimento.

    private final List<byte[]> attachments;

    private final LocalDateTime createdAt;
    private final LocalDateTime updatedAt;

    // ? Quando se cria um protocol, somente há os dados da descrição do chamado e os dados do agente
    public Protocol(Queue queue,
                    String description,
                    String createdBy) {

        this.queue = queue;
        this.description = description;

        // Creator
        this.createdBy = createdBy;

        // Public unique key
        this.protocolNumber = ProtocolNumber.generate();

        this.state = new PendenteProtocoloState(new PendenteProtocoloStatus());
        this.attachments = new ArrayList<>();
        this.interactionHistory = new InteractionHistory();
        // Timings...
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
        // Private unique key
        id = new DomainId();
    }

    public Protocol(DomainId id,
                    ProtocolNumber protocolNumber,
                    Queue queue,
                    String description,
                    String createdBy,
                    ProtocoloState state,
                    InteractionHistory interactionHistory,
                    List<byte[]> attachments,
                    LocalDateTime createdAt,
                    LocalDateTime updatedAt) {
        this.id = id;
        this.protocolNumber = protocolNumber;
        this.queue = queue;
        this.description = description;
        this.createdBy = createdBy;
        this.interactionHistory = interactionHistory;
        this.state = state;
        this.attachments = attachments;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    // ? Getters ===========================================
    public List<byte[]> getAttachments() {
        return attachments;
    }

    public InteractionHistory getInteractionHistory() {
        return interactionHistory;
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

    public DomainId getId() {
        return id;
    }

    public ProtocolNumber getProtocolNumber() {
        return protocolNumber;
    }

    public Queue getQueue() {
        return queue;
    }

    // ? Custom ===========================================
    public void updateState(ProtocoloState state) {
        this.state = state;
    }

    public void interact(Interaction interaction) {
        if (Objects.isNull(interaction)){
            throw new DomainException("Adding a null interaction is not allowed");
        }
        interactionHistory.addInteraction(interaction);
    }

    public void delegate(Queue delegation) {
        this.queue = delegation;
    }

}
