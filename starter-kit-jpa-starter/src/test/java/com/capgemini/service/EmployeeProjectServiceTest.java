package com.capgemini.service;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.capgemini.domain.EmployeeEntity;
import com.capgemini.domain.EmployeeProjectEntity;
import com.capgemini.enumerations.EmployeeFunction;
import com.capgemini.exceptions.EmployeeProjectEntityDataIntegrityViolationException;


@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class EmployeeProjectServiceTest {

	@Autowired
	private EmployeeProjectService employeeProjectService;
	
	@Autowired
	private EntityManager entityManager;
	
	@Autowired
	private EmployeeService employeeService;
	
	@Autowired
	private ProjectService projectService;
	
	@Rule
	public ExpectedException thrown = ExpectedException.none();
	
	@Test
	public void testFindEmployeeProjectById() {
		// given
		final long idEmployeeProjectExisting = 4L;
		
		// when
		EmployeeProjectEntity employeeProject = employeeProjectService.findEmployeeProjectById(idEmployeeProjectExisting);
		
		// then
		Assert.assertNotNull(employeeProject);
	}
	
	@Test
	public void testShouldReturnNullWhenFindEmployeeProjectById() {
		// given
		final long idEmployeeProjectExisting = 345678345L;
		
		// when
		EmployeeProjectEntity employeeProject = employeeProjectService.findEmployeeProjectById(idEmployeeProjectExisting);
		
		// then
		Assert.assertNull(employeeProject);
	}
	
	@Test
	public void testSetTerminationDate() throws Exception {
		// given
		final long idEmployeeProjectExisting = 5L;
		EmployeeProjectEntity employeeProject = employeeProjectService.findEmployeeProjectById(idEmployeeProjectExisting);
		final Date terminationDate = new Date();
		final int properResult = 1;
		// when
		int numberOfUpdatedEmployeeProject = 
				employeeProjectService.setEmployeeTerminationDateInProject(employeeProject, terminationDate);
		
		// then
		Assert.assertEquals(properResult, numberOfUpdatedEmployeeProject);
	}
	
	@Test
	public void testFindEmployeesCurrentlyWorkingInSpecificProject() throws Exception {
		// given
		final long idProjectExisting = 3L;
		int properResultListSize = 2;
		final long idEmployeeOne = 8L;
		final long idEmployeeTwo = 13L;
		
		// when
		List<EmployeeEntity> resultList = 
				employeeProjectService.findEmployeesCurrentlyWorkingInSpecificProject(idProjectExisting);
		
		// then
		Assert.assertEquals(properResultListSize, resultList.size());
		Assert.assertEquals(idEmployeeOne, resultList.get(0).getId());
		Assert.assertEquals(idEmployeeTwo, resultList.get(1).getId());
	}
	
	@Test
	public void testFindEmployeesWorkingInSpecificProjectForSpecificNumberMonths() throws Exception {
		// given
		final long idProjectExisting = 3L;
		final int numberOfMonths = 8;
		int properResultListSize = 4;
		final long idEmployeeOne = 7L;
		final long idEmployeeTwo = 8L;
		final long idEmployeeThree = 9L;
		final long idEmployeeFour = 13L;
		
		// when
		List<EmployeeEntity> resultList = employeeProjectService.
				findEmployeesWorkingInSpecificProjectForSpecificTime(idProjectExisting, numberOfMonths);
		
		// then
		Assert.assertEquals(properResultListSize, resultList.size());
		Assert.assertEquals(idEmployeeOne, resultList.get(0).getId());
		Assert.assertEquals(idEmployeeTwo, resultList.get(1).getId());
		Assert.assertEquals(idEmployeeThree, resultList.get(2).getId());
		Assert.assertEquals(idEmployeeFour, resultList.get(3).getId());
	}
	
	@Test
	public void testFindEmployeesWorkingInSpecificProjectForSpecificNumberMonthsInHql() throws Exception {
		// given
		final long idProjectExisting = 3L;
		final int numberOfMonths = 8;
		int properResultListSize = 4;
		final long idEmployeeOne = 7L;
		final long idEmployeeTwo = 8L;
		final long idEmployeeThree = 9L;
		final long idEmployeeFour = 13L;
		
		// when
		List<EmployeeEntity> resultList = employeeProjectService.
				findEmployeesWorkingInSpecificProjectForSpecificTimeInHql(idProjectExisting, numberOfMonths);
		
		// then
		Assert.assertEquals(properResultListSize, resultList.size());
		Assert.assertEquals(idEmployeeOne, resultList.get(0).getId());
		Assert.assertEquals(idEmployeeTwo, resultList.get(1).getId());
		Assert.assertEquals(idEmployeeThree, resultList.get(2).getId());
		Assert.assertEquals(idEmployeeFour, resultList.get(3).getId());
	}
	
	@Test
	public void testShouldSaveEmployeeProject() throws Exception {
		// given
		final long idEmployeeProject = 20L;
		final long idEmployeeProjectExisting = 9L;
		final long idEmployeeProjectNotExisting = 90L;
		final BigDecimal salaryValid = new BigDecimal(20);
		DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd"); 
		Date hireDateNew = (Date) formatter.parse("2016-08-10"); 

		EmployeeProjectEntity employeeProjectToBeSaved = new EmployeeProjectEntity();
		employeeProjectToBeSaved.setHireDate(hireDateNew);
		employeeProjectToBeSaved.setTerminationDate(null);
		employeeProjectToBeSaved.setEmployeeFunction(EmployeeFunction.FCD);
		employeeProjectToBeSaved.setEmployee(employeeService.findEmployeeById(1L));
		employeeProjectToBeSaved.setProject(projectService.findProjectById(2L));
		employeeProjectToBeSaved.setSalary(salaryValid);
		
		// then
//		thrown.expect(EmployeeProjectEntityDataIntegrityViolationException.class);
//		thrown.expectMessage(" within provided time frame and therefore cannot be added!");
		
		// when
		EmployeeProjectEntity employeeProjectSaved = employeeProjectService.saveEmployeeProject(employeeProjectToBeSaved);
	}
	
	@Test
	public void testShouldThrowExceptionWhenSaveEmployeeProjectWhichAlreadyExistsInDB() throws Exception {
		// given
		final long idEmployeeProject = 9L;
		EmployeeProjectEntity employeeProjectToBeSaved = employeeProjectService.findEmployeeProjectById(idEmployeeProject);
		entityManager.detach(employeeProjectToBeSaved);
		employeeProjectToBeSaved = entityManager.merge(employeeProjectToBeSaved);
		
		// then
		thrown.expect(EmployeeProjectEntityDataIntegrityViolationException.class);
		thrown.expectMessage(" already exists and therefore cannot be added!");
		
		// when
		employeeProjectService.saveEmployeeProject(employeeProjectToBeSaved);
	}
	
	@Test
	public void testShouldThrowExceptionWhenSaveEmployeeProjectWhichSalaryIsLessThanZero() throws Exception {
		// given
		final long idEmployeeProject = 9L;
		final BigDecimal salaryInvalid = new BigDecimal(-20);
		EmployeeProjectEntity employeeProjectToBeSaved = employeeProjectService.findEmployeeProjectById(idEmployeeProject);
		entityManager.detach(employeeProjectToBeSaved);
		employeeProjectToBeSaved.setSalary(salaryInvalid);
		
		// then
		thrown.expect(EmployeeProjectEntityDataIntegrityViolationException.class);
		thrown.expectMessage("Daily salary cannot be less than 0.");
		
		// when
		employeeProjectService.saveEmployeeProject(employeeProjectToBeSaved);
	}
	
}
