package com.capgemini.exceptions;

public class EmployeeProjectEntityNotExistsException extends Exception {

	private static final long serialVersionUID = 5478128L;

	public EmployeeProjectEntityNotExistsException() {
		super("There is no such EmployeeProject entity as provided!");
	}
}
