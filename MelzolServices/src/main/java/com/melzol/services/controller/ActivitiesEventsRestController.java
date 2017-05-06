package com.melzol.services.controller;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.melzol.services.beans.ActivitiesEventsDTO;
import com.melzol.services.model.ActivitiesEvents;
import com.melzol.services.model.WorkingDays;
import com.melzol.services.service.ActivitiesEventsService;
import com.melzol.services.service.WorkingDaysService;
import com.melzol.services.util.Utils;

@RestController
@RequestMapping("/activitiesevents")
public class ActivitiesEventsRestController {
	@Autowired 
	ActivitiesEventsService activitiesEventsService;
	@Autowired
	WorkingDaysService workingDaysService;
	
	//-------------------Create a Activity/Event--------------------------------------------------------
	
			@RequestMapping(value = "/add", method = RequestMethod.POST)
			public ResponseEntity<ActivitiesEvents> create(@RequestBody ActivitiesEventsDTO activitiesEventsDTO,UriComponentsBuilder ucBuilder) {
				System.out.println("Creating activity/event x0001 " + activitiesEventsDTO.getTitle());
				ActivitiesEvents activitiesEvents = new ActivitiesEvents();
				activitiesEvents.setTitle(activitiesEventsDTO.getTitle());
				activitiesEvents.setDescription(activitiesEventsDTO.getDescription());
				activitiesEvents.setType(activitiesEventsDTO.getType());
				activitiesEvents.setSubType(activitiesEventsDTO.getSubType());
				activitiesEvents.setCategory(activitiesEventsDTO.getCategory());
				activitiesEvents.setLatitude(activitiesEventsDTO.getLatitude());
				activitiesEvents.setLongitude(activitiesEventsDTO.getLongitude());
				activitiesEvents.setActivityMode(activitiesEventsDTO.getActivityMode());
				activitiesEvents.setMobile(activitiesEventsDTO.getMobile());
				activitiesEvents.setAddress(activitiesEventsDTO.getAddress());
				activitiesEvents.setEmailId(activitiesEventsDTO.getEmailId());
				activitiesEvents.setWebsite(activitiesEventsDTO.getWebsite());
				activitiesEvents.setCreatedTs(Utils.parseCreatedDate(activitiesEventsDTO.getfDate()));
				activitiesEvents.setUpdatedTs(Utils.parseCreatedDate(activitiesEventsDTO.getfDate()));
				activitiesEvents.setCreatedBy(activitiesEventsDTO.getCreatedBy());
				activitiesEvents.setStatus(1);
				activitiesEvents.setAmount(activitiesEventsDTO.getAmount());
				activitiesEvents.setPaymentMode(activitiesEventsDTO.getPaymentMode());
				activitiesEvents.setGroupId(activitiesEventsDTO.getGroupId());
				activitiesEventsService.addEvent(activitiesEvents);
				
				for(WorkingDays w:activitiesEventsDTO.getWrk()){
				w.setStartDate(Utils.parseDate(w.getsDate()));
				w.setEndDate(Utils.parseDate(w.geteDate()));
				w.setRefId(activitiesEvents.getId());
				workingDaysService.addWorikingDays(w);
				}
				ActivitiesEvents a=activitiesEventsService.searchById(activitiesEvents.getId());
				
				return new ResponseEntity<ActivitiesEvents>(a, HttpStatus.CREATED);
			}
   //-------------------Retrieve City Activities --------------------------------------------------------
			
