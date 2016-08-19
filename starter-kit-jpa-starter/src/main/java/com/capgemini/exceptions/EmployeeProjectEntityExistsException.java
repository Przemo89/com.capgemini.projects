package com.capgemini.exceptions;

public class EmployeeProjectEntityExistsException extends Exception {

	private static final long serialVersionUID = 23189741L;

	public EmployeeProjectEntityExistsException() {
		super("Such employee worked in project in specified time and had the same function!");
	}
}
