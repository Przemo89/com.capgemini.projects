package com.capgemini.dao;

import java.util.List;

import com.capgemini.domain.EmployeeEntity;

public interface EmployeeDao extends Dao<EmployeeEntity, Long> {
	
	/**
	 * Sets new departament for specific Employee.
	 * @param idEmployee
	 * @param idDepartament
	 */
	int setEmployeeDepartament(Long idEmployee, Long idDepartament);
	
	List<EmployeeEntity> findEmployeeByFirstAndLastName(String firstName, String lastName);
	
	List<EmployeeEntity> findEmployeesByDepartament(Long idDepartament);
	
//	void deleteEmployee(EmployeeEntity employee);

}
