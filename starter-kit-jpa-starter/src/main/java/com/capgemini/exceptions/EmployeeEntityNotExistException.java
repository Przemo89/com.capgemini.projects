package com.capgemini.exceptions;

public class EmployeeEntityNotExistException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3478654L;

	public EmployeeEntityNotExistException(long idEmployee) {
		super("Employee with id = " + idEmployee + " does not exist!");
	}
}
