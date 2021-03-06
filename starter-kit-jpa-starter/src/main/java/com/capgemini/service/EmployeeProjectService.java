package com.capgemini.service;

import java.util.Date;
import java.util.List;

import com.capgemini.domain.EmployeeEntity;
import com.capgemini.domain.EmployeeProjectEntity;
import com.capgemini.exceptions.EmployeeProjectEntityDataIntegrityViolationException;

public interface EmployeeProjectService {

	int setEmployeeTerminationDateInProject(EmployeeProjectEntity employeeProject, 
			Date terminationDate) throws EmployeeProjectEntityDataIntegrityViolationException;
	
	EmployeeProjectEntity saveEmployeeProject(EmployeeProjectEntity employeeProject) 
			throws EmployeeProjectEntityDataIntegrityViolationException;
	
	EmployeeProjectEntity findEmployeeProjectById(long idEmployeeProject);
	
	List<EmployeeEntity> findEmployeesCurrentlyWorkingInSpecificProject(long idProject);
	
	List<EmployeeEntity> findEmployeesWorkingInSpecificProjectForSpecificTime(long idProject, int numberOfMonths);
	
	List<EmployeeEntity> findEmployeesWorkingInSpecificProjectForSpecificTimeInHql(long idProject, int numberOfMonths);
}
