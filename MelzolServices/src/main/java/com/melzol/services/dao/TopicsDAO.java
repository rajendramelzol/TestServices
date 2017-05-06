package com.melzol.services.dao;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.jdbc.core.JdbcTemplate;

import com.melzol.services.beans.TopicsDTO;
import com.melzol.services.model.Topics;

public interface TopicsDAO {
	
	public abstract JdbcTemplate getJdbcTemplate();

	public abstract void setJdbcTemplate(JdbcTemplate jdbcTemplate);

	public abstract void init(SessionFactory sessionFactory);

	public abstract void save(Topics topics);

	public abstract TopicsDTO findById(Integer topicId);

	public abstract List<TopicsDTO> findAll(int refId, int refType, int start);

	

}
