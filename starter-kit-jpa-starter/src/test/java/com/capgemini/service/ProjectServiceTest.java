package com.capgemini.service;

import static org.junit.Assert.assertEquals;

import java.util.List;

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
import com.capgemini.exceptions.ProjectEntityNotExistException;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class ProjectServiceTest {

	@Autowired
	private ProjectService projectService;
	
	@Autowired
	private EmployeeService employeeService;
	
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
	public void testShouldThrowExceptionWhenUpdateProject() throws Exception {
		// given
		final long idProjectThatExist = 3L;
		final long idPojectThatNotExists = 5678L;
		final String name = "random";
		ProjectEntity projectToBeUpdated = projectService.findProjectById(idProjectThatExist);
		projectToBeUpdated.setName(name);
		projectToBeUpdated.setId(idPojectThatNotExists);

		// then
		thrown.expect(ProjectEntityNotExistException.class);
		thrown.expectMessage("does not exist!");
		
		// when
		projectService.updateProject(projectToBeUpdated);
	}
	
}
