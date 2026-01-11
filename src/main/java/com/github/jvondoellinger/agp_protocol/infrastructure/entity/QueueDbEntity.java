package com.github.jvondoellinger.agp_protocol.infrastructure.entity;

import com.github.jvondoellinger.agp_protocol.domain.DomainId;
import com.github.jvondoellinger.agp_protocol.domain.queue.Queue;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

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

	public QueueDbEntity(Queue queue) {
		this.domainId = queue.getDomainId().toString();
		this.area = queue.getArea();
		this.subarea = queue.getSubarea();
		this.createdBy = new UserProfileDbEntity(queue.getCreatedById());
		this.createdAt = LocalDateTime.now();
		this.updatedAt = LocalDateTime.now();
		this.lastUpdatedBy = new UserProfileDbEntity(queue.getLastUpdatedBy());
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
