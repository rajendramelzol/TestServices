package com.melzol.services.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.melzol.services.beans.CommentsDTO;
import com.melzol.services.beans.TopicsDTO;
import com.melzol.services.dao.CommentsDAO;
import com.melzol.services.dao.TopicsDAO;
import com.melzol.services.model.Topics;
import com.melzol.services.util.Utils;

@Repository
@Service("topicsService")
@Transactional(propagation=Propagation.SUPPORTS, readOnly=true)
public class TopicsServiceImpl implements TopicsService{
	
	@Autowired
	TopicsDAO topicsDAO;
	@Autowired
	CommentsDAO commentsDAO;
	
	public void addTopic(Topics topics) {
		topicsDAO.save(topics);
		
	}

	
	public TopicsDTO searchById(Integer topicId) {
	
		return topicsDAO.findById(topicId);
	}

	
	public List<TopicsDTO> searchAll(int refId, int refType,int start) {
		
		return topicsDAO.findAll(refId,refType,start);
	}

	public TopicsDTO searchTopicView(int topicId) {
		
		TopicsDTO topic=topicsDAO.findById(topicId);
		int refType=7;
		List<CommentsDTO> comments= commentsDAO.findAll(topicId,refType,0);
		for(CommentsDTO c:comments){
			c.setcDate(Utils.formatDateComments(c.getCreatedTs()));
		}
		topic.setComments(comments);
		
		return topic;
	}

}
