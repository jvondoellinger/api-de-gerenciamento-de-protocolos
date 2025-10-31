package io.github.jvondoellinger.api_gerenciamento_de_protocolo.modules.protocol_module.domain.status;

public class SolucionadoProtocoloStatus extends ProtocoloStatus {
    public SolucionadoProtocoloStatus() {
        super(getPattern());
    }

    public static String getPattern() {
        return "Solucionado";
    }
}
