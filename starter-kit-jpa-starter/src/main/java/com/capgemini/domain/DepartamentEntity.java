package com.capgemini.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "departaments")
public class DepartamentEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;

	@Column(name = "name", nullable = false, length = 255, unique = true)
	private String nameDepartament;
	@Column(name = "email", nullable = false, length = 45, unique = true)
	private String email;
	@Column(name = "phone_stationary", nullable = false, length = 15, unique = true)
	private String phoneStationaryNumber;
	@Column(name = "phone_mobile", nullable = false, length = 15, unique = true)
	private String phoneMobileNumber;

	public DepartamentEntity() {
		
	}
	
	public DepartamentEntity(long id, String nameDepartament, String email, String phoneStationaryNumber,
			String phoneMobileNumber) {
		this.id = id;
		this.nameDepartament = nameDepartament;
		this.email = email;
		this.phoneStationaryNumber = phoneStationaryNumber;
		this.phoneMobileNumber = phoneMobileNumber;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getNameDepartament() {
		return nameDepartament;
	}

	public void setNameDepartament(String nameDepartament) {
		this.nameDepartament = nameDepartament;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhoneStationaryNumber() {
		return phoneStationaryNumber;
	}

	public void setPhoneStationaryNumber(String phoneStationaryNumber) {
		this.phoneStationaryNumber = phoneStationaryNumber;
	}

	public String getPhoneMobileNumber() {
		return phoneMobileNumber;
	}

	public void setPhoneMobileNumber(String phoneMobileNumber) {
		this.phoneMobileNumber = phoneMobileNumber;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		DepartamentEntity other = (DepartamentEntity) obj;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		if (id != other.id)
			return false;
		if (nameDepartament == null) {
			if (other.nameDepartament != null)
				return false;
		} else if (!nameDepartament.equals(other.nameDepartament))
			return false;
		if (phoneMobileNumber == null) {
			if (other.phoneMobileNumber != null)
				return false;
		} else if (!phoneMobileNumber.equals(other.phoneMobileNumber))
			return false;
		if (phoneStationaryNumber == null) {
			if (other.phoneStationaryNumber != null)
				return false;
		} else if (!phoneStationaryNumber.equals(other.phoneStationaryNumber))
			return false;
		return true;
	}
	
	
}
