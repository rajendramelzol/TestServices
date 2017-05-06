package com.melzol.services.controller;

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

import com.melzol.services.beans.NotificationDTO;
import com.melzol.services.beans.TagRequest;
import com.melzol.services.beans.TaggingDTO;
import com.melzol.services.model.Notification;
import com.melzol.services.model.Tagging;
import com.melzol.services.service.MembersService;
import com.melzol.services.service.NotificationService;
import com.melzol.services.service.TaggingService;
import com.melzol.services.util.Utils;

@RestController
@RequestMapping("/tagging")
public class TaggingRestController {
	@Autowired
	MembersService membersService;
	@Autowired
	TaggingService taggingService;
	@Autowired
	NotificationService notificationService;
	
	
	
	//-------------------Tag a Member--------------------------------------------------------
		@RequestMapping(value ="/add" ,method = RequestMethod.POST)
		public ResponseEntity<Tagging> tagMember(@RequestBody TaggingDTO tagging,UriComponentsBuilder ucBuilder){
			//tagging.setStatus(1);
			//tagging.setCreatedTs(Utils.parseCreatedDate(tagging.getfDate()));
			for(NotificationDTO n:tagging.getNotification()){
				Tagging t=new Tagging();
				t.setMelzolId(n.getSenderId());  
				t.setTagMelzolId(n.getReceiverId());
				t.setStatus(n.getStatus());
				t.setCreatedTs(Utils.parseCreatedDate(n.getcDate()));
				t.setUpdatedTs(Utils.parseCreatedDate(n.getuDate()));
				taggingService.addTagging(t);
				Notification not=new Notification();
				not.setSenderId(n.getSenderId());
				not.setReceiverId(n.getReceiverId());
				not.setMessage(n.getMessage());
				not.setType(n.getType());
				not.setStatus(2);
				not.setCreatedTs(Utils.parseCreatedDate(n.getcDate()));
				notificationService.addNotification(not);
				
			}
			
			return new ResponseEntity<Tagging>(HttpStatus.CREATED);
		}
	//-------------------Retrieve a tagged members--------------------------------------------------------
			@RequestMapping(value ="/taggedmembers/{lat}/{lon}/{memId}" ,method = RequestMethod.GET,produces = MediaType.APPLICATION_JSON_VALUE)
			public ResponseEntity<TagRequest> searchTaggedMembers(@PathVariable("lat") Double lat,
					@PathVariable("lon") Double lon,@PathVariable("memId") int memId){
				System.out.println("searching tagged members x0001 " +memId);
				TagRequest mem=membersService.searchTaggedMembers(lat,lon,memId);
				if(mem==null){
					return new ResponseEntity<TagRequest>(HttpStatus.NOT_FOUND);
				}
				
				return new ResponseEntity<TagRequest>(mem, HttpStatus.OK);
			}
	//-----------------------update tagging request------------------------------------------------------------

			@RequestMapping(value ="/updatetagrequest/{senderId}/{receiverId}/{status}" ,method = RequestMethod.GET,produces = MediaType.APPLICATION_JSON_VALUE)
			public ResponseEntity<Tagging> searchUpdateRequest(@PathVariable("senderId") int senderId,@PathVariable("receiverId") int receiverId,@PathVariable("status") int status){
				System.out.println("searching tagged members x0001 " +senderId);
				taggingService.searchUpdateRequest(senderId,receiverId,status);
				return new ResponseEntity<Tagging>(HttpStatus.OK);
			}
			
}
