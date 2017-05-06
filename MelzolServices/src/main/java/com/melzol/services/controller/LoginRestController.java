package com.melzol.services.controller;

import java.io.IOException;
import java.util.Date;

import org.apache.commons.httpclient.HttpException;
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

import com.melzol.services.model.LatitudeLongitude;
import com.melzol.services.model.MemberNeighbourhoods;
import com.melzol.services.model.Members;
import com.melzol.services.service.LatitudeLongitudeService;
import com.melzol.services.service.MemberNeighbourhoodsService;
import com.melzol.services.service.MembersService;
import com.melzol.services.util.Utils;

@RestController
@RequestMapping("/login")
public class LoginRestController {
	@Autowired
	MembersService membersService;
	@Autowired
	LatitudeLongitudeService latitudeLongitudeService;
	@Autowired
	MemberNeighbourhoodsService memberNeighbourhoodsService;
	
	//-------------------check Mobile--------------------------------------------------------
	
		@RequestMapping(value = "/checkmobile/{gcmkey}/{mobile}",method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
		public ResponseEntity<Members> checkMobile(@PathVariable("gcmkey") String gcmkey,@PathVariable("mobile") String mobile) {
			System.out.println("Checking mobile x0001 " + mobile);
			
				Members m=membersService.searchByMobile(mobile); 
				if (m == null) {
					System.out.println("members with mobile" + mobile + " not found");
					return new ResponseEntity<Members>(HttpStatus.NOT_FOUND);
				}
				m.setGcmKey(gcmkey);
				membersService.updateMember(m);
				return new ResponseEntity<Members>(m, HttpStatus.OK);
				
				
		}
		
		
	//-------------------check UserName--------------------------------------------------------
		
		@RequestMapping(value = "/checkUserName/{userName}",method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
		public ResponseEntity<Members> checkUserName(@PathVariable("userName") String userName) {
			System.out.println("Checking userName x0001 " + userName);
			
				Members m=membersService.searchUserName(userName);
				if (m == null) {
					System.out.println("members with userName " + userName + " not found");
					return new ResponseEntity<Members>(HttpStatus.NOT_FOUND);
				}
				else{
					return new ResponseEntity<Members>(m, HttpStatus.OK);
				}
			
			
		}
	
	//-------------------register a member--------------------------------------------------------
	
			@RequestMapping(value = "/register", method = RequestMethod.POST)
			public ResponseEntity<Members> login(@RequestBody Members member,UriComponentsBuilder ucBuilder) throws HttpException, IOException {
				System.out.println("Registering member x0001 " + member.getUserName());
				
				Double lat=Utils.trimDouble(Double.valueOf(member.getLatitude()));
				Double lon=Utils.trimDouble(Double.valueOf(member.getLongitude()));
				LatitudeLongitude l=latitudeLongitudeService.checkLatLong(lat,lon);
				if(l!=null){
					member.setAddressRefid(l.getId());
					
				}
				else{
					LatitudeLongitude la=new LatitudeLongitude();
					la.setLatitude(Double.valueOf(lat));
					la.setLongitude(Double.valueOf(lon));
					latitudeLongitudeService.addLatitudeLongitude(la);
					member.setAddressRefid(la.getId());
					
				}
				member.setStatus(1);
				member.setUpdatedTs(Utils.parseCreatedDate(member.getuDate()));
				membersService.addMember(member);
				MemberNeighbourhoods ngh= new MemberNeighbourhoods();
				ngh.setMemberId(member.getMemberId());
				ngh.setLatitude(member.getLatitude());
				ngh.setLongitude(member.getLongitude());
				ngh.setCreatedTs(new Date());
				ngh.setPlace(member.getPlace());
				ngh.setCity(member.getCity());
				ngh.setState(member.getState());
				ngh.setCountry(member.getCountry());
				ngh.setPincode(member.getPincode());
				memberNeighbourhoodsService.save(ngh);
				Members mem=membersService.searchById(member.getMemberId());
				return new ResponseEntity<Members>(mem, HttpStatus.CREATED);
					
			}
	//-------------------update Profile pic--------------------------------------------------------
			
			@RequestMapping(value = "/updateProfilePic/{profilePic}/{memId}",method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
			public ResponseEntity<Members> updateProfilePic(@PathVariable("profilePic") String profilePic,@PathVariable("memId") String memId) {
				System.out.println("Checking memId x0001 " + memId);
				
				membersService.updateProfilePic(profilePic,memId);
				Members m=membersService.searchById(Integer.parseInt(memId));
					if (m == null) {
						System.out.println("members with memId" + memId + " not found");
						return new ResponseEntity<Members>(HttpStatus.NOT_FOUND);
					}
					else{
						return new ResponseEntity<Members>(m, HttpStatus.OK);
					}
					
			}
			
}
