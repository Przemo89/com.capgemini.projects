package com.capgemini.exceptions;

public class EmployeeEntityDataIntegrityViolationException extends Exception {

	private static final long serialVersionUID = -32590561L;

	public EmployeeEntityDataIntegrityViolationException(String message) {
		super(message);
	}
}
