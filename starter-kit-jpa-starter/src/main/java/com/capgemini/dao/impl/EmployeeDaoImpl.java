package com.capgemini.dao.impl;

import java.util.List;

import javax.persistence.Query;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

import com.capgemini.dao.EmployeeDao;
import com.capgemini.domain.EmployeeEntity;

@Repository
public class EmployeeDaoImpl extends AbstractDao<EmployeeEntity, Long> implements EmployeeDao {

	/**DB has constraints, that Id, PIN, Email or Phones' numbers should be unique.
	 * Therefore this method retrieves from database any employee, which has the same PIN, Email 
	 * or Phones' numbers before adding to DB new one. 
	 * Thanks to this, proper exception might be thrown and possible repetitions of listed unique fields 
	 * will be caught in the service layer.
	 * @param employee - the one, which is supposed to be added to DB.
	 * @return list of Employees, which meet criteria. If list is empty, everything is ok.
	 */
	@Override
	public List<EmployeeEntity> findEmployeesWithSameIdPinEmailAndPhones(EmployeeEntity employee) {
		TypedQuery<EmployeeEntity> query = entityManager.createQuery( 
				"select e from EmployeeEntity e "
				+ "where id = :id OR pin = :pin OR email = :email "
				+ "OR phone_stationary = :phone_stationary OR phone_mobile = :phone_mobile", EmployeeEntity.class);
		query.setParameter("id", employee.getId());
		query.setParameter("pin", employee.getPin());
		query.setParameter("email", employee.getContactDetails().getEmail());
		query.setParameter("phone_stationary", employee.getContactDetails().getPhoneStationaryNumber());
		query.setParameter("phone_mobile", employee.getContactDetails().getPhoneMobileNumber());
		return query.getResultList();
	}
	
	@Override
	public List<EmployeeEntity> findEmployeeManagerOfDepartamentAndEmployeeById(long idEmployee) {
		// TODO Auto-generated method stub
		TypedQuery<EmployeeEntity> query = entityManager.createQuery(
				"select e from EmployeeEntity e "
				+ " left join e.project p "
				+ "where e.id = :id or id_manager = :id", EmployeeEntity.class);
		query.setParameter("id", idEmployee);
		return query.getResultList();
	}

	@Override
	public void deleteEmployee(EmployeeEntity employee) {
		employee = entityManager.merge(employee);
		entityManager.remove(employee);
	}

	@Override
	public int setEmployeeDepartament(Long idEmployee, Long idDepartament) {
		Query query = entityManager.createQuery(
				"update EmployeeEntity set id_departament = :id_departament where id = :id_employee");
		query.setParameter("id_departament", idDepartament);
		query.setParameter("id_employee", idEmployee);
		return query.executeUpdate();
	}

	@Override
	public List<EmployeeEntity> findEmployeeByFirstAndLastName(String firstName, String lastName) {
		TypedQuery<EmployeeEntity> query = entityManager.createQuery(
				"select e from EmployeeEntity e "
				+ "join fetch e.departament d "
				+ "where upper(first_name) like concat(upper(:first_name), '%') and "
				+ "upper(last_name) like concat(upper(:last_name), '%')", EmployeeEntity.class);
		query.setParameter("first_name", firstName);
		query.setParameter("last_name", lastName);
		return query.getResultList();
	}

	@Override
	public List<EmployeeEntity> findEmployeesByDepartament(Long idDepartament) {
		TypedQuery<EmployeeEntity> query = entityManager.createQuery(
				"select e from EmployeeEntity e "
				+ "join fetch e.departament d "
				+ "where id_departament = :id_departament", EmployeeEntity.class);
		query.setParameter("id_departament", idDepartament);
		return query.getResultList();
	}

	
}
