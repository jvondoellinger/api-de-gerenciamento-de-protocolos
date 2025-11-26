package io.github.jvondoellinger.api_gerenciamento_de_protocolo.modules.protocol_module.domain.valueObjects;

import io.github.jvondoellinger.api_gerenciamento_de_protocolo.modules.protocol_module.domain.exception.ParsingException;
import io.github.jvondoellinger.api_gerenciamento_de_protocolo.modules.protocol_module.domain.exception.ParsingFormatException;

import java.util.UUID;

public class DomainId {
    private final UUID value;

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
    public static DomainId from(String value) {
        try {
            var uuid = UUID.fromString(value);
            return new DomainId(uuid);
        } catch (RuntimeException e) {
            throw new ParsingException("Isn't possible convert the value on DomainId value, because it's not valid value to conversion!");
        }
    }

    @Override
    public String toString() {
        return getValue().toString();
    }
}
