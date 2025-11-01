package io.github.jvondoellinger.api_gerenciamento_de_protocolo.modules.protocol_module.application.commands;

import io.github.jvondoellinger.api_gerenciamento_de_protocolo.modules.protocol_module.application.dtos.QueueDto;

public class CreateProtocolCommand {

    public CreateProtocolCommand(String description, String createdBy, QueueDto queueDto) {
        this.description = description;
        this.createdBy = createdBy;
        this.queueDto = queueDto;
    }

    private String description;
    private String createdBy;
    private QueueDto queueDto;

    public String getDescription() {
        return description;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public QueueDto getQueueDto() {
        return queueDto;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public void setQueueDto(QueueDto queueDto) {
        this.queueDto = queueDto;
    }
}
