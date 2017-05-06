package com.melzol.services.service;

import java.util.List;

import com.melzol.services.model.EventMembers;

public interface EventMembersService {
	public abstract void addEventMembers(EventMembers eventMembers);
	
	public abstract List searchByProperty(String propertyName, int value);

	public abstract void deleteEventMembers(int eventid, int memid);

	public abstract void deleteEventDetails(int id);

}
