package com.capgemini.domain;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class ContactDetails {

	@Column(name = "email", nullable = false, length = 45, unique = true)
	private String email;
	@Column(name = "phone_stationary", nullable = false, length = 15, unique = true)
	private String phoneStationaryNumber;
	@Column(name = "phone_mobile", nullable = false, length = 15, unique = true)
	private String phoneMobileNumber;

	public ContactDetails() {
		
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
