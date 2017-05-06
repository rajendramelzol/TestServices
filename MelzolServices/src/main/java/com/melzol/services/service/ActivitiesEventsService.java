package com.melzol.services.service;

import java.util.Date;
import java.util.List;

import com.melzol.services.beans.ActivitiesEventsDTO;
import com.melzol.services.model.ActivitiesEvents;

public interface ActivitiesEventsService {

	public abstract void addEvent(ActivitiesEvents activitiesevents);

	public abstract ActivitiesEvents searchById(Integer id);

	public abstract List<ActivitiesEventsDTO> searchCityActivities(int memId, String city, Date curDate, int start);

	public abstract ActivitiesEventsDTO searchActivityEventView(int id, int memid, int type);

	public abstract List<ActivitiesEventsDTO> searchNeighbourhoodActivities(Double lat, Double lon, int memId,
			Date curDate, int start);

	public abstract List<ActivitiesEventsDTO> searchActivitiesByCat(int memId, String city, int refType, int cat,
			int start);

	public abstract List<ActivitiesEventsDTO> searchInCityActivities(int memId, String city, int refType, String title);

	public abstract List<ActivitiesEventsDTO> searchInNeighbourhoodActivities(Double lat, Double lon, int memId,
			Date curDate, String title);

	public abstract List<ActivitiesEventsDTO> myActivities(int memId, int start);

	public abstract void updateActivityEvent(ActivitiesEventsDTO activitiesEventsDTO);

	public abstract void deleteActivityEvents(ActivitiesEvents activitiesEvents);

	public abstract List<ActivitiesEventsDTO> searchInMyActivities(int memId, String title);
	
	public abstract List<ActivitiesEventsDTO> createdActivities(int memId,int refType, int start);

	public abstract List<ActivitiesEventsDTO> searchGroupEvents(int groupId, Date curDate, int start);

	public abstract List<ActivitiesEventsDTO> searchGroupPastEvents(int groupId, Date curDate, int start);

}
