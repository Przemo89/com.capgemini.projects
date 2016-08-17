package com.capgemini.dao.impl;

import java.util.List;

import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import com.capgemini.dao.EmployeeDao;
import com.capgemini.domain.EmployeeEntity;

@Repository
public class EmployeeDaoImpl extends AbstractDao<EmployeeEntity, Long> implements EmployeeDao {

	@Override
	public int setEmployeeDepartament(Long idEmployee, Long idDepartament) {
		// TODO Auto-generated method stub
		Query query = entityManager.createQuery(
				"update EmployeeEntity set id_departament = :id_departament where id = :id_employee");
		query.setParameter("id_departament", idDepartament);
		query.setParameter("id_employee", idEmployee);
		return query.executeUpdate();
	}

	@Override
	public EmployeeEntity findEmployeeByFirstAndLastName(String firstName, String lastName) {
		// TODO Auto-generated method stub
		//      TypedQuery<BookEntity> query = entityManager.createQuery(
		//      "select book from BookEntity book where upper(book.title) like concat(upper(:title), '%')", BookEntity.class);
		//query.setParameter("title", title);
		//return query.getResultList();
		return null;
	}

	@Override
	public List<EmployeeEntity> findEmployeesByDepartament(Long idDepartament) {
		// TODO Auto-generated method stub
		return null;
	}

	
}
