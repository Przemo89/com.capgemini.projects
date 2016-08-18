package com.capgemini.exceptions;

public class ProjectEntityNotExistException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3482689723L;
	
	public ProjectEntityNotExistException(long idProject) {
		super("Project with id = " + idProject + " does not exist!");
	}

	
}
