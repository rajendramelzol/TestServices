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

import com.melzol.services.beans.TopicsDTO;
import com.melzol.services.model.Topics;
import com.melzol.services.service.TopicsService;
import com.melzol.services.util.Utils;

@RestController
@RequestMapping("/topics")
public class TopicsRestController {
	
	@Autowired 
	TopicsService topicsService;
	
	//-------------------Create a topic--------------------------------------------------------
	
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public ResponseEntity<TopicsDTO> createComment(@RequestBody Topics topics,UriComponentsBuilder ucBuilder) {
		System.out.println("Creating Comments x0001 " + topics.getTitle());
		topics.setStatus(1);
		topics.setCreatedTs(new Date());
	
		topicsService.addTopic(topics);
		
		TopicsDTO a=topicsService.searchById(topics.getTopicId());
	
		a.setCreatedDate(Utils.formatDateComments(a.getCreatedTs()));
	
		return new ResponseEntity<TopicsDTO>(a, HttpStatus.CREATED);
	}
	//-------------------Topic view--------------------------------------------------------
	
		@RequestMapping(value = "/topicview/{topicId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
		public ResponseEntity<TopicsDTO> getTopicView(@PathVariable("topicId") int topicId) {
			System.out.println("Fetching all topics with X00001 topicId " + topicId);
			
			TopicsDTO topics = topicsService.searchTopicView(topicId);
			if (topics == null) {
				System.out.println("topics with topicId " + topicId + " not found");
				return new ResponseEntity<TopicsDTO>(HttpStatus.NOT_FOUND);
			}
			
			return new ResponseEntity<TopicsDTO>(topics, HttpStatus.OK);
		}

//-------------------Retrieve topics--------------------------------------------------------
	
	@RequestMapping(value = "/searchall/{refid}/{reftype}/{page}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<TopicsDTO>> getAllTopics(@PathVariable("refid") int refid,@PathVariable("reftype") int reftype,
			@PathVariable("page") int page) {
		System.out.println("Fetching all Comments with X00001 reftype " + reftype);
		int start = (page-1)*10;
		List<TopicsDTO> topics = topicsService.searchAll(refid,reftype,start);
		if (topics == null) {
			System.out.println("comments with reftype " + reftype + " not found");
			return new ResponseEntity<List<TopicsDTO>>(HttpStatus.NOT_FOUND);
		}
		for(TopicsDTO c:topics){
			c.setCreatedDate(Utils.formatDateComments(c.getCreatedTs()));
		}
		return new ResponseEntity<List<TopicsDTO>>(topics, HttpStatus.OK);
	}

}
