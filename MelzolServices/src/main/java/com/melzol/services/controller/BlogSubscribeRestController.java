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

import com.melzol.services.beans.MemberDTO;
import com.melzol.services.model.BlogSubscribe;
import com.melzol.services.model.Members;
import com.melzol.services.service.BlogSubscribeService;

@RestController
@RequestMapping("/blogsubscribe")
public class BlogSubscribeRestController {
	
	@Autowired
	BlogSubscribeService blogSubscribeService;
	
	
	//-------------------subscribe  blogs--------------------------------------------------------
	@RequestMapping(value ="/subscribe" ,method = RequestMethod.POST)
	public ResponseEntity<BlogSubscribe> createGossips(@RequestBody BlogSubscribe blogSubscribe,UriComponentsBuilder ucBuilder){
		System.out.println("subscribing a blog x0001 " + blogSubscribe.getSubscribeId());
		blogSubscribe.setSubscribeTs(new Date());
		blogSubscribeService.subscribe(blogSubscribe);
		
		return new ResponseEntity<BlogSubscribe>(HttpStatus.CREATED);
	}
	//-------------------Retrieve Subscribers--------------------------------------------------------
	
	@RequestMapping(value = "/subscribers/{memId}/{page}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<MemberDTO>> getAllSubscribers(@PathVariable("memId") int memId,@PathVariable("page") int page) {
		System.out.println("Fetching all members with X00001 lat and lon " +memId);
		int start = (page-1)*10;
		List<MemberDTO> members = blogSubscribeService.searchSubscribers(memId,start);
		
		if (members == null) {
			System.out.println("members with pincode " +memId+" not found");
			return new ResponseEntity<List<MemberDTO>>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<List<MemberDTO>>(members, HttpStatus.OK);
	}
	
	//-------------------Retrieve my subscribers--------------------------------------------------------
	
		@RequestMapping(value = "/mysubscribers/{memId}/{page}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
		public ResponseEntity<List<Members>> getAllMySubscribers(@PathVariable("memId") int memId,@PathVariable("page") int page) {
			System.out.println("Fetching all members with X00001 lat and lon " +memId);
			int start = (page-1)*10;
			List<Members> members = blogSubscribeService.searchMySubscribers(memId,start);
			
			if (members == null) {
				System.out.println("members with pincode " +memId+" not found");
				return new ResponseEntity<List<Members>>(HttpStatus.NOT_FOUND);
			}
			return new ResponseEntity<List<Members>>(members, HttpStatus.OK);
		}

}
