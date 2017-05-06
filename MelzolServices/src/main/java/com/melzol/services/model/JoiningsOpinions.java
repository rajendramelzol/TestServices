package com.melzol.services.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@Entity
@Table(name = "joinings_opinions")
@JsonInclude(Include.NON_NULL)
public class JoiningsOpinions implements java.io.Serializable{
	
	private Integer refId;
	private Integer memberId;
	private Integer refType;
	private Integer activityType;
	private Date joinDate;
	private Date exitDate;
	private Integer opinion;
	private String uDate;
	
	
	@Transient
	public String getuDate() {
		return uDate;
	}
	public void setuDate(String uDate) {
		this.uDate = uDate;
	}



	public JoiningsOpinions(){
		
	}



	public JoiningsOpinions(Integer refId, Integer memberId, Integer refType, Integer activityType, Date joinDate,
			Date exitDate, Integer opinion) {
		super();
		this.refId = refId;
		this.memberId = memberId;
		this.refType = refType;
		this.activityType = activityType;
		this.joinDate = joinDate;
		this.exitDate = exitDate;
		this.opinion = opinion;
	}



	@Id
	@Column(name = "ref_id", unique = true, nullable = false)
	public Integer getRefId() {
		return refId;
	}
	public void setRefId(Integer refId) {
		this.refId = refId;
	}
	@Id
	@Column(name = "member_id" , unique = true, nullable = false)
	public Integer getMemberId() {
		return memberId;
	}
	public void setMemberId(Integer memberId) {
		this.memberId = memberId;
	}
	@Id
	@Column(name="ref_type", unique = true, nullable = false)
	public Integer getRefType() {
		return refType;
	}
	public void setRefType(Integer refType) {
		this.refType = refType;
	}
	@Column(name = "activity_type")
	public Integer getActivityType() {
		return activityType;
	}

	public void setActivityType(Integer activityType) {
		this.activityType = activityType;
	}
	@Column(name = "join_date")
	public Date getJoinDate() {
		return joinDate;
	}

	public void setJoinDate(Date joinDate) {
		this.joinDate = joinDate;
	}
	@Column(name = "exit_date")
	public Date getExitDate() {
		return exitDate;
	}

	public void setExitDate(Date exitDate) {
		this.exitDate = exitDate;
	}
	@Column(name = "opinion")
	public Integer getOpinion() {
		return opinion;
	}

	public void setOpinion(Integer opinion) {
		this.opinion = opinion;
	}
	
	
}
