package com.melzol.services.dao;

import org.hibernate.SessionFactory;
import org.springframework.jdbc.core.JdbcTemplate;

import com.melzol.services.model.LatitudeLongitude;

public interface LatitudeLongitudeDAO {
	
	public abstract JdbcTemplate getJdbcTemplate();

	public abstract void setJdbcTemplate(JdbcTemplate jdbcTemplate);

	public abstract void init(SessionFactory sessionFactory);

	public abstract LatitudeLongitude checkLatLong(Double latitude, Double longitude);

	public abstract void save(LatitudeLongitude la);

}
