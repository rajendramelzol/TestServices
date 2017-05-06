package com.melzol.services.controller;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.melzol.services.beans.EventDTO;
import com.melzol.services.model.Event;
import com.melzol.services.model.EventMembers;
import com.melzol.services.service.EventMembersService;
import com.melzol.services.service.EventService;
import com.melzol.services.util.Utils;

@RestController
@RequestMapping("/event")
public class EventRestController {
	@Autowired 
	EventService eventService;
	@Autowired
	EventMembersService eventMembersService;
	
	//-------------------Create a Event--------------------------------------------------------
	
		@RequestMapping(value = "/add", method = RequestMethod.POST)
		public ResponseEntity<Event> createEvent(@RequestBody Event event, 	UriComponentsBuilder ucBuilder) {
			System.out.println("Creating event x0001 " + event.getEventName());
			event.setFromDate(Utils.parseDate(event.getfDate()));
			event.setMemberCount(1);
			event.setStatus(1);
			eventService.addEvent(event);
			Event a=eventService.searchById(event.getId());
			HttpHeaders headers = new HttpHeaders();
			headers.setLocation(ucBuilder.path("/event/{id}").buildAndExpand(event.getId()).toUri());
			return new ResponseEntity<Event>(a, HttpStatus.CREATED);
		}
	//-------------------Retrieve single Event--------------------------------------------------------
		
