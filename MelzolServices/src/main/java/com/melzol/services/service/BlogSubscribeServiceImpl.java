package com.melzol.services.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import com.melzol.services.beans.MemberDTO;
import com.melzol.services.dao.BlogSubscribeDAO;
import com.melzol.services.model.BlogSubscribe;
import com.melzol.services.model.Members;

@Repository
@Service("blogSubscribeService")
public class BlogSubscribeServiceImpl implements BlogSubscribeService{

	@Autowired
	BlogSubscribeDAO blogSubscribeDAO;
	
	public void subscribe(BlogSubscribe blogSubscribe) {
		blogSubscribeDAO.subscribe(blogSubscribe);
		
	}

	public List<MemberDTO> searchSubscribers(int memId, int start) {
		return blogSubscribeDAO.findSubscribers(memId,start);
	}

	public List<Members> searchMySubscribers(int memId, int start) {
		return blogSubscribeDAO.findMySubscribers(memId,start);
	}

}
