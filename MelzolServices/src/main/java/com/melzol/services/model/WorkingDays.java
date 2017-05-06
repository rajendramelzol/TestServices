package com.melzol.services.model;

import static javax.persistence.GenerationType.IDENTITY;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.persistence.UniqueConstraint;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@Entity
@Table(name = "working_days",  uniqueConstraints = @UniqueConstraint(columnNames = "id"))
@JsonInclude(Include.NON_NULL)
public class WorkingDays implements Serializable{
	
	private Integer id;
	private Date startDate;
	private Date endDate;
	private String days;
	private String startTime;
	private String endTime;
	private String imageId;
	private Integer refId;
	private Integer refType;
	private String sDate;
	private String eDate;
	@Transient
	public String getsDate() {
		return sDate;
	}

	public void setsDate(String sDate) {
		this.sDate = sDate;
	}
	@Transient
	public String geteDate() {
		return eDate;
	}

	public void seteDate(String eDate) {
		this.eDate = eDate;
	}

	public WorkingDays(){ 
		
	}

	public WorkingDays(Integer id, Date startDate, Date endDate, String days, String startTime, String endTime,
			String imageId, Integer refid,Integer refType) {
		super();
		this.id = id;
		this.startDate = startDate;
		this.endDate = endDate;
		this.days = days;
		this.startTime = startTime;
		this.endTime = endTime;
		this.imageId = imageId;
		this.refId = refId;
		this.refType=refType;
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
	@Column(name = "start_date")
	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	@Column(name = "end_date")
	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	@Column(name = "days")
	public String getDays() {
		return days;
	}

	public void setDays(String days) {
		this.days = days;
	}
	@Column(name = "start_time")
	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}
	@Column(name = "end_time")
	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}
	@Column(name = "image_id")
	public String getImageId() {
		return imageId;
	}

	public void setImageId(String imageId) {
		this.imageId = imageId;
	}
	@Column(name = "ref_id")
	public Integer getRefId() {
		return refId;
	}
	
	public void setRefId(Integer refId) {
		this.refId = refId;
	}
	@Column(name = "ref_type")
	public Integer getRefType() {
		return refType;
	}

	public void setRefType(Integer refType) {
		this.refType = refType;
	}
	
	

}
