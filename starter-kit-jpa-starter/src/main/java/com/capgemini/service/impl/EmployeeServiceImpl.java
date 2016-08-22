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
	public EmployeeEntity saveEmployee(EmployeeEntity employee) throws EmployeeEntityDataIntegrityViolationException {
		List<EmployeeEntity> employeesWithSameFields = employeeRepository.findEmployeesWithSameIdPinEmailAndPhones(employee);
		isEmployeeToBeSavedViolatesConstraints(employeesWithSameFields, employee);
		return employeeRepository.save(employee);
	}
	
	private void isEmployeeToBeSavedViolatesConstraints(List<EmployeeEntity> employeesWithSameFields, EmployeeEntity employeeToBeChecked) 
			throws EmployeeEntityDataIntegrityViolationException {
		
		if (employeesWithSameFields.isEmpty()) {
			return;
		}
		for (EmployeeEntity employee : employeesWithSameFields) {
			if (employee.getId() == employeeToBeChecked.getId()) {
				throw new EmployeeEntityDataIntegrityViolationException("Employee with id = " + employeeToBeChecked.getId() + 
						" already exists and therefore cannot be added!");
			}
			isEmployeePinOrContactDetailsRepeated(employeeToBeChecked, employee);
		}
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
			throws EmployeeEntityDataIntegrityViolationException {
		List<EmployeeEntity> employeesWithSameFields = employeeRepository.findEmployeesWithSameIdPinEmailAndPhones(employee);
		isEmployeeToBeUpdatedViolatesConstraints(employeesWithSameFields, employee);
		return employeeRepository.update(employee);
	}
	
	private void isEmployeeToBeUpdatedViolatesConstraints(List<EmployeeEntity> employeesWithSameFields, EmployeeEntity employeeToBeChecked) 
			throws EmployeeEntityDataIntegrityViolationException {
		boolean isEmployeeEntityExists = false;
		if (employeesWithSameFields.isEmpty()) {
			throw new EmployeeEntityDataIntegrityViolationException("Employee with id = " + employeeToBeChecked.getId() + 
					" does not exist and therefore cannot be updated!");
		}
		for (EmployeeEntity employee : employeesWithSameFields) {
			if (employee.getId() != employeeToBeChecked.getId()) {
				isEmployeePinOrContactDetailsRepeated(employeeToBeChecked, employee);
			} else {
				isEmployeeEntityExists = true;
			}
		}
		if (isEmployeeEntityExists == false) {
			throw new EmployeeEntityDataIntegrityViolationException("Employee with id = " + employeeToBeChecked.getId() + 
					" does not exist and therefore cannot be updated!");
		}
	}

	private void isEmployeePinOrContactDetailsRepeated(EmployeeEntity employeeToBeChecked, EmployeeEntity employee)
			throws EmployeeEntityDataIntegrityViolationException {
		if (employee.getPin().equals(employeeToBeChecked.getPin())) {
			throw new EmployeeEntityDataIntegrityViolationException(
					"Provided employee's PIN is the same as employee with id = " + employee.getId() 
					+ "! Please provide proper PIN or correct existing employee's.");
		}
		if (employee.getContactDetails().getEmail()
				.equals(employeeToBeChecked.getContactDetails().getEmail())) {
			throw new EmployeeEntityDataIntegrityViolationException(
					"Provided employee's email is the same as employee with id = " + employee.getId() 
					+ "! Please provide proper email or correct existing employee's.");
		}
		if (employee.getContactDetails().getPhoneMobileNumber()
				.equals(employeeToBeChecked.getContactDetails().getPhoneMobileNumber())) {
			throw new EmployeeEntityDataIntegrityViolationException(
					"Provided employee's mobile phone number is the same as employee with id = " + employee.getId() 
					+ "! Please provide proper mobile phone number or correct existing employee's.");
		}
		if (employee.getContactDetails().getPhoneStationaryNumber()
				.equals(employeeToBeChecked.getContactDetails().getPhoneStationaryNumber())) {
			throw new EmployeeEntityDataIntegrityViolationException(
					"Provided employee's stationary phone number is the same as employee with id = " + employee.getId() 
					+ "! Please provide proper stationary phone number or correct existing employee's.");
		}
	}

	@Override
	@Transactional(readOnly = false)
	public int setEmployeeDepartament(Long idEmployee, Long idDepartament) throws EmployeeEntityDataIntegrityViolationException {
		if (!employeeRepository.exists(idEmployee)) {
			throw new EmployeeEntityDataIntegrityViolationException("Employee with id = " + idEmployee + 
					" does not exist and therefore cannot be updated!");
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
