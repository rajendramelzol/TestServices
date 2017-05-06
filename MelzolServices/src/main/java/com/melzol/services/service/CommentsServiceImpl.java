package com.melzol.services.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.melzol.services.beans.CommentsDTO;
import com.melzol.services.dao.CommentsDAO;
import com.melzol.services.model.Comments;

@Repository
@Service("commentService")
@Transactional(propagation=Propagation.SUPPORTS, readOnly=true)
public class CommentsServiceImpl implements CommentsService{
	@Autowired
	private CommentsDAO commentsDAO;


	public void addEvent(Comments comments) {
		commentsDAO.save(comments);
	}

	public Comments searchById(Date id) {
		return commentsDAO.findById(id);
	}

	public List<CommentsDTO> searchAll( int refid,int reftype,int page) {
		return commentsDAO.findAll(refid,reftype,page);
	}

	
	public void deleteComments(Comments comments) {
		commentsDAO.delete(comments);
		
	}
	
	

}
