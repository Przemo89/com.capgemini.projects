package com.capgemini.dao;

import java.util.Date;
import java.util.List;

import com.capgemini.domain.EmployeeEntity;
import com.capgemini.domain.EmployeeProjectEntity;

public interface EmployeeProjectDao extends Dao<EmployeeProjectEntity, Long> {

	int setEmployeeTerminationDateInProject(EmployeeProjectEntity employeeProject, Date terminationDate);
	
	List<EmployeeEntity> findEmployeesCurrentlyWorkingInSpecificProject(long idProject);
	
	List<EmployeeEntity> findEmployeesWorkingInSpecificProjectForSpecificTime(long idProject, int numberOfMonths);
	
	List<EmployeeEntity> findEmployeesWorkingInSpecificProjectForSpecificTimeInHql(long idProject, int numberOfMonths);
}
