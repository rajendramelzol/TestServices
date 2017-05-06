package com.melzol.services.service;

import java.util.List;

import com.melzol.services.beans.MemberDTO;
import com.melzol.services.model.BlogSubscribe;
import com.melzol.services.model.Members;

public interface BlogSubscribeService {

	public abstract void subscribe(BlogSubscribe blogSubscribe);

	public abstract List<MemberDTO> searchSubscribers(int memId, int start);

	public abstract List<Members> searchMySubscribers(int memId, int start);
	
	

}
