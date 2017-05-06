package com.melzol.services.dao;

import java.util.Date;
import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.jdbc.core.JdbcTemplate;

import com.melzol.services.beans.CommentsDTO;
import com.melzol.services.model.Comments;

public interface CommentsDAO {
	public abstract JdbcTemplate getJdbcTemplate();

	public abstract void setJdbcTemplate(JdbcTemplate jdbcTemplate);

	public abstract void init(SessionFactory sessionFactory);
	
	public abstract void save(Comments transientInstance);
	
	public abstract Comments findById(Date id);

	public abstract List<CommentsDTO> findAll(int refid,int reftype, int page);

	public abstract void delete(Comments comments);

}
