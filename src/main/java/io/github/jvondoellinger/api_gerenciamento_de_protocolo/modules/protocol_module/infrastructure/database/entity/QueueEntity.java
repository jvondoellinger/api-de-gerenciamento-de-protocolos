package io.github.jvondoellinger.api_gerenciamento_de_protocolo.modules.protocol_module.infrastructure.database.entity;

import io.github.jvondoellinger.api_gerenciamento_de_protocolo.modules.protocol_module.domain.Queue;
import io.github.jvondoellinger.api_gerenciamento_de_protocolo.modules.protocol_module.domain.valueObjects.DomainId;
import org.springframework.data.annotation.PersistenceCreator;
import org.springframework.data.cassandra.core.cql.PrimaryKeyType;
import org.springframework.data.cassandra.core.mapping.PrimaryKey;
import org.springframework.data.cassandra.core.mapping.PrimaryKeyColumn;
import org.springframework.data.cassandra.core.mapping.Table;

import java.util.UUID;

@Table("queue")
public class QueueEntity implements ObjectEntity<Queue> {
      @PrimaryKey
      private UUID id;
      private String area;
      private String subarea;
      private String createdBy;
      private UUID createdById;

      @PersistenceCreator
      public QueueEntity(UUID id,
                         String area,
                         String subarea,
                         String createdBy,
                         UUID createdById) {
            this.id = id;
            this.area = area;
            this.subarea = subarea;
            this.createdBy = createdBy;
            this.createdById = createdById;
      }
      private QueueEntity(Queue queue) {
            this.id = queue.getId().getValue();
            this.area = queue.getArea();
            this.subarea = queue.getSubarea();
            this.createdBy = queue.getCreatedBy();
            this.createdById = queue.getCreatedById().getValue();
      }

      @Override
      public Queue parse() {
            return new Queue(DomainId.from(id),
                    area,
                    subarea,
                    createdBy,
                    DomainId.from(createdById));
      }

      public static QueueEntity create(Queue queue) {
            return new QueueEntity(queue);
      }


      public UUID getId() {
            return id;
      }

      public void setId(UUID id) {
            this.id = id;
      }

      public String getArea() {
            return area;
      }

      public void setArea(String area) {
            this.area = area;
      }

      public String getSubarea() {
            return subarea;
      }

      public void setSubarea(String subarea) {
            this.subarea = subarea;
      }

      public String getCreatedBy() {
            return createdBy;
      }

      public void setCreatedBy(String createdBy) {
            this.createdBy = createdBy;
      }

      public UUID getCreatedById() {
            return createdById;
      }

      public void setCreatedById(UUID createdById) {
            this.createdById = createdById;
      }
}
