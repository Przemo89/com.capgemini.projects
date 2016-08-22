package com.capgemini.dao;

import java.util.List;

import com.capgemini.domain.EmployeeEntity;

public interface EmployeeDao extends Dao<EmployeeEntity, Long> {
	
	/**
	 * Sets new departament for specific Employee.
	 * @param idEmployee
	 * @param idDepartament
	 */
	int setEmployeeDepartament(Long idEmployee, Long idDepartament);
	
	List<EmployeeEntity> findEmployeeByFirstAndLastName(String firstName, String lastName);
	
	List<EmployeeEntity> findEmployeesByDepartament(Long idDepartament);
	
	/**DB has constraints, that Id, PIN, Email or Phones' numbers should be unique.
	 * Therefore this method retrieves from database any employee, which has the same PIN, Email 
	 * or Phones' numbers before adding to DB new one. 
	 * Thanks to this, proper exception might be thrown and possible repetitions of listed unique fields 
	 * will be caught in the service layer.
	 * @param employee - the one, which is supposed to be added to DB.
	 * @return list of Employees, which meet criteria. If list is empty, everything is ok.
	 */
	List<EmployeeEntity> findEmployeesWithSameIdPinEmailAndPhones(EmployeeEntity employee);
	
	/**Very similar method in comparison to the @findEmployeesWithSameIdPinEmailAndPhones.
	 * However, this method does not retrieve EmployeeEntity, which has the same id, as provided
	 * EmployeeEntity to be updated. Therefore this method retrieves from database any employee, 
	 * which has the same PIN, Email or Phones' numbers before update of some already existing Employee.
	 * Thanks to this, proper exception might be thrown and possible repetitions of listed unique fields 
	 * will be caught in the service layer.
	 * @param employee - the one, which is supposed to be updated in DB.
	 * @return list of Employees, which meet criteria. If list is empty, everything is ok.
	 */
	List<EmployeeEntity> findEmployeesWithSamePinEmailAndPhones(EmployeeEntity employee);
	
	void deleteEmployee(EmployeeEntity employee);

}
