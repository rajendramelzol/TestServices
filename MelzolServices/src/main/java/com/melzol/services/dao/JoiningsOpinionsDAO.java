package com.melzol.services.dao;

import org.hibernate.SessionFactory;
import org.springframework.jdbc.core.JdbcTemplate;

import com.melzol.services.model.JoiningsOpinions;

public interface JoiningsOpinionsDAO {
	
	public abstract JdbcTemplate getJdbcTemplate();

	public abstract void setJdbcTemplate(JdbcTemplate jdbcTemplate);

	public abstract void init(SessionFactory sessionFactory);

	public abstract void save(JoiningsOpinions joiningsOpinions);

	public abstract void memberOpinion(int gossipId, int memId, int opinion);

	public abstract void exitGossip(int gossipId, int memId);

	public abstract void opinionOnBlog(JoiningsOpinions joiningsOpinions);
	
}
