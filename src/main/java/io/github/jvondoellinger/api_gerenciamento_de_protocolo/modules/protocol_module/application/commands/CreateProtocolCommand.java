package io.github.jvondoellinger.api_gerenciamento_de_protocolo.modules.protocol_module.application.commands;

public class CreateProtocolCommand {
    public CreateProtocolCommand(String description, String createdBy) {
        this.description = description;
        this.createdBy = createdBy;
    }

    private String description;
    private String createdBy;

    public String getDescription() {
        return description;
    }

    public String getCreatedBy() {
        return createdBy;
    }


    public void setDescription(String description) {
        this.description = description;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }
}
