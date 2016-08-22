package com.capgemini.domain;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedNativeQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@NamedNativeQuery(name = "findEmployeesWorkingInSpecificProjectForSpecificTime",
query = "select e.*  "
		+ "from employees e left join employees_projects ep on e.id = ep.id_employee "
		+ "where case when ep.termination_date is null then timestampdiff(sql_tsi_month, ep.hire_date, curdate()) > :number_of_months "
		+ "else timestampdiff(sql_tsi_month, ep.hire_date, ep.termination_date) > :number_of_months end "
		+ "and ep.id_project = :id_project "
		+ "group by e.id "
		+ "order by e.id asc;", resultClass= EmployeeEntity.class)
@Entity
@Table(name = "employees")
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class EmployeeEntity extends AbstractVersionControlEntity implements Serializable{

	private static final long serialVersionUID = 1346834L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;

	@ManyToOne
	@JoinColumn(name = "id_departament")
	private DepartamentEntity departament;
	
	@OneToOne(mappedBy = "manager", fetch = FetchType.LAZY, optional = true)
	private ProjectEntity project;

	@Column(name = "first_name", nullable = false, length = 45)
	private String firstName;
	@Column(name = "last_name", nullable = false, length = 45)
	private String lastName;
	@Column(name = "pin", nullable = false, length = 11, unique = true)
	private String pin;
	@Temporal(TemporalType.DATE)
	@Column(name = "birth_date", nullable = false)
	private Date birthDate;
	
	@Embedded
	private ContactDetails contactDetails;
	
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "employee")
	private List<EmployeeProjectEntity> employeeProjects;

	public EmployeeEntity() {
		
	}
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public DepartamentEntity getDepartament() {
		return departament;
	}

	public void setDepartament(DepartamentEntity departament) {
		this.departament = departament;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getPin() {
		return pin;
	}

	public void setPin(String pin) {
		this.pin = pin;
	}

	public Date getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}
	
	public List<EmployeeProjectEntity> getEmployeeProjects() {
		return employeeProjects;
	}

	public void setEmployeeProjects(List<EmployeeProjectEntity> employeeProjects) {
		this.employeeProjects = employeeProjects;
	}
	
	public ContactDetails getContactDetails() {
		return contactDetails;
	}

	public void setContactDetails(ContactDetails contactDetails) {
		this.contactDetails = contactDetails;
	}
	
	public ProjectEntity getProject() {
		return project;
	}

	public void setProject(ProjectEntity project) {
		this.project = project;
	}
}
