package com.capgemini.service;

import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.capgemini.domain.EmployeeProjectEntity;


@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class EmployeeProjectServiceTest {

	@Autowired
	private EmployeeProjectService employeeProjectService;
	
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
		List<EmployeeProjectEntity> resultList = employeeProjectService.findEmployeesCurrentlyWorkingInSpecificProject(idProjectExisting);
		
		// then
		Assert.assertEquals(properResultListSize, resultList.size());
		Assert.assertEquals(idEmployeeOne, resultList.get(0).getEmployee().getId());
		Assert.assertEquals(idEmployeeTwo, resultList.get(1).getEmployee().getId());
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
		List<EmployeeProjectEntity> resultList = employeeProjectService.
				findEmployeesWorkingInSpecificProjectForSpecificTime(idProjectExisting, numberOfMonths);;
		
		// then
		Assert.assertEquals(properResultListSize, resultList.size());
		Assert.assertEquals(idEmployeeOne, resultList.get(0).getEmployee().getId());
		Assert.assertEquals(idEmployeeTwo, resultList.get(1).getEmployee().getId());
		Assert.assertEquals(idEmployeeThree, resultList.get(2).getEmployee().getId());
		Assert.assertEquals(idEmployeeFour, resultList.get(3).getEmployee().getId());
	}
}
