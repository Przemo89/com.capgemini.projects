package com.capgemini.domain;

import java.util.Date;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "employees")
public class EmployeeEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;

	@OneToOne
	@JoinColumn(name = "id_departament")
	private DepartamentEntity departament;

	@Column(name = "first_name", nullable = false, length = 45)
	private String firstName;
	@Column(name = "last_name", nullable = false, length = 45)
	private String lastName;
	@Column(name = "pin", nullable = false, length = 11, unique = true)
	private String pin;
	@Temporal(TemporalType.DATE)
	@Column(name = "birth_date", nullable = false)
	private Date birthDate;
	@Column(name = "email", nullable = false, length = 45, unique = true)
	private String email;
	@Column(name = "phone_home", nullable = false, length = 15, unique = true)
	private String phoneHomeNumber;
	@Column(name = "phone_work", nullable = false, length = 15, unique = true)
	private String phoneWorkNumber;
	
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "employee")
	private List<EmployeeProjectEntity> employeeProjects;

	public EmployeeEntity(long id, DepartamentEntity departament, String firstName, String lastName, String pin,
			Date birthDate, String email, String phoneHomeNumber, String phoneWorkNumber,
			List<EmployeeProjectEntity> employeeProjects) {
		this.id = id;
		this.departament = departament;
		this.firstName = firstName;
		this.lastName = lastName;
		this.pin = pin;
		this.birthDate = birthDate;
		this.email = email;
		this.phoneHomeNumber = phoneHomeNumber;
		this.phoneWorkNumber = phoneWorkNumber;
		this.employeeProjects = employeeProjects;
	}



	protected EmployeeEntity() {
		
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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhoneHomeNumber() {
		return phoneHomeNumber;
	}

	public void setPhoneHomeNumber(String phoneHomeNumber) {
		this.phoneHomeNumber = phoneHomeNumber;
	}

	public String getPhoneWorkNumber() {
		return phoneWorkNumber;
	}

	public void setPhoneWorkNumber(String phoneWorkNumber) {
		this.phoneWorkNumber = phoneWorkNumber;
	}
	
	public List<EmployeeProjectEntity> getEmployeeProjects() {
		return employeeProjects;
	}

	public void setEmployeeProjects(List<EmployeeProjectEntity> employeeProjects) {
		this.employeeProjects = employeeProjects;
	}
	
}
