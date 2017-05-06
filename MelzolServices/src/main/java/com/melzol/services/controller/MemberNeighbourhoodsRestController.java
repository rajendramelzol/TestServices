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

import com.melzol.services.model.LatitudeLongitude;
import com.melzol.services.model.MemberNeighbourhoods;
import com.melzol.services.service.LatitudeLongitudeService;
import com.melzol.services.service.MemberNeighbourhoodsService;
import com.melzol.services.util.Utils;

@RestController
@RequestMapping("/memberneighbourhoods")
public class MemberNeighbourhoodsRestController {
	@Autowired
	MemberNeighbourhoodsService memberNeighbourhoodsService;
	@Autowired
	LatitudeLongitudeService latitudeLongitudeService;
	
	//-------------------save a member neighbourhoods--------------------------------------------------------
	
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public ResponseEntity<MemberNeighbourhoods> addMemberNeighbourhood(@RequestBody MemberNeighbourhoods memberNeighbourhoods, 	UriComponentsBuilder ucBuilder) {
		System.out.println("Creating Notification x0001 " + memberNeighbourhoods.getMemberId());
		Double lat=Utils.trimDouble(Double.valueOf(memberNeighbourhoods.getLatitude()));
		Double lon=Utils.trimDouble(Double.valueOf(memberNeighbourhoods.getLongitude()));
		LatitudeLongitude l=latitudeLongitudeService.checkLatLong(lat,lon);
		if(l!=null){
			memberNeighbourhoods.setAddressRefId(l.getId());
			
		}
		else{
			LatitudeLongitude la=new LatitudeLongitude();
			la.setLatitude(Double.valueOf(lat));
			la.setLongitude(Double.valueOf(lon));
			latitudeLongitudeService.addLatitudeLongitude(la);
			memberNeighbourhoods.setAddressRefId(la.getId());
			
		}
		memberNeighbourhoods.setCreatedTs(new Date());
		memberNeighbourhoodsService.save(memberNeighbourhoods);
	
		return new ResponseEntity<MemberNeighbourhoods>(memberNeighbourhoods, HttpStatus.CREATED);
	}
	
	//-------------------Retrieve All member Neighbourhoods--------------------------------------------------------
	
	@RequestMapping(value = "/searchall/{memId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<MemberNeighbourhoods>> getAllChatting(@PathVariable("memId") int memId) {
		System.out.println("Fetching all Neighbourhoods with X00001 memid" + memId);
		//int start = (page-1)*10;
		List<MemberNeighbourhoods> memberNeighbourhoods = memberNeighbourhoodsService.searchMemberNeighbourhoods(memId);
		if (memberNeighbourhoods== null) {
			System.out.println("Notification with memid " + memId + " not found");
			return new ResponseEntity<List<MemberNeighbourhoods>>(HttpStatus.NOT_FOUND);
		}
		
		return new ResponseEntity<List<MemberNeighbourhoods>>(memberNeighbourhoods, HttpStatus.OK);
	}


}
