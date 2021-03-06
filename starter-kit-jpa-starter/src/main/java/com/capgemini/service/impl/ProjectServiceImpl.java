package com.capgemini.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.capgemini.dao.ProjectDao;
import com.capgemini.domain.ProjectEntity;
import com.capgemini.exceptions.ProjectEntityDataIntegrityViolationException;
import com.capgemini.service.ProjectService;

@Service
@Transactional(readOnly = false)
public class ProjectServiceImpl implements ProjectService {

	@Autowired
	private ProjectDao projectRepository;
	
	@Override
	public List<ProjectEntity> findProjectByIdManager(long idManager) {
		return projectRepository.findProjectsByIdManager(idManager);
	}
	
	@Override
	@Transactional(readOnly = true)
	public ProjectEntity findProjectById(long idProject) {
		if (projectRepository.exists(idProject)) {
			return projectRepository.findOne(idProject);
		}
		return null;
	}
	
	@Override
	public ProjectEntity saveProject(ProjectEntity project) throws ProjectEntityDataIntegrityViolationException {
		List<ProjectEntity> projectsToCheck = projectRepository.findProjectsByIdAndName(project);
		isProjectToBeSavedViolatesConstraints(projectsToCheck, project);
		return projectRepository.save(project);
	}

	private void isProjectToBeSavedViolatesConstraints(List<ProjectEntity> projectsToCheck, ProjectEntity project)
			throws ProjectEntityDataIntegrityViolationException {
		boolean isProjectExist = false;
		for (ProjectEntity projectChecked : projectsToCheck) {
			if (projectChecked.getId() == project.getId()) {
				isProjectExist = true;
			} else {
				if (projectChecked.getName().equals(project.getName())) {
					throw new ProjectEntityDataIntegrityViolationException("Provided project's name is the same as "
							+ "project with id = " + projectChecked.getId() 
					+ "! Please provide proper, unique name.");
				}
			}
			
		}
		if (isProjectExist == true) {
			throw new ProjectEntityDataIntegrityViolationException("Project with id = " + project.getId() + 
						" already exists and therefore cannot be added!");
		}
	}

	@Override
	public void deleteProject(ProjectEntity project) throws ProjectEntityDataIntegrityViolationException {
		if (!projectRepository.exists(project.getId())) {
			throw new ProjectEntityDataIntegrityViolationException("Project with id = " + project.getId() + 
					" does not exist and therefore cannot be deleted!");
		}
		projectRepository.deleteProject(project);
	}

	@Override
	public ProjectEntity updateProject(ProjectEntity project) throws ProjectEntityDataIntegrityViolationException {
		List<ProjectEntity> projectsToCheck = projectRepository.findProjectsByIdAndName(project);
		isProjectToBeUpdatedViolatesConstraints(projectsToCheck, project);
		return projectRepository.update(project);
	}
	
	private void isProjectToBeUpdatedViolatesConstraints(List<ProjectEntity> projectsToCheck, ProjectEntity project)
			throws ProjectEntityDataIntegrityViolationException {
		boolean isProjectExist = false;
		for (ProjectEntity projectChecked : projectsToCheck) {
			if (projectChecked.getId() == project.getId()) {
				isProjectExist = true;
			} else {
				if (projectChecked.getName().equals(project.getName())) {
					throw new ProjectEntityDataIntegrityViolationException("Provided project's name is the same as "
							+ "project with id = " + projectChecked.getId() 
					+ "! Please provide proper, unique name.");
				}
			}
		}
		if (isProjectExist == false) {
			throw new ProjectEntityDataIntegrityViolationException("Project with id = " + project.getId() + 
						" does not exist and therefore cannot be updated!");
		}
	}
	
}
