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

import com.melzol.services.beans.ForumDTO;
import com.melzol.services.model.Forum;
import com.melzol.services.service.ForumService;
import com.melzol.services.util.Utils;

@RestController
@RequestMapping("/forum")
public class ForumRestController {
	@Autowired
	ForumService forumService;
	
	//-------------------Create a Forum--------------------------------------------------------
		@RequestMapping(value ="/add" ,method = RequestMethod.POST)
		public ResponseEntity<Forum> createForum(@RequestBody Forum forum,UriComponentsBuilder ucBuilder){
			System.out.println("Creating forum x0001 " + forum.getTitle());
			forum.setStatus(1);
			forum.setCreatedTs(Utils.parseCreatedDate(forum.getfDate()));
			forum.setUpdatedTs(Utils.parseCreatedDate(forum.getfDate()));
			forumService.addForum(forum);
			Forum a=forumService.searchById(forum.getForumId());
			return new ResponseEntity<Forum>(a, HttpStatus.CREATED);
		}
		//-------------------Retrieve a Forum--------------------------------------------------------
			@RequestMapping(value ="/search/{id}" ,method = RequestMethod.GET,produces = MediaType.APPLICATION_JSON_VALUE)
			public ResponseEntity<Forum> searchForum(@PathVariable("id") int id){
				System.out.println("searching Forum x0001 " + id);
				Forum forum=forumService.searchById(id);
				if(forum==null){
					return new ResponseEntity<Forum>(HttpStatus.NOT_FOUND);
				}
				return new ResponseEntity<Forum>(forum, HttpStatus.OK);
			}
		//-------------------Retrieve a Forumview--------------------------------------------------------
			@RequestMapping(value ="/forumview/{id}" ,method = RequestMethod.GET,produces = MediaType.APPLICATION_JSON_VALUE)
			public ResponseEntity<ForumDTO> searchForumView(@PathVariable("id") int id){
				System.out.println("searching Forum x0001 " + id);
				ForumDTO forum=forumService.searchForumView(id);
				if(forum==null){
					return new ResponseEntity<ForumDTO>(HttpStatus.NOT_FOUND);
				}
				forum.setfDate(Utils.formatDateForum(forum.getCreatedTs()));
				return new ResponseEntity<ForumDTO>(forum, HttpStatus.OK);
			}
    //-------------------Retrieve All  Forum--------------------------------------------------------
			
			@RequestMapping(value = "/searchall/{lat}/{lon}/{memId}/{page}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
			public ResponseEntity<List<ForumDTO>> getAllForum(@PathVariable("lat") Double lat,@PathVariable("lon") Double lon,@PathVariable("memId") int memId,@PathVariable("page") int page) {
				System.out.println("Fetching all forum with X00001 memId " + memId);
				int start = (page-1)*10;
				//int end=page*10;
				List<ForumDTO> forum = forumService.searchAll(lat,lon,memId,start);
				if (forum == null) {
					System.out.println("forum with page " + page + " not found");
					return new ResponseEntity<List<ForumDTO>>(HttpStatus.NOT_FOUND);
				}
				String fdate;
				for(ForumDTO f:forum){
					fdate=Utils.formatDateForum(f.getCreatedTs());
					f.setfDate(fdate);
				}
				return new ResponseEntity<List<ForumDTO>>(forum, HttpStatus.OK);
			}
 //-------------------Retrieve By  title--------------------------------------------------------
			
			@RequestMapping(value = "/searchforum/{lat}/{lon}/{memId}/{title}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
			public ResponseEntity<List<ForumDTO>> searchForum(@PathVariable("lat") Double lat,@PathVariable("lon") Double lon,@PathVariable("memId") int memId,
					@PathVariable("title") String title) {
				System.out.println("Fetching all forum with X00001 memId " + memId);
				List<ForumDTO> forum = forumService.searchForum(lat,lon,memId,title);
				if (forum == null) {
					System.out.println("forum with title " + title + " not found");
					return new ResponseEntity<List<ForumDTO>>(HttpStatus.NOT_FOUND);
				}
				String fdate;
				for(ForumDTO f:forum){
					fdate=Utils.formatDateForum(f.getCreatedTs());
					f.setfDate(fdate);
				}
				return new ResponseEntity<List<ForumDTO>>(forum, HttpStatus.OK);
			}
 //-------------------Retrieve MY  Forum--------------------------------------------------------
			
			@RequestMapping(value = "/myforum/{memId}/{page}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
			public ResponseEntity<List<ForumDTO>> getMyForum(@PathVariable("memId") int memId,@PathVariable("page") int page) {
				System.out.println("Fetching all forum with X00001 memId " + memId);
				int start = (page-1)*10;
				//int end=page*10;
				List<ForumDTO> forum = forumService.searchMyForum(memId,start);
				if (forum == null) {
					System.out.println("forum with page " + page + " not found");
					return new ResponseEntity<List<ForumDTO>>(HttpStatus.NOT_FOUND);
				}
				String fdate;
				for(ForumDTO f:forum){
					fdate=Utils.formatDateForum(f.getCreatedTs());
					f.setfDate(fdate);
				}
				return new ResponseEntity<List<ForumDTO>>(forum, HttpStatus.OK);
			}
//------------------- Search In MY  Forum--------------------------------------------------------
			
			@RequestMapping(value = "/searchinmyforum/{memId}/{title}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
			public ResponseEntity<List<ForumDTO>> searchMyForum(@PathVariable("memId") int memId,@PathVariable("title") String title) {
				System.out.println("Fetching all forum with X00001 memId " + memId);
			
				List<ForumDTO> forum = forumService.searchInMyForum(memId,title);
				if (forum == null) {
					System.out.println("forum with page " + title + " not found");
					return new ResponseEntity<List<ForumDTO>>(HttpStatus.NOT_FOUND);
				}
				String fdate;
				for(ForumDTO f:forum){
					fdate=Utils.formatDateForum(f.getCreatedTs());
					f.setfDate(fdate);
				}
				return new ResponseEntity<List<ForumDTO>>(forum, HttpStatus.OK);
			}
	//-------------------Update Blogs--------------------------------------------------------
			
			@RequestMapping(value = "update/{id}", method = RequestMethod.PUT)
			public ResponseEntity<Forum> modify(@PathVariable("id") int id, @RequestBody Forum forum){
				System.out.println("updating event with X00001 id " + id);
				Forum fo = forumService.searchById(id);
				
				if (fo == null) {
					System.out.println("mem with id " + id + " not found");
					return new ResponseEntity<Forum>(HttpStatus.NOT_FOUND);
				}
				
				forumService.updateForum(forum);
				Forum f = forumService.searchById(forum.getForumId());
				return new ResponseEntity<Forum>(f , HttpStatus.OK);
			}
	//-------------------delete a Forum--------------------------------------------------------
			
			@RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
			public ResponseEntity<Forum> deleteForum(@PathVariable("id") int id) {
				System.out.println("deleting  Forum x0001 " + id);
				Forum forum=forumService.searchById(id);
				if (forum == null) {
					System.out.println("Unable to delete. event with id " + id + " not found");
					return new ResponseEntity<Forum>(HttpStatus.NOT_FOUND);
				}
				forumService.deleteForum(forum);
				return new ResponseEntity<Forum>(HttpStatus.NO_CONTENT);
			}

}
 