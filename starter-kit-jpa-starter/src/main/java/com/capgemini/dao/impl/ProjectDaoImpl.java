package com.capgemini.dao.impl;

import java.util.List;

import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

import com.capgemini.dao.ProjectDao;
import com.capgemini.domain.ProjectEntity;

@Repository
public class ProjectDaoImpl extends AbstractDao<ProjectEntity, Long> implements ProjectDao {

	@Override
	public List<ProjectEntity> findProjectsByIdAndName(ProjectEntity project) {
		// TODO Auto-generated method stub
		TypedQuery<ProjectEntity> query = entityManager.createQuery(
				"select project from ProjectEntity project where id = :id "
				+ "or name = :name", ProjectEntity.class);
		query.setParameter("id", project.getId());
		query.setParameter("name", project.getName());
		return query.getResultList();
	}

	@Override
	public void deleteProject(ProjectEntity project) {
		project = entityManager.merge(project);
		entityManager.remove(project);
	}

	@Override
	public List<ProjectEntity> findProjectsByIdManager(long idManager) {
		TypedQuery<ProjectEntity> query = entityManager.createQuery(
				"select project from ProjectEntity project where id_manager = :id_manager", ProjectEntity.class);
		query.setParameter("id_manager", idManager);
		return query.getResultList();
	}
}
