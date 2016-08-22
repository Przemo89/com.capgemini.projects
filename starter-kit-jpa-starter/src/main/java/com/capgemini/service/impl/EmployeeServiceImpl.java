package com.capgemini.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.capgemini.dao.EmployeeDao;
import com.capgemini.dao.ProjectDao;
import com.capgemini.domain.EmployeeEntity;
import com.capgemini.domain.ProjectEntity;
import com.capgemini.exceptions.EmployeeEntityDataIntegrityViolationException;
import com.capgemini.exceptions.EmployeeEntityExistsException;
import com.capgemini.exceptions.EmployeeEntityIsManagerOfProjectException;
import com.capgemini.exceptions.EmployeeEntityNotExistException;
import com.capgemini.service.EmployeeService;

@Service
@Transactional(readOnly = true)
public class EmployeeServiceImpl implements EmployeeService {

	@Autowired
	private EmployeeDao employeeRepository;
	
	@Autowired
	private ProjectDao projectRepository;
	
	@Override
	@Transactional(readOnly = false)
	public EmployeeEntity saveEmployee(EmployeeEntity employee) throws EmployeeEntityExistsException {
		if (employeeRepository.exists(employee.getId())) {
			throw new EmployeeEntityExistsException();
		}
		EmployeeEntity employeeSaved = employeeRepository.save(employee);
		return employeeSaved;
	}

	@Override
	@Transactional(readOnly = false)
	public void deleteEmployee(EmployeeEntity employee) throws EmployeeEntityNotExistException, EmployeeEntityIsManagerOfProjectException {
		if (!employeeRepository.exists(employee.getId())) {
			throw new EmployeeEntityNotExistException(employee.getId());
		}
		List<ProjectEntity> projectsManagedByEmployee = projectRepository.findProjectsByIdManager(employee.getId());
		if (!projectsManagedByEmployee.isEmpty()) {
			throw new EmployeeEntityIsManagerOfProjectException(employee.getId());
		}
		employeeRepository.deleteEmployee(employee);
	}

	@Override
	@Transactional(readOnly = false)
	public EmployeeEntity updateEmployee(EmployeeEntity employee) 
			throws EmployeeEntityNotExistException, EmployeeEntityDataIntegrityViolationException {
//		if (employeeRepository.findOne(employee.getId()) == null) {
//			throw new EmployeeEntityNotExistException(employee.getId());
//		}
//		if (employee.get)
		
		List<EmployeeEntity> employeesWithSameFields = employeeRepository.findEmployeesWithSameIdPinEmailAndPhones(employee);
		EmployeeEntity employeeUpdated = new EmployeeEntity();
		if (employeesWithSameFields.isEmpty()) {
			throw new EmployeeEntityDataIntegrityViolationException("Employee with id = " + employee.getId() + 
					" does not exist and therefore cannot be updated!");
//			isEmployeeToBeSavedOrUpdatedViolatesConstraints(employeesWithSameFields, employee);
		}
		try {
			employeeUpdated = employeeRepository.update(employee);
		} catch (Exception e) {
			//TODO find this exception which might be throwed and throw yours
		}
		return employeeUpdated;
	}
	
	private void isEmployeeToBeSavedOrUpdatedViolatesConstraints(List<EmployeeEntity> employeesWithSameFields, EmployeeEntity employeeToBeChecked) 
			throws EmployeeEntityNotExistException, EmployeeEntityDataIntegrityViolationException{
		for (EmployeeEntity employee : employeesWithSameFields) {
			if (employee.getId() == employeeToBeChecked.getId()) {
//				throw new EmployeeEntityDataIntegrityViolationException(message)
			}
		}
	}
	
	

	@Override
	@Transactional(readOnly = false)
	public int setEmployeeDepartament(Long idEmployee, Long idDepartament) throws EmployeeEntityNotExistException {
		if (!employeeRepository.exists(idEmployee)) {
			throw new EmployeeEntityNotExistException(idEmployee);
		}
		return employeeRepository.setEmployeeDepartament(idEmployee, idDepartament);
	}

	@Override
	public List<EmployeeEntity> findEmployeeByFirstAndLastName(String firstName, String lastName) {
		return employeeRepository.findEmployeeByFirstAndLastName(firstName, lastName);
	}

	@Override
	public List<EmployeeEntity> findEmployeesByDepartament(Long idDepartament) {
		return employeeRepository.findEmployeesByDepartament(idDepartament);
	}

	@Override
	public EmployeeEntity findEmployeeById(Long idEmployee) {
		if (employeeRepository.exists(idEmployee)) {
			return employeeRepository.findOne(idEmployee);
		}
		return null;
	}
}
