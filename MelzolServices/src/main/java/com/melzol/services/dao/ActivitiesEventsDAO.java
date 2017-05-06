package com.melzol.services.dao;

import java.util.Date;
import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.jdbc.core.JdbcTemplate;

import com.melzol.services.beans.ActivitiesEventsDTO;
import com.melzol.services.model.ActivitiesEvents;

public interface ActivitiesEventsDAO {
	
	public abstract JdbcTemplate getJdbcTemplate();

	public abstract void setJdbcTemplate(JdbcTemplate jdbcTemplate);

	public abstract void init(SessionFactory sessionFactory);

	public abstract void save(ActivitiesEvents activitiesevents);

	public abstract ActivitiesEvents findById(Integer id);

	public abstract List<ActivitiesEventsDTO> searchCityActivities(int memId, String city,Date curDate, int start);

	public abstract ActivitiesEventsDTO findActivityEventView(int id, int memId, int type);

	public abstract List<ActivitiesEventsDTO> findNeighbourhoodActivities(Double lat, Double lon, int memId, Date curDate, int start);

	public abstract List<ActivitiesEventsDTO> findActivitiesByCat(int memId, String city, int refType, int cat,
			int start);

	public abstract List<ActivitiesEventsDTO> findInCityActivities(int memId, String city, int refType, String title);

	public abstract List<ActivitiesEventsDTO> findInNeighbourhoodActivities(Double lat, Double lon, int memId,
			Date curDate, String title);

	public abstract List<ActivitiesEventsDTO> findMyActivities(int memId, int start);

	public abstract void updateActivityEvent(ActivitiesEventsDTO activitiesEventsDTO);

	public abstract void deleteActivityEvents(ActivitiesEvents activitiesEvents);

	public abstract List<ActivitiesEventsDTO> findInMyActivities(int memId, String title);
	
	public abstract List<ActivitiesEventsDTO> findCreatedActivities(int memId,int refType, int start);

	public abstract List<ActivitiesEventsDTO> findGroupEvents(int groupId, Date curDate, int start);

	public abstract List<ActivitiesEventsDTO> findGroupPastEvents(int groupId, Date curDate, int start);

	

}
