package com.melzol.services.beans;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(Include.NON_NULL)
public class MemberViewDTO {
	private Integer memberId;
	private String memberName;
	
	List<EventListDTO> event=new ArrayList<EventListDTO>();

	public Integer getMemberId() {
		return memberId;
	}

	public void setMemberId(Integer memberId) {
		this.memberId = memberId;
	}

	public String getMemberName() {
		return memberName;
	}

	public void setMemberName(String memberName) {
		this.memberName = memberName;
	}

	public List<EventListDTO> getEvent() {
		return event;
	}

	public void setEvent(List<EventListDTO> event) {
		this.event = event;
	}
	
	

}
