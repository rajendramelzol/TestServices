package com.melzol.services.model;

import static javax.persistence.GenerationType.IDENTITY;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@Entity
@Table(name = "event_members",  uniqueConstraints = @UniqueConstraint(columnNames = "id"))
@JsonInclude(Include.NON_NULL)
public class EventMembers implements java.io.Serializable{
	private Integer id;
	private Integer eventId;
	private String eventName;
	private Integer memberId;
	private String memberName;
	private Integer owner;
	private Integer pincode;
	private Date updatedTs;
	
	

	public EventMembers() {
		// TODO Auto-generated constructor stub
	}
	
	

	public EventMembers(Integer id, Integer eventId, Integer memberId, String memberName, Integer owner,
			Integer pincode, Date updatedTs) {
		this.id = id;
		this.eventId = eventId;
		this.memberId = memberId;
		this.memberName = memberName;
		this.owner = owner;
		this.pincode = pincode;
		this.updatedTs = updatedTs;
	}


	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@Column(name = "event_id", nullable = false)
	public Integer getEventId() {
		return eventId;
	}

	public void setEventId(Integer eventId) {
		this.eventId = eventId;
	}
	@Column(name = "event_name")
	public String getEventName() {
		return eventName;
	}

	public void setEventName(String eventName) {
		this.eventName = eventName;
	}

	@Column(name = "member_id")
	public Integer getMemberId() {
		return memberId;
	}

	public void setMemberId(Integer memberId) {
		this.memberId = memberId;
	}
	@Column(name = "member_name")
	public String getMemberName() {
		return memberName;
	}

	public void setMemberName(String memberName) {
		this.memberName = memberName;
	}
	@Column(name = "owner")
	public Integer getOwner() {
		return owner;
	}

	public void setOwner(Integer owner) {
		this.owner = owner;
	}
	@Column(name = "pincode")
	public Integer getPincode() {
		return pincode;
	}

	public void setPincode(Integer pincode) {
		this.pincode = pincode;
	}
	@Column(name = "updated_ts")
	public Date getUpdatedTs() {
		return updatedTs;
	}

	public void setUpdatedTs(Date updatedTs) {
		this.updatedTs = updatedTs;
	}
	
	

}