			@RequestMapping(value = "/cityactivities/{memId}/{city}/{date}/{page}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
			public ResponseEntity<List<ActivitiesEventsDTO>> getAllCityActivities(@PathVariable("memId") int memId,@PathVariable("city") String city,@PathVariable("date") String date,@PathVariable("page") int page) {
				System.out.println("Fetching all gossips with X00001 page " + page);
				int start = (page-1)*10;
				//int end=page*10;
				Date curDate=Utils.parseEventStartDate(date);
				List<ActivitiesEventsDTO> activities = activitiesEventsService.searchCityActivities(memId,city,curDate,start);
				if (activities == null) {
					System.out.println("gossips with page " + page + " not found");
					return new ResponseEntity<List<ActivitiesEventsDTO>>(HttpStatus.NOT_FOUND);
				}
				String fdate;
				for(ActivitiesEventsDTO a:activities){
					fdate=Utils.formatEventDate(a.getStartDate());
					a.setfDate(fdate);
				}
				return new ResponseEntity<List<ActivitiesEventsDTO>>(activities, HttpStatus.OK);
			}
    //-------------------Retrieve group events --------------------------------------------------------
			
			@RequestMapping(value = "/groupevents/{groupId}/{date}/{page}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
			public ResponseEntity<List<ActivitiesEventsDTO>> getGroupEvents(@PathVariable("groupId") int groupId,@PathVariable("date") String date,@PathVariable("page") int page) {
				System.out.println("Fetching all gossips with X00001 page " + page);
				int start = (page-1)*10;
				//int end=page*10;
				Date curDate=Utils.parseEventStartDate(date);
				List<ActivitiesEventsDTO> activities = activitiesEventsService.searchGroupEvents (groupId,curDate,start);
				if (activities == null) {
					System.out.println("gossips with page " + page + " not found");
					return new ResponseEntity<List<ActivitiesEventsDTO>>(HttpStatus.NOT_FOUND);
				}
				String fdate;
				for(ActivitiesEventsDTO a:activities){ 
					fdate=Utils.formatEventDate(a.getStartDate());
					a.setfDate(fdate);
				}
				return new ResponseEntity<List<ActivitiesEventsDTO>>(activities, HttpStatus.OK);
			}
 //-------------------Retrieve group past events --------------------------------------------------------
			
			@RequestMapping(value = "/grouppastevents/{groupId}/{date}/{page}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
			public ResponseEntity<List<ActivitiesEventsDTO>> getGroupPastEvents(@PathVariable("groupId") int groupId,@PathVariable("date") String date,@PathVariable("page") int page) {
				System.out.println("Fetching all gossips with X00001 page " + page);
				int start = (page-1)*10;
				//int end=page*10;
				Date curDate=Utils.parseEventStartDate(date);
				List<ActivitiesEventsDTO> activities = activitiesEventsService.searchGroupPastEvents (groupId,curDate,start);
				if (activities == null) {
					System.out.println("gossips with page " + page + " not found");
					return new ResponseEntity<List<ActivitiesEventsDTO>>(HttpStatus.NOT_FOUND);
				}
				String fdate;
				for(ActivitiesEventsDTO a:activities){ 
					fdate=Utils.formatEventDate(a.getStartDate());
					a.setfDate(fdate);
				}
				return new ResponseEntity<List<ActivitiesEventsDTO>>(activities, HttpStatus.OK);
			}
	//-------------------Retrieve  Activity/Event View--------------------------------------------------------
			
			@RequestMapping(value = "/activityeventview/{id}/{memid}/{refType}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
			public ResponseEntity<ActivitiesEventsDTO> getEvent(@PathVariable("id") int id,@PathVariable("memid") int memid,@PathVariable("refType") int refType) {
				System.out.println("Fetching event view with X00001 id " + id);
				ActivitiesEventsDTO activities=activitiesEventsService.searchActivityEventView(id,memid,refType);
				if (activities== null) {
					System.out.println("User with id " + id + " not found");
					return new ResponseEntity<ActivitiesEventsDTO>(HttpStatus.NOT_FOUND);
				}
			
				return new ResponseEntity<ActivitiesEventsDTO>(activities, HttpStatus.OK);
			}
   //-------------------Retrieve Neighbourhood Activities & Events --------------------------------------------------------
			
			@RequestMapping(value = "/neighbourhoodactivities/{lat}/{lon}/{memId}/{date}/{page}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
			public ResponseEntity<List<ActivitiesEventsDTO>> getNeighbourhoodActivities(@PathVariable("lat") Double lat,@PathVariable("lon") Double lon,
					@PathVariable("memId") int memId,@PathVariable("date") String date,@PathVariable("page") int page) {
				System.out.println("Fetching all gossips with X00001 page " + page);
				int start = (page-1)*10;
				Date curDate=Utils.parseEventStartDate(date);
				//int end=page*10;
				List<ActivitiesEventsDTO> activities = activitiesEventsService.searchNeighbourhoodActivities(lat,lon,memId,curDate,start);
				if (activities == null) {
					System.out.println("gossips with page " + page + " not found");
					return new ResponseEntity<List<ActivitiesEventsDTO>>(HttpStatus.NOT_FOUND);
				}
				String fdate;
				for(ActivitiesEventsDTO a:activities){
						fdate=Utils.formatEventDate(a.getStartDate());
					a.setfDate(fdate);
				}
				return new ResponseEntity<List<ActivitiesEventsDTO>>(activities, HttpStatus.OK);
			}
//-------------------Retrieve Activities By Category --------------------------------------------------------
			
			@RequestMapping(value = "/activitiesbycat/{memId}/{city}/{refType}/{cat}/{page}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
			public ResponseEntity<List<ActivitiesEventsDTO>> getAllActivitiesByCat(@PathVariable("memId") int memId,@PathVariable("city") String city,@PathVariable("refType") int refType,
					@PathVariable("cat") int cat,@PathVariable("page") int page) {
				System.out.println("Fetching all gossips with X00001 page " + page);
				int start = (page-1)*10;
				//int end=page*10;
				List<ActivitiesEventsDTO> activities = activitiesEventsService.searchActivitiesByCat(memId,city,refType,cat,start);
				if (activities == null) {
					System.out.println("gossips with page " + page + " not found");
					return new ResponseEntity<List<ActivitiesEventsDTO>>(HttpStatus.NOT_FOUND);
				}
				return new ResponseEntity<List<ActivitiesEventsDTO>>(activities, HttpStatus.OK);
			}
    //-------------------Search in City Level Activities--------------------------------------------------------
			
			@RequestMapping(value = "/searchincityactivities/{memId}/{city}/{refType}/{title}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
			public ResponseEntity<List<ActivitiesEventsDTO>> searchInCityActivities(@PathVariable("memId") int memId,@PathVariable("city") String city,@PathVariable("refType") int refType,
					@PathVariable("title")  String title) {
				System.out.println("Fetching all ActivitiesEvents with X00001 title " + title);
				List<ActivitiesEventsDTO> activities = activitiesEventsService.searchInCityActivities(memId,city,refType,title);
				if (activities == null) {
					System.out.println("ActivitiesEvents with page " + title + " not found");
					return new ResponseEntity<List<ActivitiesEventsDTO>>(HttpStatus.NOT_FOUND);
				}
				return new ResponseEntity<List<ActivitiesEventsDTO>>(activities, HttpStatus.OK);
			}
   //-------------------Search in Neighbourhood Activities & Events --------------------------------------------------------
			
			@RequestMapping(value = "/searchinneighbourhoodactivities/{lat}/{lon}/{memId}/{date}/{title}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
			public ResponseEntity<List<ActivitiesEventsDTO>> getSearchInNeighbourhoodActivities(@PathVariable("lat") Double lat,@PathVariable("lon") Double lon,
					@PathVariable("memId") int memId,@PathVariable("date") String date,@PathVariable("title") String title) {
				System.out.println("Fetching all ActivitiesEvents with X00001 title " + title);
				Date curDate=Utils.parseCreatedDate(date);
				//int end=page*10;
				List<ActivitiesEventsDTO> activities = activitiesEventsService.searchInNeighbourhoodActivities(lat,lon,memId,curDate,title);
				if (activities == null) {
					System.out.println("ActivitiesEvents with page " + title + " not found");
					return new ResponseEntity<List<ActivitiesEventsDTO>>(HttpStatus.NOT_FOUND);
				}
				String fdate;
				for(ActivitiesEventsDTO a:activities){
					if(a.getSubType()==1){
					fdate=Utils.formatDateForum(a.getCreatedTs());
					}else{
						fdate=Utils.formatDateForum(a.getStartDate());
					}
					a.setfDate(fdate);
				}
				return new ResponseEntity<List<ActivitiesEventsDTO>>(activities, HttpStatus.OK);
			}
//-------------------Retrieve My Activities & Events --------------------------------------------------------
			
			@RequestMapping(value = "/myactivities/{memId}/{page}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
			public ResponseEntity<List<ActivitiesEventsDTO>> getMyActivities(@PathVariable("memId") int memId,@PathVariable("page") int page) {
				System.out.println("Fetching all gossips with X00001 page " + page);
				int start = (page-1)*10;
				List<ActivitiesEventsDTO> activities = activitiesEventsService.myActivities(memId,start);
				if (activities == null) {
					System.out.println("gossips with page " + page + " not found");
					return new ResponseEntity<List<ActivitiesEventsDTO>>(HttpStatus.NOT_FOUND);
				}
				String fdate;
				for(ActivitiesEventsDTO a:activities){
						fdate=Utils.formatEventDate(a.getStartDate());
					a.setfDate(fdate);
				}
				return new ResponseEntity<List<ActivitiesEventsDTO>>(activities, HttpStatus.OK);
			}
    //-------------------Update Activity/Event--------------------------------------------------------
			
			@RequestMapping(value = "update/{id}", method = RequestMethod.PUT)
			public ResponseEntity<ActivitiesEventsDTO> modify(@PathVariable("id") int id,@RequestBody ActivitiesEventsDTO activitiesEventsDTO){
				System.out.println("updating event with X00001 id " + id);
				ActivitiesEvents actevt = activitiesEventsService.searchById(id);
				
				if (actevt == null) {
					System.out.println("mem with id " + id + " not found");
					return new ResponseEntity<ActivitiesEventsDTO>(HttpStatus.NOT_FOUND);
				}
				
				activitiesEventsService.updateActivityEvent(activitiesEventsDTO);
				ActivitiesEventsDTO act = activitiesEventsService.searchActivityEventView(activitiesEventsDTO.getId(),activitiesEventsDTO.getCreatedBy(),activitiesEventsDTO.getSubType());
				return new ResponseEntity<ActivitiesEventsDTO>(act , HttpStatus.OK);
			}
		//-------------------delete a Activities/Events--------------------------------------------------------
			
			@RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
			public ResponseEntity<ActivitiesEvents> deleteBlogs(@PathVariable("id") int id) {
				System.out.println("deleting  Blogs x0001 " + id);
				ActivitiesEvents activitiesEvents=activitiesEventsService.searchById(id);
				if (activitiesEvents == null) {
					System.out.println("Unable to delete. ActivitiesEvents with id " + id + " not found");
					return new ResponseEntity<ActivitiesEvents>(HttpStatus.NOT_FOUND);
				}
				activitiesEventsService.deleteActivityEvents(activitiesEvents);
				return new ResponseEntity<ActivitiesEvents>(HttpStatus.NO_CONTENT);
			}
//-------------------Retrieve My Activities & Events --------------------------------------------------------
			
			@RequestMapping(value = "/searchinmyactivities/{memId}/{title}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
			public ResponseEntity<List<ActivitiesEventsDTO>> searchInMyActivities(@PathVariable("memId") int memId,@PathVariable("title") String title) {
				System.out.println("Fetching all ActivitiesEventsDTO with X00001 title " + title);
				
				List<ActivitiesEventsDTO> activities = activitiesEventsService.searchInMyActivities(memId,title);
				if (activities == null) {
					System.out.println("gossips with title " + title + " not found");
					return new ResponseEntity<List<ActivitiesEventsDTO>>(HttpStatus.NOT_FOUND);
				}
				String fdate;
				for(ActivitiesEventsDTO a:activities){
					if(a.getSubType()==1){
					fdate=Utils.formatDateForum(a.getCreatedTs());
					}else{
						fdate=Utils.formatDateForum(a.getStartDate());
					}
					a.setfDate(fdate);
				}
				return new ResponseEntity<List<ActivitiesEventsDTO>>(activities, HttpStatus.OK);
			}
//-----------------Retrieve only my activities or my events--------------------------------------------------

			@RequestMapping(value = "/createdactivities/{memId}/{refType}/{page}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
			public ResponseEntity<List<ActivitiesEventsDTO>> getCreatedActivities(@PathVariable("memId") int memId,@PathVariable("refType") int refType,@PathVariable("page") int page) {
				System.out.println("Fetching all gossips with X00001 page " + page);
				int start = (page-1)*10;
				List<ActivitiesEventsDTO> activities = activitiesEventsService.createdActivities(memId,refType,start);
				if (activities == null) {
					System.out.println("activities with page " + page + " not found");
					return new ResponseEntity<List<ActivitiesEventsDTO>>(HttpStatus.NOT_FOUND);
				}
				/*String fdate;
				for(ActivitiesEventsDTO a:activities){
					if(a.getSubType()==1){
					fdate=Utils.formatDateForum(a.getCreatedTs());
					}else{
						fdate=Utils.formatDateForum(a.getStartDate());
					}
					a.setfDate(fdate);
				}*/
				return new ResponseEntity<List<ActivitiesEventsDTO>>(activities, HttpStatus.OK);
			}

}
