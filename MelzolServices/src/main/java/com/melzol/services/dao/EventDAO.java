package com.melzol.services.dao;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.jdbc.core.JdbcTemplate;


import com.melzol.services.model.Event;


public interface EventDAO {
	public abstract JdbcTemplate getJdbcTemplate();

	public abstract void setJdbcTemplate(JdbcTemplate jdbcTemplate);

	public abstract void init(SessionFactory sessionFactory);
	
	public abstract void save(Event transientInstance);
	
	public abstract Event findById(java.lang.Integer id);

	public abstract List<Event> findAllEvents(int cat,int pincode);

	public abstract void update(Event event);

	public abstract List<Event> findMyEvents(int pincode, int owner);

	public abstract void delete(Event event);

	public abstract void incrementCount(Integer eventId);

	public abstract void decrementCount(int eventid);

	public abstract List<Event> joinedEvents(int pincode, int memid);

	public abstract List<Event> findCityEvents(int cat, String city);

}
