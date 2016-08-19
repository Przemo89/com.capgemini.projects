package com.capgemini.dao.impl;

import org.springframework.stereotype.Repository;

import com.capgemini.dao.DepartamentDao;
import com.capgemini.domain.DepartamentEntity;

@Repository
public class DepartamentDaoImpl extends AbstractDao<DepartamentEntity, Long> implements DepartamentDao {

}
