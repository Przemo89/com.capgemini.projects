package com.capgemini.dao.impl;

import java.util.Date;
import java.util.List;

import javax.persistence.Query;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

import com.capgemini.dao.EmployeeProjectDao;
import com.capgemini.domain.EmployeeEntity;
import com.capgemini.domain.EmployeeProjectEntity;

@Repository
public class EmployeeProjectDaoImpl extends AbstractDao<EmployeeProjectEntity, Long> implements EmployeeProjectDao {

	@Override
	public int setEmployeeTerminationDateInProject(EmployeeProjectEntity employeeProject, Date terminationDate) {
		Query query = entityManager.createQuery(
				"update EmployeeProjectEntity set termination_date = :termination_date where id = :id");
		query.setParameter("id", employeeProject.getId());
		query.setParameter("termination_date", terminationDate);
		return query.executeUpdate();
	}
	
	@Override
	public List<EmployeeEntity> findEmployeesCurrentlyWorkingInSpecificProject(long idProject) {
		TypedQuery<EmployeeEntity> query = entityManager.createQuery( 
				"select e from EmployeeEntity e "
				+ "left join e.employeeProjects ep "
				+ "where id_project = :id_project and termination_date is null "
				+ "group by e "
				+ "order by e asc"
				, EmployeeEntity.class);
		query.setParameter("id_project", idProject);
		return query.getResultList();
	}

	@Override
	public List<EmployeeEntity> findEmployeesWorkingInSpecificProjectForSpecificTime(long idProject,
			int numberOfMonths) {
		TypedQuery<EmployeeEntity> query = entityManager.
				createNamedQuery("findEmployeesWorkingInSpecificProjectForSpecificTime", EmployeeEntity.class);
		query.setParameter("number_of_months", numberOfMonths);
		query.setParameter("id_project", idProject);
		return query.getResultList();
	}
	
}
