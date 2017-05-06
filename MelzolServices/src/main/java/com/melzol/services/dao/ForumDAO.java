package com.melzol.services.dao;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.jdbc.core.JdbcTemplate;

import com.melzol.services.beans.ForumDTO;
import com.melzol.services.model.Forum;

public interface ForumDAO {
	
public abstract JdbcTemplate geJdbcTemplate();
	
	public abstract void setJdbcTemplate(JdbcTemplate jdbcTemplate);
	
	public abstract void init(SessionFactory sessionFactory);

	public abstract void save(Forum forum);

	public abstract Forum findById(Integer forumId);

	public abstract List<ForumDTO> findAll(Double lat, Double lon, int memId, int start);

	public abstract List<ForumDTO> findForum(Double lat, Double lon, int memId, String title);

	public abstract List<ForumDTO> findMyForum(int memId, int start);

	public abstract List<ForumDTO> findInMyForum(int memId, String title);

	public abstract void updateForum(Forum forum);

	public abstract void deleteForum(Forum forum);

	public abstract ForumDTO findForumView(int id);

}
