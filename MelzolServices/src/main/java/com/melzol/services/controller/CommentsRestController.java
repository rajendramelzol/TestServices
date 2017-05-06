package com.melzol.services.controller;

import java.util.Date;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
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
import com.melzol.services.beans.CommentsDTO;
import com.melzol.services.model.Blogs;
import com.melzol.services.model.Comments;
import com.melzol.services.model.Forum;
import com.melzol.services.model.Gossips;
import com.melzol.services.service.ActivitiesEventsService;
import com.melzol.services.service.BlogsService;
import com.melzol.services.service.CommentsService;
import com.melzol.services.service.ForumService;
import com.melzol.services.service.GossipsService;
import com.melzol.services.util.Utils;
@RestController
@RequestMapping("/comments")
public class CommentsRestController {
	@Autowired 
	CommentsService commentsService;
	@Autowired
	GossipsService gossipsService;
	@Autowired
	BlogsService blogsService;
	@Autowired
	ForumService forumService;
	@Autowired
	ActivitiesEventsService activitiesEventsService;
	
	private static final Log log = LogFactory.getLog(CommentsRestController.class);
	//-------------------Create a Comments--------------------------------------------------------
	
			@RequestMapping(value = "/add", method = RequestMethod.POST)
			public void createComment(@RequestBody Comments comments, 	UriComponentsBuilder ucBuilder) {
				System.out.println("Creating Comments x0001 " + comments.getComment());
				comments.setCreatedTs(Utils.parseCreatedDate(comments.getcDate()));
				commentsService.addEvent(comments);
				int refType=comments.getRefType();
				if(refType==3){
					Gossips g=new Gossips();
					g.setGossipId(comments.getRefId());
					g.setUpdatedTs(Utils.parseCreatedDate(comments.getcDate()));
					gossipsService.updateGossips(g);
				}else if(refType==4){
					Blogs b = new Blogs();
					b.setBlogId(comments.getRefId());
					b.setUpdatedTs(Utils.parseCreatedDate(comments.getcDate()));
					blogsService.updateBlogs(b);
				}else if(refType==5){
					Forum f= new Forum();
					f.setForumId(comments.getRefId());
					f.setUpdatedTs(Utils.parseCreatedDate(comments.getcDate()));
					forumService.updateForum(f);
				}else{
					ActivitiesEventsDTO a= new ActivitiesEventsDTO();
					a.setId(comments.getRefId());
					a.setUpdatedTs(Utils.parseCreatedDate(comments.getcDate()));
					activitiesEventsService.updateActivityEvent(a);
				}
				//Comments a=commentsService.searchById(comments.getCreatedTs());
				 
				//return new ResponseEntity<Comments>(a, HttpStatus.CREATED);
			}
	//-------------------Retrieve comments--------------------------------------------------------
			
			@RequestMapping(value = "/searchall/{refid}/{reftype}/{page}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
			public ResponseEntity<List<CommentsDTO>> getAllComments(@PathVariable("refid") int refid,@PathVariable("reftype") int reftype,@PathVariable("page") int page) {
				System.out.println("Fetching all Comments with X00001 reftype " + reftype);
				int start = (page-1)*10;
				List<CommentsDTO> comments = commentsService.searchAll(refid,reftype,start);
				if (comments == null) {
					System.out.println("comments with reftype " + reftype + " not found");
					return new ResponseEntity<List<CommentsDTO>>(HttpStatus.NOT_FOUND);
				}
				for(CommentsDTO c:comments){
					c.setcDate(Utils.formatDateComments(c.getCreatedTs()));
					c.setCreatedDate(Utils.parseCommentDate(c.getCreatedTs()));
				}
				return new ResponseEntity<List<CommentsDTO>>(comments, HttpStatus.OK);
			}
		//-------------------delete a comments--------------------------------------------------------
			
			@RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
			public ResponseEntity<Comments> deleteBlogs(@PathVariable("id") String id) {
				log.debug("   comments x0001 " + id);
				 Date date = Utils.parseEventStartDate(id);
				Comments comments=commentsService.searchById(date);
				if (comments == null) {
					System.out.println("Unable to delete. event with id " + id + " not found");
					return new ResponseEntity<Comments>(HttpStatus.NOT_FOUND);
				}
				commentsService.deleteComments(comments);
				return new ResponseEntity<Comments>(HttpStatus.NO_CONTENT);
			}
}
