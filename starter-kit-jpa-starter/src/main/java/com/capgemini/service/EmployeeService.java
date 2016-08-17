package com.capgemini.service;

import java.util.List;

import com.capgemini.domain.EmployeeEntity;

public interface EmployeeService {

	EmployeeEntity saveEmployee(EmployeeEntity employee);
	
	void deleteEmployee(Long idEmployee);
	
	EmployeeEntity updateEmployee(EmployeeEntity employee);
	
	int setEmployeeDepartament(Long idEmployee, Long idDepartament);
	
	EmployeeEntity findEmployeeByFirstAndLastName(String firstName, String lastName);
	EmployeeEntity findEmployeeById(Long id);
	
	List<EmployeeEntity> findEmployeesByDepartament(Long idDepartament);
}
