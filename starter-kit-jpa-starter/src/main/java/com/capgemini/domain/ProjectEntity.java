package com.capgemini.domain;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Type;

@Entity
@Table(name = "projects")
public class ProjectEntity extends AbstractVersionControlEntity implements Serializable {

	private static final long serialVersionUID = -3478563478561L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	
	@OneToOne
	@JoinColumn(name = "id_manager", nullable = true)
	private EmployeeEntity manager;
	
	@Column(name = "name", nullable = false, unique = true, length = 255)
	private String name;
	@Column(name = "is_internal", nullable = false)
	@Type(type = "org.hibernate.type.NumericBooleanType")
	private boolean isInternal;
	
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "project")
	private List<EmployeeProjectEntity> employeeProjects;
	
	public ProjectEntity() {
		
	}
	
	public ProjectEntity(long id, EmployeeEntity manager, String name, boolean isInternal,
			List<EmployeeProjectEntity> employeeProjects) {
		this.id = id;
		this.manager = manager;
		this.name = name;
		this.isInternal = isInternal;
		this.employeeProjects = employeeProjects;
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
	
	public List<EmployeeProjectEntity> getEmployeeProjects() {
		return employeeProjects;
	}

	public void setEmployeeProjects(List<EmployeeProjectEntity> employeeProjects) {
		this.employeeProjects = employeeProjects;
	}
}
