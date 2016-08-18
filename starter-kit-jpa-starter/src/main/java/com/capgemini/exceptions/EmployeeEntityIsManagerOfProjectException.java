package com.capgemini.exceptions;

public class EmployeeEntityIsManagerOfProjectException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2341L;

	public EmployeeEntityIsManagerOfProjectException(long idEmployee) {
		super("Employee with id = " + idEmployee + " cannot be removed, "
				+ "because is a manager of projects. Change project's manager and then try again.");
	}
	
}
