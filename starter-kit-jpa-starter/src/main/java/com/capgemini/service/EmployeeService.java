package com.capgemini.service;

import java.util.List;

import com.capgemini.domain.EmployeeEntity;
import com.capgemini.exceptions.EmployeeEntityDataIntegrityViolationException;

public interface EmployeeService {

	EmployeeEntity saveEmployee(EmployeeEntity employee) throws EmployeeEntityDataIntegrityViolationException;
	
	void deleteEmployee(EmployeeEntity employee) throws EmployeeEntityDataIntegrityViolationException;
	
	EmployeeEntity updateEmployee(EmployeeEntity employee) 
			throws EmployeeEntityDataIntegrityViolationException;
	
	int setEmployeeDepartament(Long idEmployee, Long idDepartament) throws EmployeeEntityDataIntegrityViolationException;
	
	List<EmployeeEntity> findEmployeeByFirstAndLastName(String firstName, String lastName);
	EmployeeEntity findEmployeeById(Long id);
	
	List<EmployeeEntity> findEmployeesByDepartament(Long idDepartament);
}
