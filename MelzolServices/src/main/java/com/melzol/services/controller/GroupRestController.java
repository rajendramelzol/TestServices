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

import com.melzol.services.beans.GroupDTO;
import com.melzol.services.model.Group;
import com.melzol.services.service.GroupService;
import com.melzol.services.util.Utils;

@RestController
@RequestMapping("/group")
public class GroupRestController {
	@Autowired
	GroupService groupService;
	
	//-------------------Create a Group--------------------------------------------------------
	
			@RequestMapping(value = "/add", method = RequestMethod.POST)
			public ResponseEntity<Group> createGroup(@RequestBody Group group, 	UriComponentsBuilder ucBuilder) {
				System.out.println("Creating group x0001 " +group.getTitle());
				group.setStatus(1);
				group.setCreatedTs(Utils.parseCreatedDate(group.getfDate()));
				group.setUpdatedTs(Utils.parseCreatedDate(group.getfDate()));	
				groupService.addGroup(group);
				Group g=groupService.searchById(group.getGroupId());
				return new ResponseEntity<Group>(g, HttpStatus.CREATED);
			}
	//-------------------Retrieve Groups By Category --------------------------------------------------------
			
			@RequestMapping(value = "/groupsbycat/{city}/{cat}/{page}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
			public ResponseEntity<List<GroupDTO>> getAllActivitiesByCat(@PathVariable("city") String city,
					@PathVariable("cat") int cat,@PathVariable("page") int page) {
				System.out.println("Fetching all groups with X00001 page " + page);
				int start = (page-1)*10;
				//int end=page*10;
				List<GroupDTO> groups = groupService.searchGroupsByCat(city,cat,start);
				if (groups == null) {
					System.out.println("groups with page " + page + " not found");
					return new ResponseEntity<List<GroupDTO>>(HttpStatus.NOT_FOUND);
				}
				return new ResponseEntity<List<GroupDTO>>(groups, HttpStatus.OK);
			}
     //-------------------Retrieve My Groups --------------------------------------------------------
			
			@RequestMapping(value = "/mygroups/{memId}/{page}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
			public ResponseEntity<List<GroupDTO>> getMyGroups(@PathVariable("memId") int memId,@PathVariable("page") int page) {
				System.out.println("Fetching all groups with X00001 page " + page);
				int start = (page-1)*10;
				//int end=page*10;
				List<GroupDTO> groups = groupService.searchMyGroups(memId,start);
				if (groups == null) {
					System.out.println("groups with page " + page + " not found");
					return new ResponseEntity<List<GroupDTO>>(HttpStatus.NOT_FOUND);
				}
				return new ResponseEntity<List<GroupDTO>>(groups, HttpStatus.OK);
			}
		//-------------------Retrieve single Group--------------------------------------------------------
			
			@RequestMapping(value = "/search/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
			public ResponseEntity<Group> getEditGroup(@PathVariable("id") int id) {
				System.out.println("Fetching group with X00001 id " + id);
				Group group = groupService.searchById(id);
				if (group == null) {
					System.out.println("User with id " + id + " not found");
					return new ResponseEntity<Group>(HttpStatus.NOT_FOUND);
				}
				
				return new ResponseEntity<Group>(group, HttpStatus.OK);
			}
     
	//-------------------Retrieve  Event View--------------------------------------------------------
			
			@RequestMapping(value = "/groupview/{id}/{memid}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
			public ResponseEntity<GroupDTO> getGroup(@PathVariable("id") int id,@PathVariable("memid") int memid) {
				System.out.println("Fetching group view with X00001 id " + id);
				GroupDTO groupDTO=groupService.searchGroupView(id,memid);
				if (groupDTO== null) {
					System.out.println("group with id " + id + " not found");
					return new ResponseEntity<GroupDTO>(HttpStatus.NOT_FOUND);
				}
				
				return new ResponseEntity<GroupDTO>(groupDTO, HttpStatus.OK);
			}
	//-------------------Update Group--------------------------------------------------------
			
			@RequestMapping(value = "/update/{id}", method = RequestMethod.PUT)
			public ResponseEntity<Group> modify(@PathVariable("id") int id, @RequestBody Group group){
				System.out.println("updating group with X00001 id " + id);
				Group currentgroup = groupService.searchById(id);
				if (currentgroup == null) {
					System.out.println("Group with id " + id + " not found");
					return new ResponseEntity<Group>(HttpStatus.NOT_FOUND);
				}
				groupService.modifyGroup(group);
				Group g=groupService.searchById(group.getGroupId());
				return new ResponseEntity<Group>(g, HttpStatus.OK);
			}
			
     //-------------------delete a Group--------------------------------------------------------
			
			@RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
			public ResponseEntity<Group> deleteGroup(@PathVariable("id") int id) {
				System.out.println("deleting  Group x0001 " + id);
				Group group=groupService.searchById(id);
				if (group == null) {
					System.out.println("Unable to delete. Group with id " + id + " not found");
					return new ResponseEntity<Group>(HttpStatus.NOT_FOUND);
				}
				groupService.deleteGroup(group);
				return new ResponseEntity<Group>(HttpStatus.NO_CONTENT);
			}
			
  //-------------------Retrieve member created Groups --------------------------------------------------------
			
			@RequestMapping(value = "/membercreatedgroups/{memId}/{page}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
			public ResponseEntity<List<GroupDTO>> getMemberCreatedGroups(@PathVariable("memId") int memId,@PathVariable("page") int page) {
				System.out.println("Fetching all groups with X00001 page " + page);
				int start = (page-1)*10;
				//int end=page*10;
				List<GroupDTO> groups = groupService.searchMemberCreatedGroups(memId,start);
				if (groups == null) {
					System.out.println("groups with page " + page + " not found");
					return new ResponseEntity<List<GroupDTO>>(HttpStatus.NOT_FOUND);
				}
				return new ResponseEntity<List<GroupDTO>>(groups, HttpStatus.OK);
			}
      
			

}
