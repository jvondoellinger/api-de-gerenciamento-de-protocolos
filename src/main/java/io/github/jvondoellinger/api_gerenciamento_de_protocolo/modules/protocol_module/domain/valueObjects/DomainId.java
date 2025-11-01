package io.github.jvondoellinger.api_gerenciamento_de_protocolo.modules.protocol_module.domain.valueObjects;

import java.util.UUID;

public class DomainId {
    private UUID value;

    public DomainId() {
        this.value = UUID.randomUUID();
    }

    public DomainId(UUID value) {
        this.value = value;
    }

    public UUID getValue() {
        return value;
    }

    public static DomainId from(UUID uuid) {
        return new DomainId(uuid);
    }
}
