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

import com.melzol.services.beans.MemberDTO;
import com.melzol.services.model.LatitudeLongitude;
import com.melzol.services.model.Members;
import com.melzol.services.service.LatitudeLongitudeService;
import com.melzol.services.service.MembersService;
import com.melzol.services.util.Utils;

@RestController
@RequestMapping("/member")
public class MemberRestController {
	@Autowired
	MembersService membersService;
	@Autowired
	LatitudeLongitudeService latitudeLongitudeService;
	
	//-------------------Retrieve All Neighbours--------------------------------------------------------
	
	@RequestMapping(value = "/searchall/{lat}/{lon}/{id}/{page}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Members>> getAllMembers(@PathVariable("lat") Double lat,@PathVariable("lon") Double lon,
			@PathVariable("id") int id,@PathVariable("page") int page) {
		System.out.println("Fetching all members with X00001 lat and lon " +id);
		int start = (page-1)*20;
		List<Members> members = membersService.searchAllMembers(lat,lon,id,start);
		
		if (members == null) {
			System.out.println("members with pincode " +id+" not found");
			return new ResponseEntity<List<Members>>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<List<Members>>(members, HttpStatus.OK);
	}
		
	//-------------------Retrieve single Member--------------------------------------------------------
	
	@RequestMapping(value ="/search/{id}" , method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Members> getMember(@PathVariable("id") int id){
		System.out.println("Fetching Member with X00001 id " + id);
		Members member = membersService.searchById(id);
		if (member == null) {
			System.out.println("member with id " + id + " not found");
			return new ResponseEntity<Members>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Members>(member, HttpStatus.OK);
		
	}
	//-------------------Retrieve  Member View--------------------------------------------------------
	
	@RequestMapping(value = "/memberview/{memid}/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<MemberDTO> getMemberView(@PathVariable("memid") int memid,@PathVariable("id") int id) {
		System.out.println("Fetching event view with X00001 id " + memid);
		MemberDTO mem=membersService.searchMemberView(memid,id);
		if (mem== null) {
			System.out.println("User with id " + memid + " not found");
			return new ResponseEntity<MemberDTO>(HttpStatus.NOT_FOUND);
		}
		
		return new ResponseEntity<MemberDTO>(mem, HttpStatus.OK);
	}
	//-------------------Update Member--------------------------------------------------------
	
	@RequestMapping(value = "update/{id}", method = RequestMethod.PUT)
	public ResponseEntity<Members> modify(@PathVariable("id") int id, @RequestBody Members member){
		System.out.println("updating event with X00001 id " + id);
		Members mem = membersService.searchById(id);
		
		if (mem == null) {
			System.out.println("mem with id " + id + " not found");
			return new ResponseEntity<Members>(HttpStatus.NOT_FOUND);
		}
		if(member.getLatitude()!=null){
		Double lat=Utils.trimDouble(Double.valueOf(member.getLatitude()));
		Double lon=Utils.trimDouble(Double.valueOf(member.getLongitude()));
		LatitudeLongitude l=latitudeLongitudeService.checkLatLong(lat,lon);
		if(l!=null){
			member.setAddressRefid(l.getId());
		}else{
			LatitudeLongitude la=new LatitudeLongitude();
			la.setLatitude(Double.valueOf(lat));
			la.setLongitude(Double.valueOf(lon));
			latitudeLongitudeService.addLatitudeLongitude(la);
			member.setAddressRefid(la.getId());			
		}
			
		}
		membersService.updateMember(member);
		Members m=membersService.searchById(member.getMemberId());
		return new ResponseEntity<Members>(m, HttpStatus.OK);
	}
	//-------------------Retrieve invite Members--------------------------------------------------------
	
		@RequestMapping(value = "/invitemembers/{lat}/{lon}/{memId}/{page}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
		public ResponseEntity<List<MemberDTO>> getInviteMembers(@PathVariable("lat") Double lat,@PathVariable("lon") Double lon,
				@PathVariable("memId") int memId,@PathVariable("page") int page) {
			System.out.println("Fetching all members with X00001 lat " + lat);
			int start = (page-1)*10;
			List<MemberDTO> members = membersService.searchInviteMembers(lat,lon,memId,start);
			if (members == null) {
				System.out.println("members with pincode " + lat + " not found");
				return new ResponseEntity<List<MemberDTO>>(HttpStatus.NOT_FOUND);
			}
			return new ResponseEntity<List<MemberDTO>>(members, HttpStatus.OK);
		}
		//-------------------Retrieve single lat--------------------------------------------------------
		
		@RequestMapping(value ="/searchlat/{lat}/{lon}" , method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
		public ResponseEntity<Members> getMemberlat(@PathVariable("lat") Double lat,@PathVariable("lon") Double lon){
			System.out.println("Fetching Member with X00001 id " + lat);
			Members member = membersService.searchBylat(lat,lon);
			if (member == null) {
				System.out.println("member with id " + lat + " not found");
				return new ResponseEntity<Members>(HttpStatus.NOT_FOUND);
			}
			return new ResponseEntity<Members>(member, HttpStatus.OK);
			
		}
		
	//-------------------Retrieve Group members--------------------------------------------------------
		
		@RequestMapping(value = "/groupmembers/{id}/{refType}/{page}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
		public ResponseEntity<List<Members>> getAllMembers(@PathVariable("id") int id,@PathVariable("refType") int refType,
				@PathVariable("page") int page) {
			System.out.println("Fetching all members with X00001 lat and lon " +id);
			int start = (page-1)*10;
			List<Members> members = membersService.searchGroupMembers(id,refType,start);
			
			if (members == null) {
				System.out.println("members with pincode " +id+" not found");
				return new ResponseEntity<List<Members>>(HttpStatus.NOT_FOUND);
			}
			return new ResponseEntity<List<Members>>(members, HttpStatus.OK);
		}
		//-------------------Retrieve Group members--------------------------------------------------------
		
				@RequestMapping(value = "/opinions/{id}/{refType}/{opinion}/{page}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
				public ResponseEntity<List<Members>> getAllMembersOpinions(@PathVariable("id") int id,@PathVariable("refType") int refType,
						@PathVariable("opinion") int opinion,@PathVariable("page") int page) {
					System.out.println("Fetching all members with X00001 lat and lon " +id);
					int start = (page-1)*30;
					List<Members> members = membersService.searchMemberOpinions(id,refType,opinion,start);
					
					if (members == null) {
						System.out.println("members with pincode " +id+" not found");
						return new ResponseEntity<List<Members>>(HttpStatus.NOT_FOUND);
					}
					return new ResponseEntity<List<Members>>(members, HttpStatus.OK);
				}
				
		//-------------------Retrieve a tagged members--------------------------------------------------------
				
				@RequestMapping(value ="/membertaggedcontacts/{lat}/{lon}/{memId}" ,method = RequestMethod.GET,produces = MediaType.APPLICATION_JSON_VALUE)
				public ResponseEntity<List<Members> > searchMemberTaggedContacts(@PathVariable("lat") Double lat,
						@PathVariable("lon") Double lon,@PathVariable("memId") int memId){
					System.out.println("searching tagged members x0001 " +memId);
					List<Members> mem=membersService.searchMemberTaggedContacts(lat,lon,memId);
					if(mem==null){
						return new ResponseEntity<List<Members> >(HttpStatus.NOT_FOUND);
					}
					
					return new ResponseEntity<List<Members> >(mem, HttpStatus.OK);
				}
		
}
