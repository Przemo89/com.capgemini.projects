package com.capgemini.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.capgemini.domain.DepartamentEntity;
import com.capgemini.domain.EmployeeEntity;
import com.capgemini.exceptions.EmployeeEntityNotExistException;

@RunWith(SpringRunner.class)
@SpringBootTest
public class EmployeeServiceTest {
	
	@Autowired
	private EmployeeService employeeService;
	
//	@Before
//	@Sql(scripts = "import.sql")
//	public void init() {
//		
//	}
	
	@Rule
	public ExpectedException thrown = ExpectedException.none();
	
	@Test
	public void testShouldFindEmployeeById() {
		// given
		final long idEmployee = 1L;
		final String first_name = "John";
		final String last_name = "Walker";
		final String pin = "56061256439";
		// when
		EmployeeEntity employee = employeeService.findEmployeeById(idEmployee);
		
		// then
		assertEquals(idEmployee, employee.getId());
		assertEquals(first_name, employee.getFirstName());
		assertEquals(last_name, employee.getLastName());
		assertEquals(pin, employee.getPin());
	}
	
    @Test
    public void testShouldUpdateIdDepartamentOfEmployee() throws Exception {
    	// given
    	final long idEmployee = 1L;
    	final long idDepartament = 2L;
    	final int properNumberOfUpdatedEmployees = 1;
    	// when
    	final int numberOfUpdatedEmployees = employeeService.setEmployeeDepartament(idEmployee, idDepartament);
    	final EmployeeEntity updatedEmployee = employeeService.findEmployeeById(idEmployee);
    	// then
    	assertEquals(properNumberOfUpdatedEmployees, numberOfUpdatedEmployees);
    	assertEquals(idDepartament, updatedEmployee.getDepartament().getId());
    }
    
	@Test
	public void testShouldThrowExceptionWhenUpdateIdDepartamentOfEmployee() throws Exception {
		// given
		final long idDepartamentThatExist = 2L;
		final long idEmployeeThatNotExist = 91L;
		
		// when
		thrown.expect(EmployeeEntityNotExistException.class);
		thrown.expectMessage("Employee with id = " + idEmployeeThatNotExist + " does not exist!");
		
		// then
		employeeService.setEmployeeDepartament(idEmployeeThatNotExist, idDepartamentThatExist);
	}
    

    @Test
    public void testShouldUpdateEmployee() throws Exception {
    	// given
    	final long idEmployee = 3L;
    	final String email = "random@random.com";
    	final String pin = "33333333333";
    	EmployeeEntity employeeToBeUpdated = employeeService.findEmployeeById(idEmployee);
    	employeeToBeUpdated.setEmail(email);
		employeeToBeUpdated.setPin(pin);

		// when
    	EmployeeEntity employeeUpdated = employeeService.updateEmployee(employeeToBeUpdated);
    	
    	// then
    	assertEquals(email, employeeUpdated.getEmail());
    	assertEquals(pin, employeeUpdated.getPin());
    }
    
	@Test
	public void testShouldThrowExceptionWhenUpdateEmployee() throws Exception {
		// given
		final long idEmployeeThatExist = 12L;
		final long idEmployeeThatNotExist = 79L;
		EmployeeEntity employeeExisting = employeeService.findEmployeeById(idEmployeeThatExist);
		employeeExisting.setId(idEmployeeThatNotExist);
		
		// when
		thrown.expect(EmployeeEntityNotExistException.class);
		thrown.expectMessage("Employee with id = " + idEmployeeThatNotExist + " does not exist!");
		
		// then
		employeeService.updateEmployee(employeeExisting);
	}
    
    @Test
    public void testShouldSaveEmployee() {
    	// given
    	final DepartamentEntity departament = new DepartamentEntity();
    	final long idDepartament = 3L;
    	departament.setId(idDepartament);
    	final String firstName = "Bob";
    	final String lastName = "Bob2";
    	final String pin = "33334333333";
    	final Date birthDate = new Date(1968-03-30);
    	final String email = "ran23om@random.com";
    	final String phoneHomeNumber = "713544779";
    	final String phoneWorkNumber = "772345490";
    	EmployeeEntity employeeToBeSaved = new EmployeeEntity(departament, 
    			firstName, lastName, pin, birthDate, email, phoneHomeNumber, phoneWorkNumber);

		// when
    	EmployeeEntity employeeSaved = employeeService.saveEmployee(employeeToBeSaved);
    	
    	// then
    	assertEquals(firstName, employeeSaved.getFirstName());
    	assertEquals(pin, employeeSaved.getPin());
    	assertEquals(email, employeeSaved.getEmail());
    	assertEquals(idDepartament, employeeSaved.getDepartament().getId());
    }
    
	@Test
	public void testShouldFindEmployeeByDepartament() {
		// given
		final long idDepartament = 5L;
		final long employeeOneProperResultId = 9L;
		final long employeeTwoProperResultId = 10L;
		final long employeeThreeProperResultId = 11L;
		// from this departament there are only three employees with IDs: 9, 10, 11 in test DB
		List<EmployeeEntity> properEmployees = new ArrayList<>();
		properEmployees.add(employeeService.findEmployeeById(employeeOneProperResultId));
		properEmployees.add(employeeService.findEmployeeById(employeeTwoProperResultId));
		properEmployees.add(employeeService.findEmployeeById(employeeThreeProperResultId));

		// when
		List<EmployeeEntity> employees = new ArrayList<>();
		employees = employeeService.findEmployeesByDepartament(idDepartament);
		
		// then
		assertEquals(properEmployees.size(), employees.size());
		assertEquals(employeeOneProperResultId, employees.get(0).getId());
		assertEquals(employeeTwoProperResultId, employees.get(1).getId());
		assertEquals(employeeThreeProperResultId, employees.get(2).getId());
	}
	
	@Test
	public void testShouldFindEmployeeByFirstAndLastName() {
		// given
		final String firstName = "john";
		final String lastName = "walker";
		final int properSize = 3;
		
		// when
		List<EmployeeEntity> employees = employeeService.findEmployeeByFirstAndLastName(firstName, lastName);
		
		// then
		assertEquals(properSize, employees.size());
		for (EmployeeEntity e : employees) {
			assertTrue(e.getFirstName().toLowerCase().contains(firstName.toLowerCase()));
			assertTrue(e.getLastName().toLowerCase().contains(lastName.toLowerCase()));
		}
	}
	
	@Test
	public void testDeleteEmployee() throws Exception {
		// given
		final long idEmployeeToBeDeleted = 16;
		EmployeeEntity employee = employeeService.findEmployeeById(idEmployeeToBeDeleted);

		// when
		employeeService.deleteEmployee(employee);
		
		// then
		Assert.assertNull(employeeService.findEmployeeById(idEmployeeToBeDeleted));
	}
	
	@Test
	public void testShouldThrowExceptionWhenDeleteEmployee() throws Exception {
		// given
		final long idEmployeeThatExist = 14L;
		final long idEmployeeThatNotExist = 78L;
		EmployeeEntity employeeExisting = employeeService.findEmployeeById(idEmployeeThatExist);
		employeeExisting.setId(idEmployeeThatNotExist);
		
		// when
		thrown.expect(EmployeeEntityNotExistException.class);
		thrown.expectMessage("Employee with id = " + idEmployeeThatNotExist + " does not exist!");
		
		// then
		employeeService.deleteEmployee(employeeExisting);
	}

}
