package com.capgemini.service;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({ EmployeeProjectServiceTest.class, EmployeeServiceTest.class, ProjectServiceTest.class })
public class ServiceTestSuite {

}
