package com.melzol.services.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.melzol.services.beans.ActivitiesEventsDTO;
import com.melzol.services.dao.ActivitiesEventsDAO;
import com.melzol.services.dao.MembersDAO;
import com.melzol.services.dao.WorkingDaysDAO;
import com.melzol.services.model.ActivitiesEvents;
import com.melzol.services.model.Members;
import com.melzol.services.model.WorkingDays;
import com.melzol.services.util.Utils;

@Repository
@Service("ActivitiesEventsService")
@Transactional(propagation=Propagation.SUPPORTS, readOnly=true)
public class ActivitiesEventsServiceImpl implements ActivitiesEventsService{
	
	private ActivitiesEventsDAO activitiesEventsDAO;
	private WorkingDaysDAO workingDaysDAO;
	private MembersDAO membersDAO;
	
	public ActivitiesEventsDAO getActivitiesEventsDAO() {
		return activitiesEventsDAO;
	}
	@Autowired
	public void setActivitiesEventsDAO(ActivitiesEventsDAO activitiesEventsDAO) {
		this.activitiesEventsDAO = activitiesEventsDAO;
	}

	public WorkingDaysDAO getWorkingDaysDAO() {
		return workingDaysDAO;
	}
	@Autowired
	public void setWorkingDaysDAO(WorkingDaysDAO workingDaysDAO) {
		this.workingDaysDAO = workingDaysDAO;
	}
	public MembersDAO getMembersDAO() {
		return membersDAO;
	}
	@Autowired
	public void setMembersDAO(MembersDAO membersDAO) {
		this.membersDAO = membersDAO;
	}
	public void addEvent(ActivitiesEvents activitiesevents) {
		activitiesEventsDAO.save(activitiesevents);
		
	}
	public ActivitiesEvents searchById(Integer id) {
		return activitiesEventsDAO.findById(id);
	}
	public List<ActivitiesEventsDTO> searchCityActivities(int memId, String city,Date curDate, int start) {

		return activitiesEventsDAO.searchCityActivities(memId,city,curDate,start);
	}
	public ActivitiesEventsDTO searchActivityEventView(int id, int memId,int type) {
		ActivitiesEventsDTO activitiesEventsDTO= activitiesEventsDAO.findActivityEventView(id,memId,type);
		List<Members>  eventMembers = membersDAO.findGroupMembers(id,2,0);
		List<WorkingDays> wrk=workingDaysDAO.findAll(id,type);
		for(WorkingDays w:wrk){
			w.setsDate(Utils.formatEventDate(w.getStartDate()));
			w.seteDate(Utils.formatEventDate(w.getEndDate()));
		}
		activitiesEventsDTO.setWrk(wrk);
		activitiesEventsDTO.setEventMembers(eventMembers);
		return activitiesEventsDTO;
	}
	public List<ActivitiesEventsDTO> searchNeighbourhoodActivities(Double lat, Double lon, int memId,Date curDate, int start) {
		return activitiesEventsDAO.findNeighbourhoodActivities(lat,lon,memId,curDate,start);
	}
	public List<ActivitiesEventsDTO> searchActivitiesByCat(int memId, String city, int refType, int cat, int start) {
		return activitiesEventsDAO.findActivitiesByCat(memId,city,refType,cat,start);
	}
	public List<ActivitiesEventsDTO> searchInCityActivities(int memId, String city, int refType, String title) {
		return activitiesEventsDAO.findInCityActivities(memId,city,refType,title);
	}
	public List<ActivitiesEventsDTO> searchInNeighbourhoodActivities(Double lat, Double lon, int memId, Date curDate,
			String title) {
		return activitiesEventsDAO.findInNeighbourhoodActivities(lat,lon,memId,curDate,title);
	}
	public List<ActivitiesEventsDTO> myActivities(int memId, int start) {
		return activitiesEventsDAO.findMyActivities(memId,start);
	}
	public void updateActivityEvent(ActivitiesEventsDTO activitiesEventsDTO) {
		activitiesEventsDAO.updateActivityEvent(activitiesEventsDTO);
	
		for(WorkingDays w:activitiesEventsDTO.getWrk()){
			w.setStartDate(Utils.parseDate(w.getsDate()));
			w.setEndDate(Utils.parseDate(w.geteDate()));
			w.setRefId(activitiesEventsDTO.getId());
			if(w.getId()==null){
			workingDaysDAO.save(w);
			}
			else{
				workingDaysDAO.updateWorikingDays(w);
			}
		}
		
	}
	public void deleteActivityEvents(ActivitiesEvents activitiesEvents) {
		activitiesEventsDAO.deleteActivityEvents(activitiesEvents);
		
	}

	public List<ActivitiesEventsDTO> searchInMyActivities(int memId, String title) {
		return activitiesEventsDAO.findInMyActivities(memId,title);
	}
	
	public List<ActivitiesEventsDTO> createdActivities(int memId,int refType, int start) {
		return activitiesEventsDAO.findCreatedActivities(memId,refType,start);
	}
	public List<ActivitiesEventsDTO> searchGroupEvents(int groupId,Date curDate, int start) {
		return activitiesEventsDAO.findGroupEvents(groupId,curDate,start);
	}

	public List<ActivitiesEventsDTO> searchGroupPastEvents(int groupId, Date curDate, int start) {
		return activitiesEventsDAO.findGroupPastEvents(groupId,curDate,start);
	}

}
