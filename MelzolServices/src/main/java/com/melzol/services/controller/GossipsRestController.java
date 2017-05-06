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

import com.melzol.services.beans.GossipsDTO;
import com.melzol.services.model.Gossips;
import com.melzol.services.service.GossipsService;
import com.melzol.services.service.JoiningsOpinionsService;
import com.melzol.services.util.Utils;

@RestController
@RequestMapping("/gossips")
public class GossipsRestController{
	@Autowired
	GossipsService gossipsService;
	@Autowired
	JoiningsOpinionsService joiningsOpinionsService;
	
	
	//-------------------Create a Gossips--------------------------------------------------------
			@RequestMapping(value ="/add" ,method = RequestMethod.POST)
			public ResponseEntity<Gossips> createGossips(@RequestBody Gossips gossips,UriComponentsBuilder ucBuilder){
				System.out.println("Creating Gossips x0001 " + gossips.getTitle());
				gossips.setCreatedDate(Utils.parseCreatedDate(gossips.getcDate()));
				gossips.setUpdatedTs(Utils.parseCreatedDate(gossips.getcDate()));
				gossips.setStatus(1);
				gossipsService.addGossips(gossips);
				Gossips a=gossipsService.searchById(gossips.getGossipId());
				return new ResponseEntity<Gossips>(a, HttpStatus.CREATED);
			}
//-------------------Retrieve All  Gossips--------------------------------------------------------
			
