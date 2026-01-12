package com.github.jvondoellinger.agp_protocol.infrastructure.entity;

import com.github.jvondoellinger.agp_protocol.domain.DomainId;
import com.github.jvondoellinger.agp_protocol.domain.queue.Queue;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.annotation.PersistenceCreator;

import java.time.LocalDateTime;

@Entity
@Table(name = "tb_queue")
@Getter
@Setter
public class QueueDbEntity implements DbEntity<Queue> {
	@Id
	private String domainId;

	private String area;
	private String subarea;

	@ManyToOne
	private UserProfileDbEntity createdBy;

	@CreationTimestamp
	private LocalDateTime createdAt;

	@UpdateTimestamp
	private LocalDateTime updatedAt;

	@ManyToOne
	private UserProfileDbEntity lastUpdatedBy;

	protected QueueDbEntity() {}
	public QueueDbEntity(Queue queue) {
		this.domainId = queue.getDomainId().toString();
		this.area = queue.getArea();
		this.subarea = queue.getSubarea();
		this.createdBy = new UserProfileDbEntity(queue.getCreatedBy());
		this.createdAt = LocalDateTime.now();
		this.updatedAt = LocalDateTime.now();
		this.lastUpdatedBy = new UserProfileDbEntity(queue.getLastUpdatedBy());
	}

	@PersistenceCreator
	public QueueDbEntity(String domainId, String area, String subarea, UserProfileDbEntity createdBy, LocalDateTime createdAt, LocalDateTime updatedAt, UserProfileDbEntity lastUpdatedBy) {
		this.domainId = domainId;
		this.area = area;
		this.subarea = subarea;
		this.createdBy = createdBy;
		this.createdAt = createdAt;
		this.updatedAt = updatedAt;
		this.lastUpdatedBy = lastUpdatedBy;
	}

	@Override
	public Queue toDomainEntity() {
		return new Queue(
			   DomainId.parse(domainId),
			   area,
			   subarea,
			   createdBy.toDomainEntity(),
			   createdAt,
			   updatedAt,
			   lastUpdatedBy.toDomainEntity()
		);
	}
}
