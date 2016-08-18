package com.capgemini.service;

import java.util.List;

import com.capgemini.domain.ProjectEntity;
import com.capgemini.exceptions.ProjectEntityNotExistException;

public interface ProjectService {

	ProjectEntity saveProject(ProjectEntity project);
	
	void deleteProject(ProjectEntity project) throws ProjectEntityNotExistException;
	
	ProjectEntity updateProject(ProjectEntity project) throws ProjectEntityNotExistException;
	
	ProjectEntity findProjectById(long idProject);
	
	List<ProjectEntity> findProjectByIdManager(long idManager);
}
