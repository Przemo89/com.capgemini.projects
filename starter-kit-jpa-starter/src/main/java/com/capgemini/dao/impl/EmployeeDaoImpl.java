package com.capgemini.dao.impl;

import java.util.List;

import javax.persistence.Query;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

import com.capgemini.dao.EmployeeDao;
import com.capgemini.domain.EmployeeEntity;

@Repository
public class EmployeeDaoImpl extends AbstractDao<EmployeeEntity, Long> implements EmployeeDao {

//	@Override
//	public void deleteEmployee(EmployeeEntity employee) {
//		employee = entityManager.merge(employee);
//		entityManager.remove(employee);
//	}

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
