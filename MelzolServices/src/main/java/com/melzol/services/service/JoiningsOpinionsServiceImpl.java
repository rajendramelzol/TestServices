package com.melzol.services.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.melzol.services.dao.JoiningsOpinionsDAO;
import com.melzol.services.model.JoiningsOpinions;

@Repository
@Service("joiningsOpinionsService")
@Transactional(propagation=Propagation.SUPPORTS, readOnly=true)
public class JoiningsOpinionsServiceImpl implements JoiningsOpinionsService {
	@Autowired
	private JoiningsOpinionsDAO joiningsOpinionsDAO;

	
	public void addJoiningsOpinions(JoiningsOpinions joiningsOpinions) {

		joiningsOpinionsDAO.save(joiningsOpinions);
		
	}

	public void memberOpinion(int gossipId,int memId,int opinion) {
		joiningsOpinionsDAO.memberOpinion(gossipId,memId,opinion);
		
	}

	public void exitGossip(int gossipId, int memId) {
		joiningsOpinionsDAO.exitGossip(gossipId,memId);
		
	}
	public void opinionOnBlog(JoiningsOpinions joiningsOpinions) {
		joiningsOpinionsDAO.opinionOnBlog(joiningsOpinions);
	}
}
