package com.capgemini.domain;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class ContactDetails {

	@Column(name = "email", nullable = false, length = 45, unique = true)
	private String email;
	@Column(name = "phone_stationary", nullable = false, length = 15, unique = true)
	private String phoneHomeNumber;
	@Column(name = "phone_mobile", nullable = false, length = 15, unique = true)
	private String phoneWorkNumber;

	public ContactDetails() {
		
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
}
