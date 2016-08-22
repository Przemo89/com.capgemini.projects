package com.capgemini.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.capgemini.dao.DepartamentDao;
import com.capgemini.domain.DepartamentEntity;
import com.capgemini.service.DepartamentService;

@Service
@Transactional(readOnly = true)
public class DepartamentServiceImpl implements DepartamentService {

	@Autowired
	private DepartamentDao departamentRepository;
	
	@Override
	public DepartamentEntity findDepartamentById(long idDepartament) {
		return departamentRepository.findOne(idDepartament);
	}

	
}
