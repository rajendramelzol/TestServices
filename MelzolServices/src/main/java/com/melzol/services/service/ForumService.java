package com.melzol.services.service;

import java.util.List;

import com.melzol.services.beans.ForumDTO;
import com.melzol.services.model.Forum;

public interface ForumService {

	public abstract void addForum(Forum forum);

	public abstract Forum searchById(Integer forumId);

	public abstract List<ForumDTO> searchAll(Double lat, Double lon, int memId, int start);

	public abstract List<ForumDTO> searchForum(Double lat, Double lon, int memId, String title);

	public abstract List<ForumDTO> searchMyForum(int memId, int start);

	public abstract List<ForumDTO> searchInMyForum(int memId, String title);

	public abstract void updateForum(Forum forum);

	public abstract void deleteForum(Forum forum);

	public abstract ForumDTO searchForumView(int id);

}
