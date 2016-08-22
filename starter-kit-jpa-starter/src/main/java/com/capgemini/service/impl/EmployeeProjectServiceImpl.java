package com.capgemini.service.impl;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.capgemini.dao.EmployeeProjectDao;
import com.capgemini.domain.EmployeeEntity;
import com.capgemini.domain.EmployeeProjectEntity;
import com.capgemini.exceptions.EmployeeProjectEntityDataIntegrityViolationException;
import com.capgemini.service.EmployeeProjectService;

@Service
@Transactional(readOnly = true)
public class EmployeeProjectServiceImpl implements EmployeeProjectService {

	@Autowired
	private EmployeeProjectDao employeeProjectRepository;
	
	@Override
	@Transactional(readOnly = false)
	public int setEmployeeTerminationDateInProject(EmployeeProjectEntity employeeProject, Date terminationDate)
			throws EmployeeProjectEntityDataIntegrityViolationException {
		if (!employeeProjectRepository.exists(employeeProject.getId())) {
			throw new EmployeeProjectEntityDataIntegrityViolationException("There is no such EmployeeProject entity as provided!");
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
			throws EmployeeProjectEntityDataIntegrityViolationException {
		isHireDateAndSalaryValid(employeeProject);
		isEmployeeProjectAlreadyExists(employeeProject);
		return employeeProjectRepository.save(employeeProject);
	}

	private void isEmployeeProjectAlreadyExists(EmployeeProjectEntity employeeProject)
			throws EmployeeProjectEntityDataIntegrityViolationException {
		if (employeeProjectRepository.exists(employeeProject.getId())) {
			throw new EmployeeProjectEntityDataIntegrityViolationException(
					"Employee participation in specified project with id = "+ employeeProject.getId()
					+ " already exists and therefore cannot be added!");
		}
	}

	private void isHireDateAndSalaryValid(EmployeeProjectEntity employeeProject)
			throws EmployeeProjectEntityDataIntegrityViolationException {
		if (employeeProject.getTerminationDate() != null && employeeProject.getHireDate().after(employeeProject.getTerminationDate())) {
			throw new EmployeeProjectEntityDataIntegrityViolationException("Hire date cannot be before termination date!");
		}
		if (employeeProject.getSalary().compareTo(new BigDecimal(0)) < 0) {
			throw new EmployeeProjectEntityDataIntegrityViolationException("Daily salary cannot be less than 0.");
		}
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

	@Override
	public List<EmployeeEntity> findEmployeesWorkingInSpecificProjectForSpecificTimeInHql(long idProject,
			int numberOfMonths) {
		return employeeProjectRepository.findEmployeesWorkingInSpecificProjectForSpecificTimeInHql(idProject, numberOfMonths);
	}
}
