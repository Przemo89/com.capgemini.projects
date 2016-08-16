package com.capgemini.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class EmployeeEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	
	@Column(name = "first_name", nullable = false, length = 45)
	private String firstName;
	@Column(name = "last_name", nullable = false, length = 45)
	private String lastName;
	@Column(name = "pin", nullable = false, length = 11)
	private String pin;
	@Temporal(TemporalType.DATE)
	@Column(name = "birth_date", nullable = false)
	private Date birthDate;
	@Column(name = "email", nullable = false, length = 45)
	private String email;
	@Column(name = "phone_home", nullable = false, length = 15)
	private String phoneHomeNumber;
	@Column(name = "phone_work", nullable = false, length = 15)
	private String phoneWorkNumber;
	
	
}
