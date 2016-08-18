package com.capgemini.exceptions;

public class ProjectEntityExistsException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 48921L;

	public ProjectEntityExistsException() {
		super("Project cannot be saved, because it already exists!");
	}
}
