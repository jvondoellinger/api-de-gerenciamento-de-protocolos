package io.github.jvondoellinger.api_gerenciamento_de_protocolo.modules.protocol_module.domain;

import io.github.jvondoellinger.api_gerenciamento_de_protocolo.modules.protocol_module.domain.valueObjects.DomainId;

public class Queue {
    private final DomainId id;
    private final String area;
    private final String subarea;
    private final String createdBy;
    private final DomainId createdById;


    public Queue(String area,
                 String subarea,
                 String createdBy,
                 DomainId createdById) {
        this.id = new DomainId();
        this.area = area;
        this.subarea = subarea;
        this.createdBy = createdBy;
        this.createdById = createdById;
    }

    public Queue(DomainId id,
                 String area,
                 String subarea,
                 String createdBy,
                 DomainId createdById) {
        this.id = id;
        this.area = area;
        this.subarea = subarea;
        this.createdBy = createdBy;
        this.createdById = createdById;
    }

    public DomainId getId() {
        return id;
    }

    public String getArea() {
        return area;
    }

    public String getSubarea() {
        return subarea;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public DomainId getCreatedById() {
        return createdById;
    }

    public boolean isComplete() {
        return
            (area != null) &&
            (subarea != null) &&
            (createdBy != null) &&
            (createdById != null);
    }
}
