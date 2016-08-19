package com.capgemini.domain;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedNativeQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.capgemini.enumerations.EmployeeFunction;

@NamedNativeQuery(name = "findEmployeesWorkingInSpecificProjectForSpecificTime",
		query = "select * from employees_projects ep left join employees e on ep.id_employee = e.id "
				+ "where case when ep.termination_date is null then timestampdiff(month, ep.hire_date, curdate()) > :number_of_months "
				+ "else timestampdiff(month, ep.hire_date, ep.termination_date) > :number_of_months end "
				+ "and ep.id_project = :id_project "
				+ "group by ep.id_employee;",
				resultClass= EmployeeProjectEntity.class)
@Entity
@Table(name = "employees_projects")
public class EmployeeProjectEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	
	@ManyToOne
	@JoinColumn(name = "id_employee", nullable = false)
	private EmployeeEntity employee;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_project", nullable = false)
	private ProjectEntity project;
	
	@Column(name = "hire_date", nullable = false)
	@Temporal(TemporalType.DATE)
	private Date hireDate;
	
	@Column(name = "termination_date", nullable = true)
	@Temporal(TemporalType.DATE)
	private Date terminationDate;
	
	@Column(name = "employee_function", nullable = false)
	@Enumerated(EnumType.STRING)
	private EmployeeFunction employeeFunction;
	
	@Column(name = "daily_salary", nullable = false, precision = 10, scale = 2)
	private BigDecimal salary;

	protected EmployeeProjectEntity() {
		
	}
	
	public EmployeeProjectEntity(long id, EmployeeEntity employee, ProjectEntity project, Date hireDate,
			Date terminationDate, EmployeeFunction employeeFunction, BigDecimal salary) {
		this.id = id;
		this.employee = employee;
		this.project = project;
		this.hireDate = hireDate;
		this.terminationDate = terminationDate;
		this.employeeFunction = employeeFunction;
		this.salary = salary;
	}
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public EmployeeEntity getEmployee() {
		return employee;
	}

	public void setEmployee(EmployeeEntity employee) {
		this.employee = employee;
	}

	public ProjectEntity getProject() {
		return project;
	}

	public void setProject(ProjectEntity project) {
		this.project = project;
	}

	public Date getHireDate() {
		return hireDate;
	}

	public void setHireDate(Date hireDate) {
		this.hireDate = hireDate;
	}

	public Date getTerminationDate() {
		return terminationDate;
	}

	public void setTerminationDate(Date terminationDate) {
		this.terminationDate = terminationDate;
	}

	public EmployeeFunction getEmployeeFunction() {
		return employeeFunction;
	}

	public void setEmployeeFunction(EmployeeFunction employeeFunction) {
		this.employeeFunction = employeeFunction;
	}

	public BigDecimal getSalary() {
		return salary;
	}

	public void setSalary(BigDecimal salary) {
		this.salary = salary;
	}
}
