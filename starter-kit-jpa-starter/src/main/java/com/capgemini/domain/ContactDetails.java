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

	public String getPhoneHomeNumber() {
		return phoneStationaryNumber;
	}

	public void setPhoneHomeNumber(String phoneStationaryNumber) {
		this.phoneStationaryNumber = phoneStationaryNumber;
	}

	public String getPhoneWorkNumber() {
		return phoneMobileNumber;
	}

	public void setPhoneWorkNumber(String phoneMobileNumber) {
		this.phoneMobileNumber = phoneMobileNumber;
	}
}
