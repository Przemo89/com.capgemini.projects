package com.capgemini.dao.impl;

import java.util.List;

import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

import com.capgemini.dao.ProjectDao;
import com.capgemini.domain.ProjectEntity;

@Repository
public class ProjectDaoImpl extends AbstractDao<ProjectEntity, Long> implements ProjectDao {

	@Override
	public List<ProjectEntity> findProjectsByIdManager(long idManager) {
		TypedQuery<ProjectEntity> query = entityManager.createQuery(
				"select project from ProjectEntity project where id_manager = :id_manager", ProjectEntity.class);
		query.setParameter("id_manager", idManager);
		return query.getResultList();
	}
	
}