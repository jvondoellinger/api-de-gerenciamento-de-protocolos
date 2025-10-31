package io.github.jvondoellinger.api_gerenciamento_de_protocolo.modules.protocol_module.domain.contracts.persistence.filters;

import io.github.jvondoellinger.api_gerenciamento_de_protocolo.modules.protocol_module.domain.valueObjects.ProtocolNumber;

public class ProtocolNumberFilter {
    private ProtocolNumber protocolNumber;

    public ProtocolNumberFilter(ProtocolNumber protocolNumber) {
        this.protocolNumber = protocolNumber;
    }

    public ProtocolNumber getProtocolNumber() {
        return protocolNumber;
    }
}
