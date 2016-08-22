package com.capgemini.service;

import static org.junit.Assert.assertEquals;

import java.util.List;

import javax.persistence.EntityManager;

import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import com.capgemini.domain.EmployeeEntity;
import com.capgemini.domain.ProjectEntity;
import com.capgemini.exceptions.ProjectEntityDataIntegrityViolationException;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class ProjectServiceTest {

	@Autowired
	private ProjectService projectService;
	
	@Autowired
	private EmployeeService employeeService;
	
	@Autowired
	private EntityManager entityManager;
	
	@Rule
	public ExpectedException thrown = ExpectedException.none();
	
	@Test
	public void testDeleteProject() throws Exception {
		// given
		final long idProjectToBeDeleted = 5L;
		ProjectEntity projectToBeDeleted = projectService.findProjectById(idProjectToBeDeleted);
		
		// when
		projectService.deleteProject(projectToBeDeleted);
		
		// then
		Assert.assertNull(projectService.findProjectById(idProjectToBeDeleted));
	}
	
	@Test
	public void testShouldThrowExceptionWhenDeleteProjectNotExisting() throws Exception {
		// given
		final long idProjectThatExist = 3L;
		final long idPojectThatNotExists = 5678L;
		ProjectEntity projectToBeDeleted = projectService.findProjectById(idProjectThatExist);
		entityManager.detach(projectToBeDeleted);
		projectToBeDeleted.setId(idPojectThatNotExists);
		
		// then
		thrown.expect(ProjectEntityDataIntegrityViolationException.class);
		thrown.expectMessage(" does not exist and therefore cannot be deleted!");
		
		// when
		projectService.deleteProject(projectToBeDeleted);
	}
	
	@Test
	public void testFindProjectByIdManager() throws Exception {
		// given
		final long idManager = 12L;
		final long idOfProperResultProject = 5L;
		
		// when
		List<ProjectEntity> projectsWithSpecifiedManager = projectService.findProjectByIdManager(idManager);
		int resultSize = 1;
		
		// then
		assertEquals(resultSize, projectsWithSpecifiedManager.size());
		assertEquals(idOfProperResultProject, projectsWithSpecifiedManager.get(0).getId());
	}
	
	@Test
	public void testSaveProject() throws Exception {
		// given
		final long idManager = 8L;
		final EmployeeEntity manager = employeeService.findEmployeeById(idManager);
		final String name = "Medical Project";
		final boolean isInternal = true;
		ProjectEntity project = new ProjectEntity();
		project.setName(name);
		project.setManager(manager);
		project.setInternal(isInternal);
		
		// when
		ProjectEntity projectSaved = projectService.saveProject(project);

		// then
		assertEquals(name, projectSaved.getName());
		assertEquals(idManager, projectSaved.getManager().getId());
	}
	
	@Test
	public void testShouldThrowExceptionWhenSaveProjectThatAlreadyExists() throws Exception {
		// given
		final long idProjectThatExist = 3L;
		ProjectEntity projectExistingToBeSaved = projectService.findProjectById(idProjectThatExist);
		entityManager.detach(projectExistingToBeSaved);

		// then
		thrown.expect(ProjectEntityDataIntegrityViolationException.class);
		thrown.expectMessage(" already exists and therefore cannot be added!");
		
		// when
		projectService.saveProject(projectExistingToBeSaved);
	}
	
	@Test
	public void testShouldThrowExceptionWhenSaveProjectThatHasNotUniqueName() throws Exception {
		// given
		final long idProjectThatExist = 3L;
		ProjectEntity projectExisting = projectService.findProjectById(idProjectThatExist);
		entityManager.detach(projectExisting);
		final long idManager = 4L;
		EmployeeEntity manager = employeeService.findEmployeeById(idManager);
		entityManager.detach(manager);
		final boolean isInternal = true;
		ProjectEntity projectToBeSaved = new ProjectEntity();
		projectToBeSaved.setName(projectExisting.getName());
		projectToBeSaved.setInternal(isInternal);
		projectToBeSaved.setManager(manager);

		// then
		thrown.expect(ProjectEntityDataIntegrityViolationException.class);
		thrown.expectMessage("! Please provide proper, unique name.");
		
		// when
		projectService.saveProject(projectToBeSaved);
	}
	
	@Test
  public void testShouldUpdateProject() throws Exception {
  	// given
  	final long idProject = 3L;
  	final String name = "random";
  	ProjectEntity projectToBeUpdated = projectService.findProjectById(idProject);
  	projectToBeUpdated.setName(name);

	// when
  	ProjectEntity projectUpdated = projectService.updateProject(projectToBeUpdated);
  	
  	// then
  	assertEquals(name, projectUpdated.getName());
  }
	
	@Test
	public void testShouldThrowExceptionWhenUpdateProjectWhichNotExists() throws Exception {
		// given
		final long idProjectThatExist = 3L;
		final long idPojectThatNotExists = 5678L;
		final String name = "random";
		ProjectEntity projectToBeUpdated = projectService.findProjectById(idProjectThatExist);
		entityManager.detach(projectToBeUpdated);
		projectToBeUpdated.setName(name);
		projectToBeUpdated.setId(idPojectThatNotExists);

		// then
		thrown.expect(ProjectEntityDataIntegrityViolationException.class);
		thrown.expectMessage(" does not exist and therefore cannot be updated!");
		
		// when
		projectService.updateProject(projectToBeUpdated);
	}
	
	@Test
	public void testShouldThrowExceptionWhenUpdateProjectWithNonUniqueName() throws Exception {
		// given
		final long idProjectThatExistOne = 3L;
		ProjectEntity projectExistingOne = projectService.findProjectById(idProjectThatExistOne);
		entityManager.detach(projectExistingOne);
		final long idProjectThatExistTwo = 4L;
		ProjectEntity projectExistingTwo = projectService.findProjectById(idProjectThatExistTwo);
		entityManager.detach(projectExistingTwo);
		projectExistingOne.setName(projectExistingTwo.getName());

		// then
		thrown.expect(ProjectEntityDataIntegrityViolationException.class);
		thrown.expectMessage("! Please provide proper, unique name.");
		
		// when
		projectService.updateProject(projectExistingOne);
	}
	
}
