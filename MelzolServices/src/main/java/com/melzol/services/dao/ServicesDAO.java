package com.melzol.services.dao;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.jdbc.core.JdbcTemplate;

import com.melzol.services.beans.ServiceDTO;
import com.melzol.services.model.Services;

public interface ServicesDAO {
	
	public abstract JdbcTemplate getJdbcTemplate();

	public abstract void setJdbcTemplate(JdbcTemplate jdbcTemplate);

	public abstract void init(SessionFactory sessionFactory);
	
	public abstract void save(Services transientInstance);

	public abstract Services findById(Integer serviceId);
	
	public abstract Services findServiceView(Integer serviceId);

	public abstract List<ServiceDTO> findAllServices(int cat, String city,int page);

	public abstract List<ServiceDTO> findMyServices(int memId, int start);
	
	public abstract List<ServiceDTO> findNeighbourhoodServices(Double lat, Double lon, int start);

	public abstract void update(ServiceDTO serviceDTO);
	
	public abstract void deleteServices(Services service);
	
	public abstract List<ServiceDTO> findInCityServices(String city,int cat,String title);
	
	public abstract List<ServiceDTO> findInNeighbourhoodServices(Double lat, Double lon, String title);

}
