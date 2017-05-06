package com.melzol.services.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.melzol.services.dao.TaggingDAO;
import com.melzol.services.model.Tagging;
@Repository
@Service("taggingService")
@Transactional(propagation=Propagation.SUPPORTS, readOnly=true)
public class TaggingServiceImpl implements TaggingService {
	
	private TaggingDAO taggingDAO;

	public TaggingDAO getTaggingDAO() {
		return taggingDAO;
	}
	@Autowired
	public void setTaggingDAO(TaggingDAO taggingDAO) {
		this.taggingDAO = taggingDAO;
	}

	public void addTagging(Tagging tagging) {
		taggingDAO.save(tagging);
		
	}
	
	public void searchUpdateRequest(int senderId,int receiverId,int status) {
		taggingDAO.findUpdateTagRequest(senderId,receiverId,status);
		
	}
	

	

}
