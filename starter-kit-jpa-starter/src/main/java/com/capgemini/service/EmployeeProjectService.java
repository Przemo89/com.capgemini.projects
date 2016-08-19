package com.capgemini.service;

import java.util.Date;
import java.util.List;

import com.capgemini.domain.EmployeeProjectEntity;
import com.capgemini.exceptions.EmployeeProjectEntityExistsException;
import com.capgemini.exceptions.EmployeeProjectEntityNotExistsException;

public interface EmployeeProjectService {

	int setEmployeeTerminationDateInProject(EmployeeProjectEntity employeeProject, 
			Date terminationDate) throws EmployeeProjectEntityNotExistsException;
	
	EmployeeProjectEntity saveEmployeeProject(EmployeeProjectEntity employeeProject) throws EmployeeProjectEntityExistsException;
	
	EmployeeProjectEntity findEmployeeProjectById(long idEmployeeProject);
	
	List<EmployeeProjectEntity> findEmployeesCurrentlyWorkingInSpecificProject(long idProject);
	
	List<EmployeeProjectEntity> findEmployeesWorkingInSpecificProjectForSpecificTime(long idProject, int numberOfMonths);
}