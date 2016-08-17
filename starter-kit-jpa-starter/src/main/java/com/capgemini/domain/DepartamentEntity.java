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

	protected DepartamentEntity() {
		
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
}
