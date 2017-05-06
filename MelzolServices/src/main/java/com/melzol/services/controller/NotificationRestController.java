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

import com.melzol.services.beans.NotificationDTO;
import com.melzol.services.model.Notification;
import com.melzol.services.service.NotificationService;
import com.melzol.services.util.Utils;

@RestController
@RequestMapping("/notification")
public class NotificationRestController {
	@Autowired
	NotificationService notificationService;
	
	
	//-------------------Create a Notification--------------------------------------------------------
	
			@RequestMapping(value = "/add", method = RequestMethod.POST)
			public ResponseEntity<Notification> createNotification(@RequestBody Notification notification, 	UriComponentsBuilder ucBuilder) {
				System.out.println("Creating Notification x0001 " + notification.getReceiverId());
				notification.setStatus(2);
				notification.setCreatedTs(Utils.parseCreatedDate(notification.getcDate()));
				notificationService.addNotification(notification);
				//Notification a=notificationService.searchById(notification.getId());
				return new ResponseEntity<Notification>(notification, HttpStatus.CREATED);
			}
			
	//-------------------Retrieve All member Notification--------------------------------------------------------
			
			@RequestMapping(value = "/searchall/{memid}/{page}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
			public ResponseEntity<List<NotificationDTO>> getAllNotification(@PathVariable("memid") int memid,@PathVariable("page") int page) {
				System.out.println("Fetching all Notification with X00001 memid" + memid);
				int start = (page-1)*10;
				List<NotificationDTO> notification = notificationService.searchAllNotification(memid,start);
				if (notification== null) {
					System.out.println("Notification with memid " + memid + " not found");
					return new ResponseEntity<List<NotificationDTO>>(HttpStatus.NOT_FOUND);
				}
				return new ResponseEntity<List<NotificationDTO>>(notification, HttpStatus.OK);
			}
			
    //-------------------Retrieve All member Notification--------------------------------------------------------
			
			@RequestMapping(value = "/notificationcount/{memId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
			public ResponseEntity<Long> getNotificationCount(@PathVariable("memId") int memId) {
				System.out.println("Fetching all Notificationcount with X00001 memid" + memId);
			
				Long notification = notificationService.searchNotificationCount(memId);
				if (notification== null) {
					System.out.println("Notification with memid " + memId + " not found");
					return new ResponseEntity<Long>(HttpStatus.NOT_FOUND);
				}
				return new ResponseEntity<Long>(notification, HttpStatus.OK);
			}
		
		//-------------------reject a notification--------------------------------------------------------
			
			@RequestMapping(value = "/changestatus/{memId}", method = RequestMethod.GET)
			public void changeNotificationStatus(@PathVariable("memId") int memId) {
				notificationService.changeStatus(memId);
			}

}
