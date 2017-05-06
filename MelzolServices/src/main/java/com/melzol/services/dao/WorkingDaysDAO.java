package com.melzol.services.dao;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.jdbc.core.JdbcTemplate;

import com.melzol.services.model.WorkingDays;

public interface WorkingDaysDAO {
	
public abstract JdbcTemplate geJdbcTemplate();
	
	public abstract void setJdbcTemplate(JdbcTemplate jdbcTemplate);
	
	public abstract void init(SessionFactory sessionFactory);
	
   public abstract void save(WorkingDays workingDays);

    public abstract List<WorkingDays> findAll(Integer refId, int refType);

	public abstract void updateWorikingDays(WorkingDays w);

}
