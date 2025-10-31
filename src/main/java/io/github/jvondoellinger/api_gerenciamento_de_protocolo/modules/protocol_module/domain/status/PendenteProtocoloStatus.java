package io.github.jvondoellinger.api_gerenciamento_de_protocolo.modules.protocol_module.domain.status;

public class PendenteProtocoloStatus extends ProtocoloStatus {
    public PendenteProtocoloStatus() {
        super(getPattern());
    }

    public static String getPattern() {
        return "Pendente";
    }
}
