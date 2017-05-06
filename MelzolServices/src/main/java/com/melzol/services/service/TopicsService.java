package com.melzol.services.service;

import java.util.List;

import com.melzol.services.beans.TopicsDTO;
import com.melzol.services.model.Topics;

public interface TopicsService {

	public abstract void addTopic(Topics topics);

	public abstract TopicsDTO searchById(Integer topicId);

	public abstract List<TopicsDTO> searchAll(int refid, int reftype, int start);

	public abstract TopicsDTO searchTopicView(int topicId);

	

}
