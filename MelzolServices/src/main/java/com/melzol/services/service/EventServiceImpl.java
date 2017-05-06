package com.melzol.services.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.melzol.services.beans.EventDTO;
import com.melzol.services.beans.MemberDTO;
import com.melzol.services.dao.EventDAO;
import com.melzol.services.dao.EventMembersDAO;
import com.melzol.services.model.Event;
import com.melzol.services.model.EventMembers;
import com.melzol.services.util.Utils;

@Repository
@Service("eventService")
@Transactional(propagation=Propagation.SUPPORTS, readOnly=true)
public class EventServiceImpl implements EventService {
	private EventDAO eventDAO;
	private EventMembersDAO eventMembersDAO;
	
	public EventDAO getEventDAO() {
		return eventDAO;
	}
	@Autowired
	public void setEventDAO(EventDAO eventDAO) {
		this.eventDAO = eventDAO;
	}
	
	public EventMembersDAO getEventMembersDAO() {
		return eventMembersDAO;
	}
	@Autowired
	public void setEventMembersDAO(EventMembersDAO eventMembersDAO) {
		this.eventMembersDAO = eventMembersDAO;
	}
	public void addEvent(Event event){
		event.setCreatedDate(new Date());
		event.setUpdatedDate(new Date());
		eventDAO.save(event);
		EventMembers eventMembers=new EventMembers();
		eventMembers.setEventId(event.getId());
		eventMembers.setEventName(event.getEventName());
		eventMembers.setMemberId(event.getOwner());
		eventMembers.setMemberName(event.getOwnerName());
		eventMembers.setOwner(event.getOwner());
		eventMembers.setPincode(event.getPincode());
		eventMembers.setUpdatedTs(new Date());
		eventMembersDAO.save(eventMembers);
		
	}
	public Event searchById(Integer id){
		return eventDAO.findById(id);
	}
	public EventDTO searchEventView(int id,int memid){
		Event event=eventDAO.findById(id);
		EventDTO eventDTO=new EventDTO();
		List<EventMembers> eventMembers=eventMembersDAO.findByProperty("eventId", id);
		eventDTO.setId(event.getId());
		eventDTO.setEventName(event.getEventName());
		eventDTO.setCategory(event.getCategory());
		eventDTO.setType(event.getType());
		eventDTO.setfDate(Utils.formatDateTime(event.getFromDate()));
		eventDTO.setFtTime(event.getFtTime());
		eventDTO.setLocation(event.getLocation());
		eventDTO.setDescription(event.getDescription());
		eventDTO.setEventPic(event.getEventPic());
		eventDTO.setPincode(event.getPincode());
		eventDTO.setOwner(event.getOwner());
		eventDTO.setCreatedDate(event.getCreatedDate());
		eventDTO.setUpdatedDate(event.getUpdatedDate());
		eventDTO.setOwnerName(event.getOwnerName());
		if(event.getFromDate()!=null){
		if(event.getFromDate().after(new Date()))
			eventDTO.setPast(true);
		}
		for(EventMembers mem:eventMembers){
			MemberDTO m=new MemberDTO();
			m.setMemberId(mem.getMemberId());
			if(m.getMemberId()==memid)
				eventDTO.setMember(true);
		//	m.setMemberName(mem.getMemberName());
			
			eventDTO.getMem().add(m);
		}
		return eventDTO;
	}
	public List<Event> searchAllEvents(int cat,int pincode) {
	
		return eventDAO.findAllEvents(cat,pincode);
	}
	public void modifyEvent(Event event){
		eventDAO.update(event);
		eventMembersDAO.update(event.getEventName(),event.getId());
		
	}
	public List<Event> searchMyEvents(int pincode,int owner) {
		
		return eventDAO.findMyEvents(pincode,owner);
	}
	public void deleteEvent(Event event){
		eventDAO.delete(event);
		
	}
	public void incrementCount(Integer eventId) {
		eventDAO.incrementCount(eventId);
		
	}
	public void decrementCount(int eventid) {
		eventDAO.decrementCount(eventid);
		
	}
	public List<Event> joinedEvents(int pincode, int memid) {
		return eventDAO.joinedEvents(pincode,memid);
		
	}
	public List<Event> searchCityEvents(int cat, String city) {
		return eventDAO.findCityEvents(cat,city);
	}

}
