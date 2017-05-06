package com.melzol.services.dao;

import org.hibernate.SessionFactory;
import org.springframework.jdbc.core.JdbcTemplate;

import com.melzol.services.model.Tagging;

public interface TaggingDAO {
	
	public abstract JdbcTemplate getJdbcTemplate();

	public abstract void setJdbcTemplate(JdbcTemplate jdbcTemplate);

	public abstract void init(SessionFactory sessionFactory);

	public abstract void save(Tagging tagging);
	
	public abstract void findUpdateTagRequest(int senderId,int receiverId,int status);

}
