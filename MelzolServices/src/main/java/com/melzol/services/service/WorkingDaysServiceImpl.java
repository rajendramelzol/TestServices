package com.melzol.services.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.melzol.services.dao.WorkingDaysDAO;
import com.melzol.services.model.WorkingDays;

@Repository
@Service("workingDaysService")
@Transactional(propagation=Propagation.SUPPORTS, readOnly=true)
public class WorkingDaysServiceImpl implements WorkingDaysService{
	@Autowired
	WorkingDaysDAO workingDaysDAO;

	
	public void addWorikingDays(WorkingDays workingDays) {
		workingDaysDAO.save(workingDays);
		
	}

}
