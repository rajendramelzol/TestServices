package com.melzol.services.model;
// default package
// Generated Jun 20, 2016 5:08:51 PM by Hibernate Tools 3.4.0.CR1

import static javax.persistence.GenerationType.IDENTITY;

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
@Table(name = "event",  uniqueConstraints = @UniqueConstraint(columnNames = "id"))
@JsonInclude(Include.NON_NULL)
public class Event implements java.io.Serializable {

	private Integer id;
	private String eventName;
	private Integer category;
	private Integer type;
	private Date fromDate;
	private String ftTime;
	private String location;
	private String description;
	private String eventPic;
	private Integer pincode;
	private Integer owner;
	private Date createdDate;
	private Date updatedDate;
	private Integer status;
	private String areaName;
	private String city;
	private String state;
	private String country;
	private Integer memberCount;
	private String ownerName;
	private String fDate;
	@Transient
	public String getfDate() {
		return fDate;
	}

	public void setfDate(String fDate) {
		this.fDate = fDate;
	}

	@Transient
	public String getOwnerName() {
		return ownerName;
	}

	public void setOwnerName(String ownerName) {
		this.ownerName = ownerName;
	}

	public Event() {
	}
	
	
	public Event(Integer id, String eventName, Integer category, Integer type, Date fromDate, String ftTime,
			String location, String description, String eventPic, Integer pincode, Integer owner, Date createdDate,
			Date updatedDate, Integer status, String areaName, String city, String state, String country,
			Integer memberCount, String ownerName, String fDate) {
		this.id = id;
		this.eventName = eventName;
		this.category = category;
		this.type = type;
		this.fromDate = fromDate;
		this.ftTime = ftTime;
		this.location = location;
		this.description = description;
		this.eventPic = eventPic;
		this.pincode = pincode;
		this.owner = owner;
		this.createdDate = createdDate;
		this.updatedDate = updatedDate;
		this.status = status;
		this.areaName = areaName;
		this.city = city;
		this.state = state;
		this.country = country;
		this.memberCount = memberCount;
		this.ownerName = ownerName;
		this.fDate = fDate;
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	@Column(name = "event_name")
	public String getEventName() {
		return this.eventName;
	}

	public void setEventName(String eventName) {
		this.eventName = eventName;
	}
	@Column(name = "category")
	public Integer getCategory() {
		return category;
	}

	public void setCategory(Integer category) {
		this.category = category;
	}
	@Column(name = "type")
	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	@Column(name = "from_date")
	public Date getFromDate() {
		return this.fromDate;
	}

	public void setFromDate(Date fromDate) {
		this.fromDate = fromDate;
	}
	@Column(name = "ft_time")
	public String getFtTime() {
		return this.ftTime;
	}

	public void setFtTime(String ftTime) {
		this.ftTime = ftTime;
	}
	@Column(name = "location")
	public String getLocation() {
		return this.location;
	}

	public void setLocation(String location) {
		this.location = location;
	}
	@Column(name = "description")
	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	@Column(name = "event_pic")
	public String getEventPic() {
		return this.eventPic;
	}

	public void setEventPic(String eventPic) {
		this.eventPic = eventPic;
	}
	@Column(name = "pincode")
	public Integer getPincode() {
		return this.pincode;
	}

	public void setPincode(Integer pincode) {
		this.pincode = pincode;
	}
	@Column(name = "owner")
	public Integer getOwner() {
		return this.owner;
	}

	public void setOwner(Integer owner) {
		this.owner = owner;
	}
	@Column(name = "created_date")
	public Date getCreatedDate() {
		return this.createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}
	@Column(name = "updated_date")
	public Date getUpdatedDate() {
		return this.updatedDate;
	}

	public void setUpdatedDate(Date updatedDate) {
		this.updatedDate = updatedDate;
	}
	@Column(name = "status")
	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}
	@Column(name = "area_name")
	public String getAreaName() {
		return areaName;
	}

	public void setAreaName(String areaName) {
		this.areaName = areaName;
	}

	@Column(name = "city")
	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}
	@Column(name = "state")
	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}
	@Column(name = "country")
	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}
	@Column(name = "member_count")
	public Integer getMemberCount() {
		return memberCount;
	}

	public void setMemberCount(Integer memberCount) {
		this.memberCount = memberCount;
	}
	
	

}
