package com.github.jvondoellinger.agp_protocol.infrastructure.entity;

import com.github.jvondoellinger.agp_protocol.domain.DomainId;
import com.github.jvondoellinger.agp_protocol.domain.profile.UserProfile;
import com.github.jvondoellinger.agp_protocol.domain.queue.Queue;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Entity
@Table(name = "tb_queue")
public class QueueDbEntity implements DbEntity<Queue> {
	@Id
	private DomainId domainId;

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
		this.domainId = queue.getDomainId();
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
			   domainId,
			   area,
			   subarea,
			   createdBy.toDomainEntity(),
			   createdAt,
			   updatedAt,
			   lastUpdatedBy.toDomainEntity()
		);
	}

	public DomainId getDomainId() {
		return domainId;
	}
	public String getArea() {
		return area;
	}
	public String getSubarea() {
		return subarea;
	}
	public UserProfileDbEntity getCreatedBy() {
		return createdBy;
	}
	public LocalDateTime getCreatedAt() {
		return createdAt;
	}
	public LocalDateTime getUpdatedAt() {
		return updatedAt;
	}
	public UserProfileDbEntity getLastUpdatedBy() {
		return lastUpdatedBy;
	}

	public void setDomainId(DomainId domainId) {
		this.domainId = domainId;
	}
	public void setArea(String area) {
		this.area = area;
	}
	public void setSubarea(String subarea) {
		this.subarea = subarea;
	}
	public void setCreatedBy(UserProfileDbEntity createdBy) {
		this.createdBy = createdBy;
	}
	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}
	public void setUpdatedAt(LocalDateTime updatedAt) {
		this.updatedAt = updatedAt;
	}
	public void setLastUpdatedBy(UserProfileDbEntity lastUpdatedBy) {
		this.lastUpdatedBy = lastUpdatedBy;
	}
}
