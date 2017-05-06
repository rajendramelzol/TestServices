package com.melzol.services.service;

import java.util.List;

import com.melzol.services.beans.EventDTO;
import com.melzol.services.model.Event;

public interface EventService {
	public abstract void addEvent(Event event);

	public abstract Event searchById(Integer id);

	public abstract List<Event> searchAllEvents(int cat,int pincode);
	
	public abstract EventDTO searchEventView(int id,int memid);

	public abstract void modifyEvent(Event event);

	public abstract List<Event> searchMyEvents(int pincode, int owner);

	public abstract void deleteEvent(Event event);

	public abstract void incrementCount(Integer eventId);

	public abstract void decrementCount(int eventid);

	public abstract List<Event> joinedEvents(int pincode, int memid);

	public abstract List<Event> searchCityEvents(int cat, String city);


}
