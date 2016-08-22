package com.capgemini.service;

import java.util.List;

import com.capgemini.domain.EmployeeEntity;
import com.capgemini.exceptions.EmployeeEntityDataIntegrityViolationException;
import com.capgemini.exceptions.EmployeeEntityExistsException;
import com.capgemini.exceptions.EmployeeEntityIsManagerOfProjectException;
import com.capgemini.exceptions.EmployeeEntityNotExistException;

public interface EmployeeService {

	EmployeeEntity saveEmployee(EmployeeEntity employee) throws EmployeeEntityDataIntegrityViolationException;
	
	void deleteEmployee(EmployeeEntity employee) throws EmployeeEntityNotExistException, EmployeeEntityIsManagerOfProjectException;
	
	EmployeeEntity updateEmployee(EmployeeEntity employee) 
			throws EmployeeEntityDataIntegrityViolationException;
	
	int setEmployeeDepartament(Long idEmployee, Long idDepartament) throws EmployeeEntityDataIntegrityViolationException;
	
	List<EmployeeEntity> findEmployeeByFirstAndLastName(String firstName, String lastName);
	EmployeeEntity findEmployeeById(Long id);
	
	List<EmployeeEntity> findEmployeesByDepartament(Long idDepartament);
}
