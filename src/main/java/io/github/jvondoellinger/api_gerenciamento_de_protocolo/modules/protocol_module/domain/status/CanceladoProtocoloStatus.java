package io.github.jvondoellinger.api_gerenciamento_de_protocolo.modules.protocol_module.domain.status;

public class CanceladoProtocoloStatus extends ProtocoloStatus {
    public CanceladoProtocoloStatus() {
        super(getPattern());
    }

    public static String getPattern() {
        return "Cancelado";
    }
}
