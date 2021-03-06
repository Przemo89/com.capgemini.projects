package com.capgemini.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.persistence.EntityManager;
import javax.persistence.OptimisticLockException;

import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import com.capgemini.domain.ContactDetails;
import com.capgemini.domain.DepartamentEntity;
import com.capgemini.domain.EmployeeEntity;
import com.capgemini.exceptions.EmployeeEntityDataIntegrityViolationException;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class EmployeeServiceTest {
	
	@Autowired
	private EmployeeService employeeService;
	
	@Autowired
	private EntityManager entityManager;
	
	@Autowired
	private DepartamentService departamentService;
	
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
    	// then
    	assertEquals(properNumberOfUpdatedEmployees, numberOfUpdatedEmployees);
    }
    
	@Test
	public void testShouldThrowExceptionWhenUpdateIdDepartamentOfEmployee() throws Exception {
		// given
		final long idDepartamentThatExist = 2L;
		final long idEmployeeThatNotExist = 91L;
		
		// when
		thrown.expect(EmployeeEntityDataIntegrityViolationException.class);
		thrown.expectMessage(" does not exist and therefore cannot be updated!");
		
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
    	employeeToBeUpdated.getContactDetails().setEmail(email);
		employeeToBeUpdated.setPin(pin);

		// when
    	EmployeeEntity employeeUpdated = employeeService.updateEmployee(employeeToBeUpdated);
    	
    	// then
    	assertEquals(email, employeeUpdated.getContactDetails().getEmail());
    	assertEquals(pin, employeeUpdated.getPin());
    }
    
	@Test
	public void testShouldThrowExceptionWhenUpdateEmployeeWhoseIdNotInDB() throws Exception {
		// given
		final long idEmployeeThatExist = 10L;
		final long idEmployeeThatNotExist = 79L;
    	final String pin = "33333553333";
    	final String email = "ran23om@randfom.com";
    	final String phoneHomeNumber = "713544779";
    	final String phoneWorkNumber = "772345490";
    	final ContactDetails contactDetails = new ContactDetails();
    	contactDetails.setEmail(email);
    	contactDetails.setPhoneStationaryNumber(phoneHomeNumber);
    	contactDetails.setPhoneMobileNumber(phoneWorkNumber);
		EmployeeEntity employeeExisting = employeeService.findEmployeeById(idEmployeeThatExist);
		entityManager.detach(employeeExisting);
		employeeExisting.setId(idEmployeeThatNotExist);
		employeeExisting.setPin(pin);
		employeeExisting.setContactDetails(contactDetails);
		
		// when
		thrown.expect(EmployeeEntityDataIntegrityViolationException.class);
		thrown.expectMessage(" does not exist and therefore cannot be updated!");
		
		// then
		employeeService.updateEmployee(employeeExisting);
	}
	
	@Test
	public void testShouldThrowExceptionWhenUpdateEmployeeWithSamePin() throws Exception {
		// given
		final long idEmployeeThatExistOne = 10L;
		final long idEmployeeThatExistTwo = 12L;
		EmployeeEntity employeeExistingOne = employeeService.findEmployeeById(idEmployeeThatExistOne);
		entityManager.detach(employeeExistingOne);
		EmployeeEntity employeeExistingTwo = employeeService.findEmployeeById(idEmployeeThatExistTwo);
		entityManager.detach(employeeExistingTwo);
		employeeExistingOne.setPin(employeeExistingTwo.getPin());
		
		// when
		thrown.expect(EmployeeEntityDataIntegrityViolationException.class);
		thrown.expectMessage("Please provide proper PIN or correct existing employee's.");
		
		// then
		employeeService.updateEmployee(employeeExistingOne);
	}
	
	@Test
	public void testShouldThrowExceptionWhenUpdateEmployeeWithSameEmail() throws Exception {
		// given
		final long idEmployeeThatExistOne = 10L;
		final long idEmployeeThatExistTwo = 12L;
		EmployeeEntity employeeExistingOne = employeeService.findEmployeeById(idEmployeeThatExistOne);
		entityManager.detach(employeeExistingOne);
		EmployeeEntity employeeExistingTwo = employeeService.findEmployeeById(idEmployeeThatExistTwo);
		entityManager.detach(employeeExistingTwo);
		ContactDetails contactDetails = employeeExistingOne.getContactDetails();
		contactDetails.setEmail(employeeExistingTwo.getContactDetails().getEmail());
		employeeExistingOne.setContactDetails(contactDetails);
		
		// when
		thrown.expect(EmployeeEntityDataIntegrityViolationException.class);
		thrown.expectMessage("Provided employee's email is the same as employee with id = ");
		
		// then
		employeeService.updateEmployee(employeeExistingOne);
	}
	
	@Test
	public void testShouldThrowExceptionWhenUpdateEmployeeWithSamePhoneStationary() throws Exception {
		// given
		final long idEmployeeThatExistOne = 10L;
		final long idEmployeeThatExistTwo = 12L;
		EmployeeEntity employeeExistingOne = employeeService.findEmployeeById(idEmployeeThatExistOne);
		entityManager.detach(employeeExistingOne);
		EmployeeEntity employeeExistingTwo = employeeService.findEmployeeById(idEmployeeThatExistTwo);
		entityManager.detach(employeeExistingTwo);
		ContactDetails contactDetails = employeeExistingOne.getContactDetails();
		contactDetails.setPhoneStationaryNumber(employeeExistingTwo.getContactDetails().getPhoneStationaryNumber());
		employeeExistingOne.setContactDetails(contactDetails);
		
		// when
		thrown.expect(EmployeeEntityDataIntegrityViolationException.class);
		thrown.expectMessage("Provided employee's stationary phone number is the same as employee with id =");
		
		// then
		employeeService.updateEmployee(employeeExistingOne);
	}
	
	@Test
	public void testShouldThrowExceptionWhenUpdateEmployeeWithSamePhoneMobile() throws Exception {
		// given
		final long idEmployeeThatExistOne = 10L;
		final long idEmployeeThatExistTwo = 12L;
		EmployeeEntity employeeExistingOne = employeeService.findEmployeeById(idEmployeeThatExistOne);
		entityManager.detach(employeeExistingOne);
		EmployeeEntity employeeExistingTwo = employeeService.findEmployeeById(idEmployeeThatExistTwo);
		entityManager.detach(employeeExistingTwo);
		ContactDetails contactDetails = employeeExistingOne.getContactDetails();
		contactDetails.setPhoneMobileNumber(employeeExistingTwo.getContactDetails().getPhoneMobileNumber());
		employeeExistingOne.setContactDetails(contactDetails);
		
		// when
		thrown.expect(EmployeeEntityDataIntegrityViolationException.class);
		thrown.expectMessage("Provided employee's mobile phone number is the same as employee with id = ");
		
		// then
		employeeService.updateEmployee(employeeExistingOne);
	}
    
    @Test
    public void testShouldSaveEmployee() throws Exception {
    	// given
    	final long idDepartament = 3L;
    	DepartamentEntity departament = departamentService.findDepartamentById(idDepartament);
    	final String firstName = "Bob";
    	final String lastName = "Bob2";
    	final String pin = "33333553333";
    	final Date birthDate = new Date(1968-03-30);
    	final String email = "ran23om@randfom.com";
    	final String phoneHomeNumber = "713544779";
    	final String phoneWorkNumber = "772345490";
    	final ContactDetails contactDetails = new ContactDetails();
    	contactDetails.setEmail(email);
    	contactDetails.setPhoneStationaryNumber(phoneHomeNumber);
    	contactDetails.setPhoneMobileNumber(phoneWorkNumber);
    	EmployeeEntity employeeToBeSaved = new EmployeeEntity();
    	employeeToBeSaved.setDepartament(departament);
    	employeeToBeSaved.setFirstName(firstName);
    	employeeToBeSaved.setLastName(lastName);
    	employeeToBeSaved.setPin(pin);
    	employeeToBeSaved.setBirthDate(birthDate);
    	employeeToBeSaved.setContactDetails(contactDetails);
    	
		// when
    	EmployeeEntity employeeSaved = employeeService.saveEmployee(employeeToBeSaved);
    	
    	// then
    	assertEquals(firstName, employeeSaved.getFirstName());
    	assertEquals(pin, employeeSaved.getPin());
    	assertEquals(email, employeeSaved.getContactDetails().getEmail());
    	assertEquals(idDepartament, employeeSaved.getDepartament().getId());
    }
    
    @Test
    public void testShouldThrowExceptionWhenSaveEmployee() throws Exception {
		// given
		final long idEmployeeThatExist = 9L;
		EmployeeEntity employeeExisting = employeeService.findEmployeeById(idEmployeeThatExist);
		
		// when
		thrown.expect(EmployeeEntityDataIntegrityViolationException.class);
		thrown.expectMessage(" already exists and therefore cannot be added!");
		
		// then
		employeeService.saveEmployee(employeeExisting);
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
		final long idEmployeeToBeDeleted = 11L;
		EmployeeEntity employee = employeeService.findEmployeeById(idEmployeeToBeDeleted);
		entityManager.detach(employee);

		// when
		employeeService.deleteEmployee(employee);
		
		// then
		Assert.assertNull(employeeService.findEmployeeById(idEmployeeToBeDeleted));
	}
	
	@Test
	public void testShouldThrowExceptionWhenDeleteEmployeeWhoNotExists() throws Exception {
		// given
		final long idEmployeeThatExist = 14L;
		final long idEmployeeThatNotExist = 78L;
		EmployeeEntity employeeExisting = employeeService.findEmployeeById(idEmployeeThatExist);
		entityManager.detach(employeeExisting);
		employeeExisting.setId(idEmployeeThatNotExist);
		
		// when
		thrown.expect(EmployeeEntityDataIntegrityViolationException.class);
		thrown.expectMessage(" does not exist and therefore cannot be deleted!");
		
		// then
		employeeService.deleteEmployee(employeeExisting);
	}
	
	@Test
	public void testShouldThrowExceptionWhenDeleteEmployeeWhichIsManagingProject() throws Exception {
		// given
		final long idEmployeeThatIsManagingProject = 12L;
		EmployeeEntity employeeThatIsManagingProject = employeeService.findEmployeeById(idEmployeeThatIsManagingProject);
		
		// when
		thrown.expect(EmployeeEntityDataIntegrityViolationException.class);
		thrown.expectMessage("Change project's manager first and then remove this employee.");
		
		// then
		employeeService.deleteEmployee(employeeThatIsManagingProject);
	}
	
	@Test
	public void testShouldThrowOptimisticLockExceptionWhenSimultaneoslyUpdatingSameRecord() throws Exception {
		// given
		final long idEmployeeToBeUpdatedTwice = 5L;
		EmployeeEntity employeeFirstTime = employeeService.findEmployeeById(idEmployeeToBeUpdatedTwice);
		entityManager.detach(employeeFirstTime);
		employeeFirstTime.setFirstName("Ranasfiofh");

		EmployeeEntity employeeSecondTime = employeeService.findEmployeeById(idEmployeeToBeUpdatedTwice);
		entityManager.detach(employeeSecondTime);
		employeeSecondTime.setFirstName("sdfgjkhfdgjkhdfgjk");

		employeeFirstTime = entityManager.merge(employeeFirstTime);
		entityManager.flush();

		// then
		thrown.expect(OptimisticLockException.class);
		
		// when
		entityManager.merge(employeeSecondTime);
	}
	
	@Test
	public void testShouldUpdateLastModificationDateWhenUpdate() throws Exception {
		// given
		final long idEmployeeExisting = 4L;

		EmployeeEntity employeeExisting = employeeService.findEmployeeById(idEmployeeExisting);
		entityManager.detach(employeeExisting);
		
		final String firstName = "Zenonn";
		final String lastName = "Grzybobralski";
    	final String pin = "32343553333";
    	final String email = "ran23om@randfom.com";
    	final String phoneHomeNumber = "713544779";
    	final String phoneWorkNumber = "772345490";
    	final ContactDetails contactDetails = new ContactDetails();
    	contactDetails.setEmail(email);
    	contactDetails.setPhoneStationaryNumber(phoneHomeNumber);
    	contactDetails.setPhoneMobileNumber(phoneWorkNumber);
    	EmployeeEntity employeeToBeSaved = new EmployeeEntity();
    	employeeToBeSaved.setDepartament(employeeExisting.getDepartament());
    	employeeToBeSaved.setFirstName(firstName);
    	employeeToBeSaved.setLastName(lastName);
    	employeeToBeSaved.setPin(pin);
    	employeeToBeSaved.setBirthDate(employeeExisting.getBirthDate());
    	employeeToBeSaved.setContactDetails(contactDetails);
    	
    	EmployeeEntity employeeSaved = employeeService.saveEmployee(employeeToBeSaved);
    	entityManager.flush();
    	entityManager.detach(employeeSaved);
		final String firstNameNew = "CompleteRandom";
    	employeeSaved.setFirstName(firstNameNew);
		
		// when
		TimeUnit.SECONDS.sleep(1);
		EmployeeEntity employeeUpdated = employeeService.updateEmployee(employeeSaved);
		entityManager.flush();
		entityManager.detach(employeeUpdated);
		
		// then
		Assert.assertNotEquals(employeeSaved.getLastModifiedDate(), employeeUpdated.getLastModifiedDate());
	}
	
	@Test
	public void testShouldUpdateVersionWhenUpdate() throws Exception {
		// given
		final long idEmployeeExisting = 4L;

		EmployeeEntity employeeExisting = employeeService.findEmployeeById(idEmployeeExisting);
		entityManager.detach(employeeExisting);
		
		final String firstName = "Zenonnnn";
		final String lastName = "Grzybobralskid";
    	final String pin = "32343558033";
    	final String email = "ran239m@randfom.com";
    	final String phoneHomeNumber = "713544879";
    	final String phoneWorkNumber = "772345790";
    	final ContactDetails contactDetails = new ContactDetails();
    	contactDetails.setEmail(email);
    	contactDetails.setPhoneStationaryNumber(phoneHomeNumber);
    	contactDetails.setPhoneMobileNumber(phoneWorkNumber);
    	EmployeeEntity employeeToBeSaved = new EmployeeEntity();
    	employeeToBeSaved.setDepartament(employeeExisting.getDepartament());
    	employeeToBeSaved.setFirstName(firstName);
    	employeeToBeSaved.setLastName(lastName);
    	employeeToBeSaved.setPin(pin);
    	employeeToBeSaved.setBirthDate(employeeExisting.getBirthDate());
    	employeeToBeSaved.setContactDetails(contactDetails);
    	
    	EmployeeEntity employeeSaved = employeeService.saveEmployee(employeeToBeSaved);
    	entityManager.flush();
    	entityManager.detach(employeeSaved);
		final String firstNameNew = "CompleteRandom34";
    	employeeSaved.setFirstName(firstNameNew);
    	
    	final Integer properVersionNumberOne = 1;
    	final Integer properVersionNumberTwo = 2;
		
		// when
		EmployeeEntity employeeUpdated = employeeService.updateEmployee(employeeSaved);
		entityManager.flush();
		entityManager.detach(employeeUpdated);
		
		// then
		Assert.assertEquals(properVersionNumberOne, employeeSaved.getVersion());
		Assert.assertEquals(properVersionNumberTwo, employeeUpdated.getVersion());
	}
}
