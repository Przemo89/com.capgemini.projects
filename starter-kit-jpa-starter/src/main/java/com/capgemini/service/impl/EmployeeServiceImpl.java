package com.capgemini.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.capgemini.dao.EmployeeDao;
import com.capgemini.domain.EmployeeEntity;
import com.capgemini.exceptions.EmployeeEntityNotExistException;
import com.capgemini.service.EmployeeService;

@Service
@Transactional(readOnly = true)
public class EmployeeServiceImpl implements EmployeeService {

	@Autowired
	private EmployeeDao employeeRepository;
	
	@Override
	@Transactional(readOnly = false)
	public EmployeeEntity saveEmployee(EmployeeEntity employee) {
		return employeeRepository.save(employee);
	}

	@Override
	@Transactional(readOnly = false)
	public void deleteEmployee(EmployeeEntity employee) throws EmployeeEntityNotExistException {
		if (!employeeRepository.exists(employee.getId())) {
			throw new EmployeeEntityNotExistException(employee.getId());
		}
		employeeRepository.deleteEmployee(employee);
	}

	@Override
	@Transactional(readOnly = false)
	public EmployeeEntity updateEmployee(EmployeeEntity employee) throws EmployeeEntityNotExistException {
		if (!employeeRepository.exists(employee.getId())) {
			throw new EmployeeEntityNotExistException(employee.getId());
		}
		return employeeRepository.update(employee);
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
