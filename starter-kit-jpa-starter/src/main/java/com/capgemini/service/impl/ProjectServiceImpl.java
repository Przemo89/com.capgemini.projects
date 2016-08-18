package com.capgemini.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.capgemini.dao.ProjectDao;
import com.capgemini.domain.ProjectEntity;
import com.capgemini.exceptions.ProjectEntityNotExistException;
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
	public ProjectEntity saveProject(ProjectEntity project) {
		return projectRepository.save(project);
	}

	@Override
	public void deleteProject(ProjectEntity project) throws ProjectEntityNotExistException {
		if (!projectRepository.exists(project.getId())) {
			throw new ProjectEntityNotExistException(project.getId());
		}
		projectRepository.deleteProject(project);
	}

	@Override
	public ProjectEntity updateProject(ProjectEntity project) throws ProjectEntityNotExistException {
		// TODO Auto-generated method stub
		if (!projectRepository.exists(project.getId())) {
			throw new ProjectEntityNotExistException(project.getId());
		}
		return projectRepository.update(project);
	}
	
}
