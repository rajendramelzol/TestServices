package com.melzol.services.dao;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.jdbc.core.JdbcTemplate;

import com.melzol.services.model.Chatting;

public interface ChattingDAO {
	
	public abstract JdbcTemplate getJdbcTemplate();

	public abstract void setJdbcTemplate(JdbcTemplate jdbcTemplate);

	public abstract void init(SessionFactory sessionFactory);

	public abstract void save(Chatting chatting);

	public abstract List<Chatting> findAllChatting(int memId, int id);

}
