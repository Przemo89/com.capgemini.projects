package com.capgemini.domain;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.capgemini.listeners.EmployeeEntityListener;

@Entity
@Table(name = "employees")
@EntityListeners(EmployeeEntityListener.class)
public class EmployeeEntity implements Serializable{



	/**
	 * 
	 */
	private static final long serialVersionUID = 1346834L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;

	@ManyToOne
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
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "employee")
	private List<EmployeeProjectEntity> employeeProjects;

	public EmployeeEntity(DepartamentEntity departament, String firstName, String lastName, String pin,
			Date birthDate, String email, String phoneHomeNumber, String phoneWorkNumber) {
		this.departament = departament;
		this.firstName = firstName;
		this.lastName = lastName;
		this.pin = pin;
		this.birthDate = birthDate;
		this.email = email;
		this.phoneHomeNumber = phoneHomeNumber;
		this.phoneWorkNumber = phoneWorkNumber;
	}

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
	
//	@Override
//	public boolean equals(Object obj) {
//		if (this == obj)
//			return true;
//		if (obj == null)
//			return false;
//		if (getClass() != obj.getClass())
//			return false;
//		EmployeeEntity other = (EmployeeEntity) obj;
//		if (birthDate == null) {
//			if (other.birthDate != null)
//				return false;
//		} else if (!birthDate.equals(other.birthDate))
//			return false;
//		if (departament == null) {
//			if (other.departament != null)
//				return false;
//		} else if (!departament.equals(other.departament))
//			return false;
//		if (email == null) {
//			if (other.email != null)
//				return false;
//		} else if (!email.equals(other.email))
//			return false;
////		if (employeeProjects == null) {
////			if (other.employeeProjects != null)
////				return false;
////		} else if (!employeeProjects.equals(other.employeeProjects))
////			return false;
//		if (firstName == null) {
//			if (other.firstName != null)
//				return false;
//		} else if (!firstName.equals(other.firstName))
//			return false;
//		if (id != other.id)
//			return false;
//		if (lastName == null) {
//			if (other.lastName != null)
//				return false;
//		} else if (!lastName.equals(other.lastName))
//			return false;
//		if (phoneHomeNumber == null) {
//			if (other.phoneHomeNumber != null)
//				return false;
//		} else if (!phoneHomeNumber.equals(other.phoneHomeNumber))
//			return false;
//		if (phoneWorkNumber == null) {
//			if (other.phoneWorkNumber != null)
//				return false;
//		} else if (!phoneWorkNumber.equals(other.phoneWorkNumber))
//			return false;
//		if (pin == null) {
//			if (other.pin != null)
//				return false;
//		} else if (!pin.equals(other.pin))
//			return false;
//		return true;
//	}
	
}
