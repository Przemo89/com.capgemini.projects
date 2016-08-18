package com.capgemini.service;

import java.util.List;

import com.capgemini.domain.EmployeeEntity;
import com.capgemini.exceptions.EmployeeEntityNotExistException;

public interface EmployeeService {

	EmployeeEntity saveEmployee(EmployeeEntity employee);
	
	void deleteEmployee(EmployeeEntity employee) throws EmployeeEntityNotExistException;
	
	EmployeeEntity updateEmployee(EmployeeEntity employee) throws EmployeeEntityNotExistException;
	
	int setEmployeeDepartament(Long idEmployee, Long idDepartament) throws EmployeeEntityNotExistException;
	
	List<EmployeeEntity> findEmployeeByFirstAndLastName(String firstName, String lastName);
	EmployeeEntity findEmployeeById(Long id);
	
	List<EmployeeEntity> findEmployeesByDepartament(Long idDepartament);
}
