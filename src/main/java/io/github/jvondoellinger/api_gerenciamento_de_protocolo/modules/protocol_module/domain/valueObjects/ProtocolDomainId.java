package io.github.jvondoellinger.api_gerenciamento_de_protocolo.modules.protocol_module.domain.valueObjects;

import java.util.UUID;

public class ProtocolDomainId {
    private UUID value;

    public ProtocolDomainId() {
        this.value = UUID.randomUUID();
    }

    public ProtocolDomainId(UUID value) {
        this.value = value;
    }

    public UUID getValue() {
        return value;
    }
}
