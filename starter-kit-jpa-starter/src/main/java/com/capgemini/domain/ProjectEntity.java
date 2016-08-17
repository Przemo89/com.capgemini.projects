package com.capgemini.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Type;

@Entity
@Table(name = "projects")
public class ProjectEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	
	@OneToOne
	@JoinColumn(name = "id_manager")
	private EmployeeEntity manager;
	
	@Column(name = "name", nullable = false, length = 255)
	private String name;
	@Column(name = "is_internal", nullable = false)
	@Type(type = "org.hibernate.type.NumericBooleanType")
	private boolean isInternal;
	
	protected ProjectEntity() {
		
	}
	
	public ProjectEntity(long id, EmployeeEntity manager, String name, boolean isInternal) {
		this.id = id;
		this.manager = manager;
		this.name = name;
		this.isInternal = isInternal;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public EmployeeEntity getManager() {
		return manager;
	}

	public void setManager(EmployeeEntity manager) {
		this.manager = manager;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public boolean isInternal() {
		return isInternal;
	}

	public void setInternal(boolean isInternal) {
		this.isInternal = isInternal;
	}
}
