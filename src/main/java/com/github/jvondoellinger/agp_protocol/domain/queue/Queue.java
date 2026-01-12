package com.github.jvondoellinger.agp_protocol.domain.queue;

import com.github.jvondoellinger.agp_protocol.domain.DomainId;
import com.github.jvondoellinger.agp_protocol.domain.profile.UserProfile;

import java.time.LocalDateTime;

public class Queue {
	private final DomainId domainId;
	private final String area;
	private final String subarea;
	private final UserProfile createdBy;
	private final LocalDateTime createdAt;
	private final LocalDateTime updatedAt;
	private final UserProfile lastUpdatedBy;

	public Queue(DomainId domainId, String area, String subarea, UserProfile createdBy, LocalDateTime createdAt, LocalDateTime updatedAt, UserProfile lastUpdatedBy) {
		this.domainId = domainId;
		this.area = area;
		this.subarea = subarea;
		this.createdBy = createdBy;
		this.createdAt = createdAt;
		this.updatedAt = updatedAt;
		this.lastUpdatedBy = lastUpdatedBy;
	}

	public Queue(String area, String subarea, UserProfile createdBy) {
		this.domainId = DomainId.create();
		this.area = area;
		this.subarea = subarea;
		this.createdBy = createdBy;
		this.createdAt = LocalDateTime.now();
		this.updatedAt = null;
		this.lastUpdatedBy = null;
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
	public UserProfile getCreatedBy() {
		return createdBy;
	}
	public LocalDateTime getCreatedAt() {
		return createdAt;
	}
	public LocalDateTime getUpdatedAt() {
		return updatedAt;
	}
	public UserProfile getLastUpdatedBy() {
		return lastUpdatedBy;
	}
}