		@RequestMapping(value = "/search/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
		public ResponseEntity<Event> getEditEvent(@PathVariable("id") int id) {
			System.out.println("Fetching event with X00001 id " + id);
			Event event = eventService.searchById(id);
			if (event == null) {
				System.out.println("User with id " + id + " not found");
				return new ResponseEntity<Event>(HttpStatus.NOT_FOUND);
			}
			event.setfDate(Utils.formatDateTime(event.getFromDate()));
			
			return new ResponseEntity<Event>(event, HttpStatus.OK);
		}

	//-------------------Retrieve  Event View--------------------------------------------------------
		
			@RequestMapping(value = "/eventview/{id}/{memid}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
			public ResponseEntity<EventDTO> getEvent(@PathVariable("id") int id,@PathVariable("memid") int memid) {
				System.out.println("Fetching event view with X00001 id " + id);
				EventDTO eventDTOs=eventService.searchEventView(id,memid);
				if (eventDTOs== null) {
					System.out.println("User with id " + id + " not found");
					return new ResponseEntity<EventDTO>(HttpStatus.NOT_FOUND);
				}
				
				return new ResponseEntity<EventDTO>(eventDTOs, HttpStatus.OK);
			}
	//-------------------Retrieve All pincode Events--------------------------------------------------------
			
			@RequestMapping(value = "/searchall/{cat}/{pincode}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
			public ResponseEntity<List<Event>> getAllEvents(@PathVariable("cat") int cat,@PathVariable("pincode") int pincode) {
				System.out.println("Fetching all events with X00001 pincode " + pincode);
				List<Event> event = eventService.searchAllEvents(cat,pincode);
				if (event == null) {
					System.out.println("events with picode " + pincode + " not found");
					return new ResponseEntity<List<Event>>(HttpStatus.NOT_FOUND);
				}
				String fdate;
				for(Event e:event){
					fdate=Utils.formatDateTime(e.getFromDate());
					e.setfDate(fdate);
				}
				return new ResponseEntity<List<Event>>(event, HttpStatus.OK);
			}
	//-------------------Retrieve All City Events--------------------------------------------------------
			
			@RequestMapping(value = "/cityevents/{cat}/{city}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
			public ResponseEntity<List<Event>> getCityEvents(@PathVariable("cat") int cat,@PathVariable("city") String city) {
				System.out.println("Fetching all events with X00001 city " + city);
				List<Event> event = eventService.searchCityEvents(cat,city);
				if (event == null) {
					System.out.println("events with city " + city + " not found");
					return new ResponseEntity<List<Event>>(HttpStatus.NOT_FOUND);
				}
				String fdate;
				for(Event e:event){
					fdate=Utils.formatDateTime(e.getFromDate());
					e.setfDate(fdate);
				}
				return new ResponseEntity<List<Event>>(event, HttpStatus.OK);
			}
	//-------------------Update Event--------------------------------------------------------
			
			@RequestMapping(value = "update/{id}", method = RequestMethod.PUT)
			public ResponseEntity<Event> modify(@PathVariable("id") int id, @RequestBody Event event){
				System.out.println("updating event with X00001 id " + id);
				Event currentevent = eventService.searchById(id);
				if (currentevent == null) {
					System.out.println("User with id " + id + " not found");
					return new ResponseEntity<Event>(HttpStatus.NOT_FOUND);
				}
				eventService.modifyEvent(event);
				Event e=eventService.searchById(event.getId());
				return new ResponseEntity<Event>(e, HttpStatus.OK);
			}

	//-------------------Retrieve All MyEvents--------------------------------------------------------
			
			@RequestMapping(value = "/searchmyevents/{pincode}/{owner}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
			public ResponseEntity<List<Event>> getMyEvents(@PathVariable("pincode") int pincode,@PathVariable("owner") int owner) {
				System.out.println("Fetching event with X00001 id " + owner);
				List<Event> event = eventService.searchMyEvents(pincode,owner);
				if (event == null) {
					System.out.println("events with pincode " + owner + " not found"); 
					return new ResponseEntity<List<Event>>(HttpStatus.NOT_FOUND);
				}
				String fdate;
				for(Event e:event){
					fdate=Utils.formatDateTime(e.getFromDate());
					e.setfDate(fdate);
				}
				
				return new ResponseEntity<List<Event>>(event, HttpStatus.OK);
			}
			
	//-------------------Join a Event--------------------------------------------------------
			
			@RequestMapping(value = "/join", method = RequestMethod.POST)
			public ResponseEntity<EventMembers> joinEvent(@RequestBody EventMembers eventMembers, UriComponentsBuilder ucBuilder) {
				System.out.println("Joining in  event x0001 " + eventMembers.getMemberName());
				eventMembers.setUpdatedTs(new Date());
				eventMembersService.addEventMembers(eventMembers);
				System.out.println("event id in join"+eventMembers.getEventId());
				eventService.incrementCount(eventMembers.getEventId());
				return new ResponseEntity<EventMembers>(eventMembers, HttpStatus.CREATED);
			}
			
     //-------------------delete a Event--------------------------------------------------------
			
			@RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
			public ResponseEntity<Event> deleteEvent(@PathVariable("id") int id) {
				System.out.println("deleting  event x0001 " + id);
				Event event=eventService.searchById(id);
				if (event == null) {
					System.out.println("Unable to delete. event with id " + id + " not found");
					return new ResponseEntity<Event>(HttpStatus.NOT_FOUND);
				}
				eventService.deleteEvent(event);
				eventMembersService.deleteEventDetails(id);
				return new ResponseEntity<Event>(HttpStatus.NO_CONTENT);
			}
      //-------------------leave a Event--------------------------------------------------------
			
			@RequestMapping(value = "/leave/{eventid}/{memid}", method = RequestMethod.GET)
			public ResponseEntity<EventMembers> leaveEvent(@PathVariable("eventid") int eventid,@PathVariable("memid") int memid) {
				System.out.println("leaving the  event x0001 " + eventid);
				eventMembersService.deleteEventMembers(eventid,memid);
				eventService.decrementCount(eventid);
				return new ResponseEntity<EventMembers>(HttpStatus.NO_CONTENT);
			}
      //-------------------JOINED  EventS--------------------------------------------------------
			
			@RequestMapping(value = "/joinedevents/{pincode}/{memid}",method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
			public ResponseEntity<List<Event>> joinedEvent(@PathVariable("pincode") int pincode,@PathVariable("memid") int memid) {
				System.out.println("joined events  x0001 " + pincode+"and"+memid);
				List<Event> event=eventService.joinedEvents(pincode,memid);
				if (event == null) {
					System.out.println("events with piNcode " + pincode + " not found"); 
					return new ResponseEntity<List<Event>>(HttpStatus.NOT_FOUND);
				}
				String fdate;
				for(Event e:event){
					fdate=Utils.formatDateTime(e.getFromDate());
					e.setfDate(fdate);
				}
				return new ResponseEntity<List<Event>>(event,HttpStatus.OK);
			}
		

}
