package com.capgemini.listeners;

import javax.persistence.PreUpdate;

import com.capgemini.domain.EmployeeEntity;

public class EmployeeEntityListener {
	
	@PreUpdate
	public void onCreate(EmployeeEntity ee){
		System.out.println("AAAAAAAAAAAAAAAAA "+ee.getEmail());
	}

}
