package com.capgemini.dao.impl;

import org.springframework.stereotype.Repository;

import com.capgemini.dao.ProjectDao;
import com.capgemini.domain.ProjectEntity;

@Repository
public class ProjectDaoImpl extends AbstractDao<ProjectEntity, Long> implements ProjectDao {

	@Override
	public void deleteProject(ProjectEntity project) {
		// TODO Auto-generated method stub
		project = entityManager.merge(project);
		entityManager.remove(project);
	}

	
}
