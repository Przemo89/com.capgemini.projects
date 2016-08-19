package com.capgemini.dao.impl;

import java.util.Date;
import java.util.List;

import javax.persistence.Query;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

import com.capgemini.dao.EmployeeProjectDao;
import com.capgemini.domain.EmployeeProjectEntity;

@Repository
public class EmployeeProjectDaoImpl extends AbstractDao<EmployeeProjectEntity, Long> implements EmployeeProjectDao {

	@Override
	public int setEmployeeTerminationDateInProject(EmployeeProjectEntity employeeProject, Date terminationDate) {
		Query query = entityManager.createQuery(
				"update EmployeeProjectEntity set termination_date = :termination_date where id = :id");
		query.setParameter("id", employeeProject.getId());
		query.setParameter("termination_date", terminationDate);
		// TODO Auto-generated method stub
		return query.executeUpdate();
	}
	
	@Override
	public List<EmployeeProjectEntity> findEmployeesCurrentlyWorkingInSpecificProject(long idProject) {
		TypedQuery<EmployeeProjectEntity> query = entityManager.createQuery( 
				"select ep from EmployeeProjectEntity ep "
				+ "join fetch ep.employee "
				+ "where id_project = :id_project and termination_date is null "
				+ "group by id_employee"
				, EmployeeProjectEntity.class);
		query.setParameter("id_project", idProject);
		return query.getResultList();
	}

	@Override
	public List<EmployeeProjectEntity> findEmployeesWorkingInSpecificProjectForSpecificTime(long idProject,
			int numberOfMonths) {
		// TODO Auto-generated method stub
		TypedQuery<EmployeeProjectEntity> query = entityManager.
				createNamedQuery("findEmployeesWorkingInSpecificProjectForSpecificTime", EmployeeProjectEntity.class);
		query.setParameter("number_of_months", numberOfMonths);
		query.setParameter("id_project", idProject);
		return query.getResultList();
	}
	
}
