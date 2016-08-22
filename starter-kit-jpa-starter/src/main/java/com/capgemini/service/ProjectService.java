package com.capgemini.service;

import java.util.List;

import com.capgemini.domain.ProjectEntity;
import com.capgemini.exceptions.ProjectEntityDataIntegrityViolationException;

public interface ProjectService {

	ProjectEntity saveProject(ProjectEntity project) throws ProjectEntityDataIntegrityViolationException;
	
	void deleteProject(ProjectEntity project) throws ProjectEntityDataIntegrityViolationException;
	
	ProjectEntity updateProject(ProjectEntity project) throws ProjectEntityDataIntegrityViolationException;
	
	ProjectEntity findProjectById(long idProject);
	
	List<ProjectEntity> findProjectByIdManager(long idManager);
}
