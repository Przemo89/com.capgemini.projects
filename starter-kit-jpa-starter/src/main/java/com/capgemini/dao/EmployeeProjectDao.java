package com.capgemini.dao;

import java.util.Date;
import java.util.List;

import com.capgemini.domain.EmployeeProjectEntity;

public interface EmployeeProjectDao extends Dao<EmployeeProjectEntity, Long> {

	int setEmployeeTerminationDateInProject(EmployeeProjectEntity employeeProject, Date terminationDate);
	
	List<EmployeeProjectEntity> findEmployeesCurrentlyWorkingInSpecificProject(long idProject);
	
	List<EmployeeProjectEntity> findEmployeesWorkingInSpecificProjectForSpecificTime(long idProject, int numberOfMonths);
}
