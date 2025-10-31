package io.github.jvondoellinger.api_gerenciamento_de_protocolo.modules.protocol_module.domain;

import io.github.jvondoellinger.api_gerenciamento_de_protocolo.modules.protocol_module.domain.valueObjects.ProtocolDomainId;

public class Queue {
    private ProtocolDomainId id;
    private String area;
    private String subarea;

    public Queue(ProtocolDomainId id, String area, String subarea) {
        this.id = id;
        this.area = area;
        this.subarea = subarea;
    }

    public ProtocolDomainId getId() {
        return id;
    }

    public String getArea() {
        return area;
    }

    public String getSubarea() {
        return subarea;
    }
}
