package com.capgemini.service;

import static org.junit.Assert.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.capgemini.domain.EmployeeEntity;

@RunWith(SpringRunner.class)
@SpringBootTest
public class EmployeeServiceTest {
	
	@Autowired
	private EmployeeService employeeService;
	
//    @Before
//    public void testShouldUpdateIdDepartamentOfEmployee() {
//    	// given
//    	final Long idEmployee = 1L;
//    	final long idDepartament = 2L;
//    	// when
//    	employeeService.setEmployeeDepartament(idEmployee, idDepartament);
//    	// then
//    	
//    }
//    @Before
//    public void init() {
//    	
//    }
	
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
    public void testShouldUpdateIdDepartamentOfEmployee() {
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
    public void testShouldUpdateEmployee() {
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

}
