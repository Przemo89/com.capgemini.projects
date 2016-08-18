package com.capgemini.exceptions;

public class EmployeeEntityExistsException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5478752L;
	
	public EmployeeEntityExistsException() {
		super("Employee entity cannot be saved, because it already exists!");
	}
}
