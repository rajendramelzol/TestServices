package com.melzol.services.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.melzol.services.beans.CommentsDTO;
import com.melzol.services.beans.ForumDTO;
import com.melzol.services.dao.CommentsDAO;
import com.melzol.services.dao.ForumDAO;
import com.melzol.services.model.Forum;
import com.melzol.services.util.Utils;

@Repository
@Service("forumService")
@Transactional(propagation=Propagation.SUPPORTS, readOnly=true)
public class ForumServiceImpl implements ForumService{
	@Autowired
	ForumDAO forumDAO;
	@Autowired
	CommentsDAO commentsDAO;

	public void addForum(Forum forum) {
		forumDAO.save(forum);
		
	}
	public Forum searchById(Integer forumId) {
		return forumDAO.findById(forumId);
	}
	public List<ForumDTO> searchAll(Double lat,Double lon,int memId, int start) {
		return forumDAO.findAll(lat,lon,memId,start);
	}
	public List<ForumDTO> searchForum(Double lat,Double lon,int memId, String title) {
		return forumDAO.findForum(lat,lon,memId,title);
	}
	public List<ForumDTO> searchMyForum(int memId, int start) {
		return forumDAO.findMyForum(memId,start);
	}
	public List<ForumDTO> searchInMyForum(int memId, String title) {
		return forumDAO.findInMyForum(memId,title);
	}
	public void updateForum(Forum forum) {
	    forumDAO.updateForum(forum);
		
	}
	
	public void deleteForum(Forum forum) {
		forumDAO.deleteForum(forum);
		
	}
	public ForumDTO searchForumView(int id) {
	
		ForumDTO forumDTO= forumDAO.findForumView(id);
		List<CommentsDTO> comments= commentsDAO.findAll(id,5,0);
		for(CommentsDTO c:comments){
			c.setcDate(Utils.formatDateComments(c.getCreatedTs()));
		}
		forumDTO.setComments(comments);
		
		return forumDTO;
	}
	
	

}
