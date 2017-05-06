package com.melzol.services.dao;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.jdbc.core.JdbcTemplate;

import com.melzol.services.model.EventMembers;

public interface EventMembersDAO {
	
	public abstract JdbcTemplate getJdbcTemplate();

	public abstract void setJdbcTemplate(JdbcTemplate jdbcTemplate);

	public abstract void init(SessionFactory sessionFactory);
	
	public abstract void save(EventMembers transientInstance);

	public abstract List findByProperty(String string, int id);

	public abstract void deleteEventMembers(int eventid, int memid);

	public abstract void update(String eventName, Integer id);

	public abstract void updateMemberName(String firstName, Integer memberId);

	public abstract void deleteEventDetails(int id);

	

}
