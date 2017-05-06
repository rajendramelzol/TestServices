package com.melzol.services.controller;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.melzol.services.beans.ActivitiesEventsDTO;
import com.melzol.services.model.Blogs;
import com.melzol.services.model.Forum;
import com.melzol.services.model.Gossips;
import com.melzol.services.model.JoiningsOpinions;
import com.melzol.services.service.ActivitiesEventsService;
import com.melzol.services.service.BlogsService;
import com.melzol.services.service.ForumService;
import com.melzol.services.service.GossipsService;
import com.melzol.services.service.JoiningsOpinionsService;
import com.melzol.services.util.Utils;

@RestController
@RequestMapping("/joinings")
public class JoiningsOpinionsController {
	@Autowired
	JoiningsOpinionsService joiningsOpinionsService;
	@Autowired
	GossipsService gossipsService;
	@Autowired
	BlogsService blogsService;
	@Autowired
	ForumService forumService;
	@Autowired
	ActivitiesEventsService activitiesEventsService;
	
	
	//-------------------Join --------------------------------------------------------
	
	@RequestMapping(value = "/join", method = RequestMethod.POST)
	public ResponseEntity<JoiningsOpinions> joinGossip(@RequestBody JoiningsOpinions joiningsOpinions, UriComponentsBuilder ucBuilder) {
		System.out.println("Joining in  gossip x0001 " + joiningsOpinions.getRefId());
		joiningsOpinions.setJoinDate(new Date());
		joiningsOpinionsService.addJoiningsOpinions(joiningsOpinions);
		//gossipsService.incrementCount(gossipMembers.getGossipId());
		return new ResponseEntity<JoiningsOpinions>(joiningsOpinions, HttpStatus.CREATED);
	}
	
	//-------------------opinion of member--------------------------------------------------------
	
	@RequestMapping(value = "/opinion", method = RequestMethod.POST)
	public ResponseEntity<JoiningsOpinions> opinion(@RequestBody JoiningsOpinions joiningsOpinions, UriComponentsBuilder ucBuilder) {
		System.out.println("opinion on x0001 " + joiningsOpinions.getRefId());
		joiningsOpinions.setJoinDate(new Date());
		joiningsOpinionsService.opinionOnBlog(joiningsOpinions);
		if(joiningsOpinions.getOpinion()==1){
			int refType=joiningsOpinions.getRefType();
			if(refType==3){
				Gossips g=new Gossips();
				g.setGossipId(joiningsOpinions.getRefId());
				g.setUpdatedTs(Utils.parseCreatedDate(joiningsOpinions.getuDate()));
				gossipsService.updateGossips(g);
			}else if(refType==4){
				Blogs b = new Blogs();
				b.setBlogId(joiningsOpinions.getRefId());
				b.setUpdatedTs(Utils.parseCreatedDate(joiningsOpinions.getuDate()));
				blogsService.updateBlogs(b);
			}else if(refType==5){
				Forum f= new Forum();
				f.setForumId(joiningsOpinions.getRefId());
				f.setUpdatedTs(Utils.parseCreatedDate(joiningsOpinions.getuDate()));
				forumService.updateForum(f);
			}else{
				ActivitiesEventsDTO a= new ActivitiesEventsDTO();
				a.setId(joiningsOpinions.getRefId());
				a.setUpdatedTs(Utils.parseCreatedDate(joiningsOpinions.getuDate()));
				activitiesEventsService.updateActivityEvent(a);
			}
		}
		//gossipsService.incrementCount(gossipMembers.getGossipId());
		return new ResponseEntity<JoiningsOpinions>(joiningsOpinions, HttpStatus.CREATED);
	}

}
