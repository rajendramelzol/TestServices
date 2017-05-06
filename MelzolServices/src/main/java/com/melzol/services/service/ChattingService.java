package com.melzol.services.service;

import java.util.List;

import com.melzol.services.model.Chatting;

public interface ChattingService {

	public abstract void saveChatting(Chatting chatting);

	public abstract List<Chatting> searchAllChatting(int memId, int id);

}
