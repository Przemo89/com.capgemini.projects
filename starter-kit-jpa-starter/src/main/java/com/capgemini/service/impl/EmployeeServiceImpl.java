package com.capgemini.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.capgemini.dao.EmployeeDao;
import com.capgemini.domain.EmployeeEntity;
import com.capgemini.service.EmployeeService;

@Service
@Transactional(readOnly=true)
public class EmployeeServiceImpl implements EmployeeService {

	@Autowired
	private EmployeeDao employeeRepository;
	
	@Override
	public EmployeeEntity saveEmployee(EmployeeEntity employee) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteEmployee(Long idEmployee) {
		// TODO Auto-generated method stub
		
	}

	@Override
	@Transactional(readOnly = false)
	public EmployeeEntity updateEmployee(EmployeeEntity employee) {
		// TODO Auto-generated method stub
		return employeeRepository.update(employee);
	}

	@Override
	@Transactional(readOnly = false)
	public int setEmployeeDepartament(Long idEmployee, Long idDepartament) {
		return employeeRepository.setEmployeeDepartament(idEmployee, idDepartament);
	}

	@Override
	public EmployeeEntity findEmployeeByFirstAndLastName(String firstName, String lastName) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<EmployeeEntity> findEmployeesByDepartament(Long idDepartament) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public EmployeeEntity findEmployeeById(Long id) {
		return employeeRepository.findOne(id);
	}
}
