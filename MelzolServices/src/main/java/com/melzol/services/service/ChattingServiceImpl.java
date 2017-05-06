package com.melzol.services.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.melzol.services.dao.ChattingDAO;
import com.melzol.services.model.Chatting;

@Repository
@Service("chattingService")
@Transactional(propagation=Propagation.SUPPORTS, readOnly=true)
public class ChattingServiceImpl implements ChattingService {
	@Autowired
	private ChattingDAO chattingDAO;

	
	public void saveChatting(Chatting chatting) {
		chattingDAO.save(chatting);
		
	}

	
	public List<Chatting> searchAllChatting(int memId, int id) {
		
		return chattingDAO.findAllChatting(memId,id);
	}

}
