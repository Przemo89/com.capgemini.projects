package com.capgemini.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.capgemini.dao.EmployeeProjectDao;
import com.capgemini.domain.EmployeeEntity;
import com.capgemini.domain.EmployeeProjectEntity;
import com.capgemini.exceptions.EmployeeProjectEntityExistsException;
import com.capgemini.exceptions.EmployeeProjectEntityNotExistsException;
import com.capgemini.service.EmployeeProjectService;

@Service
@Transactional(readOnly = true)
public class EmployeeProjectServiceImpl implements EmployeeProjectService {

	@Autowired
	private EmployeeProjectDao employeeProjectRepository;
	
	@Override
	@Transactional(readOnly = false)
	public int setEmployeeTerminationDateInProject(EmployeeProjectEntity employeeProject, Date terminationDate)
			throws EmployeeProjectEntityNotExistsException {
		if (!employeeProjectRepository.exists(employeeProject.getId())) {
			throw new EmployeeProjectEntityNotExistsException();
		}
		return employeeProjectRepository.setEmployeeTerminationDateInProject(employeeProject, terminationDate);
	}
	
	@Override
	public EmployeeProjectEntity findEmployeeProjectById(long idEmployeeProject) {
		if (employeeProjectRepository.exists(idEmployeeProject)) {
			return employeeProjectRepository.findOne(idEmployeeProject);
		}
		return null;
	}
	
	@Override
	public EmployeeProjectEntity saveEmployeeProject(EmployeeProjectEntity employeeProject)
			throws EmployeeProjectEntityExistsException {
		// TODO Auto-generated method stub
		if (employeeProjectRepository.exists(employeeProject.getId())) {
			throw new EmployeeProjectEntityExistsException();
		}
		return null;
	}

	@Override
	public List<EmployeeEntity> findEmployeesCurrentlyWorkingInSpecificProject(long idProject) {
		return employeeProjectRepository.findEmployeesCurrentlyWorkingInSpecificProject(idProject);
	}

	@Override
	public List<EmployeeEntity> findEmployeesWorkingInSpecificProjectForSpecificTime(long idProject,
			int numberOfMonths) {
		return employeeProjectRepository.findEmployeesWorkingInSpecificProjectForSpecificTime(idProject, numberOfMonths);
	}

	
}
