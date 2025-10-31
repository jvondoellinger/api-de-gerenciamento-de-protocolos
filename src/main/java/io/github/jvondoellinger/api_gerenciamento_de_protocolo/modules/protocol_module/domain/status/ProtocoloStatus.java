package io.github.jvondoellinger.api_gerenciamento_de_protocolo.modules.protocol_module.domain.status;

public abstract class ProtocoloStatus {
    private String value;

    protected ProtocoloStatus(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