			@RequestMapping(value = "/searchall/{memId}/{page}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
			public ResponseEntity<List<GossipsDTO>> getAllGossips(@PathVariable("memId") int memId,@PathVariable("page") int page) {
				System.out.println("Fetching all gossips with X00001 page " + page);
				int start = (page-1)*10;
				//int end=page*10;
				List<GossipsDTO> gossips = gossipsService.searchAll(memId,start);
				if (gossips == null) {
					System.out.println("gossips with page " + page + " not found");
					return new ResponseEntity<List<GossipsDTO>>(HttpStatus.NOT_FOUND);
				}
				return new ResponseEntity<List<GossipsDTO>>(gossips, HttpStatus.OK);
			}
    //-------------------Retrieve All  Gossips by category--------------------------------------------------------
			
			@RequestMapping(value = "/searchbycat/{memId}/{cat}/{page}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
			public ResponseEntity<List<GossipsDTO>> getGossipsByCat(@PathVariable("memId") int memId,@PathVariable("cat") int cat,@PathVariable("page") int page) {
				System.out.println("Fetching all gossips with X00001 cat " + cat);
				int start = (page-1)*20;
				//int end=page*10;
				List<GossipsDTO> gossips = gossipsService.searchByCat(memId,cat,start);
				if (gossips == null) {
					System.out.println("gossips with cat " + cat + " not found");
					return new ResponseEntity<List<GossipsDTO>>(HttpStatus.NOT_FOUND);
				}
				return new ResponseEntity<List<GossipsDTO>>(gossips, HttpStatus.OK);
			}
			
	//-------------------Retrieve a Gossips--------------------------------------------------------
				@RequestMapping(value ="/search/{id}" ,method = RequestMethod.GET,produces = MediaType.APPLICATION_JSON_VALUE)
				public ResponseEntity<Gossips> searchForum(@PathVariable("id") int id){
					System.out.println("searching Gossips x0001 " + id);
					Gossips gossips=gossipsService.searchById(id);
					if(gossips==null){
						return new ResponseEntity<Gossips>(HttpStatus.NOT_FOUND);
					}
					return new ResponseEntity<Gossips>(gossips, HttpStatus.OK);
				}
	
	//-------------------Retrieve All member Gossips--------------------------------------------------------
				
				@RequestMapping(value = "/mygossips/{memId}/{page}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
				public ResponseEntity<List<GossipsDTO>> getMyGossips(@PathVariable("memId") int memId,@PathVariable("page") int page) {
					System.out.println("Fetching my gossips with X00001 cat " + memId);
					int start = (page-1)*20;
					//int end=page*10;
					List<GossipsDTO> gossips = gossipsService.searchMyGossips(memId,start);
					if (gossips == null) {
						System.out.println("gossips with memId " + memId + " not found");
						return new ResponseEntity<List<GossipsDTO>>(HttpStatus.NOT_FOUND);
					}
					return new ResponseEntity<List<GossipsDTO>>(gossips, HttpStatus.OK);
				}
	//-------------------Retrieve gossips By title--------------------------------------------------------
				@RequestMapping(value = "/searchGossip/{memId}/{title}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
				public ResponseEntity<List<GossipsDTO>> searchGossip(@PathVariable("memId") int memId,@PathVariable("title") String title) {
					List<GossipsDTO> gossips = gossipsService.searchByTitle(memId,title);
					if (gossips == null) {
						System.out.println("gossips with title " + title + " not found");
						return new ResponseEntity<List<GossipsDTO>>(HttpStatus.NOT_FOUND);
					}
					return new ResponseEntity<List<GossipsDTO>>(gossips, HttpStatus.OK);
				}
    //-------------------Search in My Gossips--------------------------------------------------------
				
				@RequestMapping(value = "/searchinmygossips/{memId}/{title}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
				public ResponseEntity<List<GossipsDTO>> searchMyGossips(@PathVariable("memId") int memId,@PathVariable("title") String title) {
					System.out.println("Fetching my gossips with X00001 cat " + memId);
					List<GossipsDTO> gossips = gossipsService.searchInMyGossips(memId,title);
					if (gossips == null) {
						System.out.println("gossips with memId " + memId + " not found");
						return new ResponseEntity<List<GossipsDTO>>(HttpStatus.NOT_FOUND);
					}
					return new ResponseEntity<List<GossipsDTO>>(gossips, HttpStatus.OK);
				}
	//-------------------Update Gossips--------------------------------------------------------
				
				@RequestMapping(value = "update/{id}", method = RequestMethod.PUT)
				public ResponseEntity<Gossips> modify(@PathVariable("id") int id, @RequestBody Gossips gossips){
					System.out.println("updating event with X00001 id " + id);
					Gossips gos = gossipsService.searchById(id);
					
					if (gos == null) {
						System.out.println("mem with id " + id + " not found");
						return new ResponseEntity<Gossips>(HttpStatus.NOT_FOUND);
					}
					
					gossipsService.updateGossips(gossips);
					Gossips g = gossipsService.searchById(gossips.getGossipId());
					return new ResponseEntity<Gossips>(g , HttpStatus.OK);
				}
	//-------------------delete a Gossip--------------------------------------------------------
				
				@RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
				public ResponseEntity<Gossips> deleteGossip(@PathVariable("id") int id) {
					System.out.println("deleting  event x0001 " + id);
					Gossips gossip=gossipsService.searchById(id);
					if (gossip == null) {
						System.out.println("Unable to delete. event with id " + id + " not found");
						return new ResponseEntity<Gossips>(HttpStatus.NOT_FOUND);
					}
					gossipsService.deleteGossip(gossip);
					//eventMembersService.deleteEventDetails(id);
					return new ResponseEntity<Gossips>(HttpStatus.NO_CONTENT);
				}
				
		//-------------------Retrieve Popular  Gossips--------------------------------------------------------
				
				@RequestMapping(value = "/populargossips/{memId}/{page}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
				public ResponseEntity<List<GossipsDTO>> getPopularGossips(@PathVariable("memId") int memId,@PathVariable("page") int page) {
					System.out.println("Fetching all gossips with X00001 page " + page);
					int start = (page-1)*20;
					List<GossipsDTO> gossips = gossipsService.searchPopularGossips(memId,start);
					if (gossips == null) {
						System.out.println("gossips with page " + page + " not found");
						return new ResponseEntity<List<GossipsDTO>>(HttpStatus.NOT_FOUND);
					}
					return new ResponseEntity<List<GossipsDTO>>(gossips, HttpStatus.OK);
				}
				
		//-------------------Retrieve a Gossipview--------------------------------------------------------
				@RequestMapping(value ="/gossipview/{id}/{memId}" ,method = RequestMethod.GET,produces = MediaType.APPLICATION_JSON_VALUE)
				public ResponseEntity<GossipsDTO> searchGossipView(@PathVariable("id") int id,@PathVariable("memId") int memId){
					System.out.println("searching Gossips x0001 " + id);
					GossipsDTO gossips=gossipsService.searchGossipView(id,memId);
					if(gossips==null){
						return new ResponseEntity<GossipsDTO>(HttpStatus.NOT_FOUND);
					}
					return new ResponseEntity<GossipsDTO>(gossips, HttpStatus.OK);
				}
				
		//-------------------Retrieve All member Gossips--------------------------------------------------------
				
				@RequestMapping(value = "/membercreatedgossips/{memId}/{page}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
				public ResponseEntity<List<GossipsDTO>> getMemberCreatedGossips(@PathVariable("memId") int memId,@PathVariable("page") int page) {
					System.out.println("Fetching my gossips with X00001 cat " + memId);
					int start = (page-1)*20;
					//int end=page*10;
					List<GossipsDTO> gossips = gossipsService.searchMemberCreatedGossips(memId,start);
					if (gossips == null) {
						System.out.println("gossips with memId " + memId + " not found");
						return new ResponseEntity<List<GossipsDTO>>(HttpStatus.NOT_FOUND);
					}
					return new ResponseEntity<List<GossipsDTO>>(gossips, HttpStatus.OK);
				}

}
