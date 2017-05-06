package com.melzol.services.service;

import com.melzol.services.model.JoiningsOpinions;

public interface JoiningsOpinionsService {

	public abstract void addJoiningsOpinions(JoiningsOpinions joiningsOpinions);

	public abstract void memberOpinion(int gossipId,int memId,int opinion);

	public abstract void exitGossip(int gossipId, int memId);

	public abstract void opinionOnBlog(JoiningsOpinions joiningsOpinions);

}
