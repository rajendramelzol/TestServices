package com.melzol.services.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.melzol.services.dao.EventMembersDAO;
import com.melzol.services.model.EventMembers;

@Repository
@Service("eventMemberService")
@Transactional(propagation=Propagation.SUPPORTS, readOnly=true)
public class EventMembersServiceImpl implements EventMembersService{
	
	private EventMembersDAO eventMembersDAO;

	public EventMembersDAO getEventMembersDAO() {
		return eventMembersDAO;
	}
	@Autowired
	public void setEventMembersDAO(EventMembersDAO eventMembersDAO) {
		this.eventMembersDAO = eventMembersDAO;
	}
	public void addEventMembers(EventMembers eventMembers){
		eventMembersDAO.save(eventMembers);
	}
	
	public List searchByProperty(String propertyName, int value){
		return eventMembersDAO.findByProperty(propertyName, value);
	}
	public void deleteEventMembers(int eventid, int memid) {
		eventMembersDAO.deleteEventMembers(eventid,memid);
		
	}
	public void deleteEventDetails(int id) {
		eventMembersDAO.deleteEventDetails(id);
		
	}
}
