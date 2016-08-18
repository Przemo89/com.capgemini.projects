package com.capgemini.service;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.capgemini.domain.ProjectEntity;

import org.junit.Assert;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ProjectServiceTest {

	@Autowired
	private ProjectService projectService;
	
	@Rule
	public ExpectedException thrown = ExpectedException.none();
	
	
	@Test
	public void testDeleteProject() throws Exception {
		// given
		final long idProjectToBeDeleted = 5L;
		ProjectEntity projectToBeDeleted = projectService.findProjectById(idProjectToBeDeleted);
		
		// when
		projectService.deleteProject(projectToBeDeleted);
		
		// then
		Assert.assertNull(projectService.findProjectById(idProjectToBeDeleted));
	}
//	@Test
//	public void testDeleteEmployee() throws Exception {
//		// given
//		final long idEmployeeToBeDeleted = 16;
//		EmployeeEntity employee = employeeService.findEmployeeById(idEmployeeToBeDeleted);
//
//		// when
//		employeeService.deleteEmployee(employee);
//		
//		// then
//		Assert.assertNull(employeeService.findEmployeeById(idEmployeeToBeDeleted));
//	}
//	
//	@Test
//	public void testShouldThrowExceptionWhenDeleteEmployee() throws Exception {
//		// given
//		final long idEmployeeThatExist = 14L;
//		final long idEmployeeThatNotExist = 78L;
//		EmployeeEntity employeeExisting = employeeService.findEmployeeById(idEmployeeThatExist);
//		employeeExisting.setId(idEmployeeThatNotExist);
//		
//		// when
//		thrown.expect(EmployeeEntityNotExistException.class);
//		thrown.expectMessage("Employee with id = " + idEmployeeThatNotExist + " does not exist!");
//		
//		// then
//		employeeService.deleteEmployee(employeeExisting);
//	}
}
