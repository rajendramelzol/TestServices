package com.melzol.services.controller;

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

import com.melzol.services.model.Chatting;
import com.melzol.services.service.ChattingService;
import com.melzol.services.util.Utils;

@RestController
@RequestMapping("/chatting")
public class ChattingRestController {
	@Autowired
	ChattingService chattingService;
	
	
	//-------------------save a Chatting--------------------------------------------------------
	
			@RequestMapping(value = "/add", method = RequestMethod.POST)
			public ResponseEntity<Chatting> createNotification(@RequestBody Chatting chatting, 	UriComponentsBuilder ucBuilder) {
				System.out.println("Creating Notification x0001 " + chatting.getReceiverId());
				chatting.setStatus(2);
				chatting.setCreatedTs(Utils.parseCreatedDate(chatting.getcDate()));
				chattingService.saveChatting(chatting);
				//Notification a=notificationService.searchById(notification.getId());
				return new ResponseEntity<Chatting>(chatting, HttpStatus.CREATED);
			}
			
	//-------------------Retrieve All member Notification--------------------------------------------------------
			
			@RequestMapping(value = "/searchall/{memId}/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
			public ResponseEntity<List<Chatting>> getAllChatting(@PathVariable("memId") int memId,@PathVariable("id") int id) {
				System.out.println("Fetching all Notification with X00001 memid" + id);
				//int start = (page-1)*10;
				List<Chatting> chatting = chattingService.searchAllChatting(memId,id);
				if (chatting== null) {
					System.out.println("Notification with memid " + memId + " not found");
					return new ResponseEntity<List<Chatting>>(HttpStatus.NOT_FOUND);
				}
				for(Chatting c:chatting){
					c.setcDate(Utils.formatDateBlogs(c.getCreatedTs()));
				
				}
				return new ResponseEntity<List<Chatting>>(chatting, HttpStatus.OK);
			}

}
