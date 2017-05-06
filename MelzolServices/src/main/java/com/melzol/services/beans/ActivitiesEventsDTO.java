package com.melzol.services.beans;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.melzol.services.model.Members;
import com.melzol.services.model.WorkingDays;
@JsonInclude(Include.NON_NULL)
public class ActivitiesEventsDTO {
	private Integer id;
	private String title;
	private String description;
	private Integer type;
	private Integer subType;
	private Integer category;
	private Double latitude;
	private Double longitude;
	private Integer workingdaysRefid;
	private Integer activityMode;
	private String mobile;
	private String address;
	private String emailId;
	private String website;
	private Date createdTs;
	private Date updatedTs;
	private Integer createdBy;
	private Integer status;
	private Integer approvedBy;
	private Integer amount;
	private Integer paymentMode;
	private Integer likeCount;
	private Integer bookCount;
	private Integer spamCount;
	private String imageId;
	private Integer activityType;
	private Integer opinion;
	private Timestamp startDate;
	private String fDate;
	private String ownerName;
	private String gcmKey;
	private Integer groupId;
	private Integer joined;
	private String ownerImage;

	List<WorkingDays> wrk=new ArrayList<WorkingDays>();
	
	List<Members> eventMembers;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Integer getType() {
		return type;
	}
	public void setType(Integer type) {
		this.type = type;
	}
	public Integer getSubType() {
		return subType;
	}
	public void setSubType(Integer subType) {
		this.subType = subType;
	}
	public Integer getCategory() {
		return category;
	}
	public void setCategory(Integer category) {
		this.category = category;
	}
	public Double getLatitude() {
		return latitude;
	}
	public void setLatitude(Double latitude) {
		this.latitude = latitude;
	}
	public Double getLongitude() {
		return longitude;
	}
	public void setLongitude(Double longitude) {
		this.longitude = longitude;
	}
	public Integer getWorkingdaysRefid() {
		return workingdaysRefid;
	}
	public void setWorkingdaysRefid(Integer workingdaysRefid) {
		this.workingdaysRefid = workingdaysRefid;
	}
	public Integer getActivityMode() {
		return activityMode;
	}
	public void setActivityMode(Integer activityMode) {
		this.activityMode = activityMode;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getEmailId() {
		return emailId;
	}
	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}
	
	
	public Date getCreatedTs() {
		return createdTs;
	}
	public void setCreatedTs(Date createdTs) {
		this.createdTs = createdTs;
	}
	
	public Date getUpdatedTs() {
		return updatedTs;
	}
	public void setUpdatedTs(Date updatedTs) {
		this.updatedTs = updatedTs;
	}
	public Integer getCreatedBy() {
		return createdBy;
	}
	public void setCreatedBy(Integer createdBy) {
		this.createdBy = createdBy;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public Integer getApprovedBy() {
		return approvedBy;
	}
	public void setApprovedBy(Integer approvedBy) {
		this.approvedBy = approvedBy;
	}
	public Integer getAmount() {
		return amount;
	}
	public void setAmount(Integer amount) {
		this.amount = amount;
	}
	public Integer getPaymentMode() {
		return paymentMode;
	}
	public void setPaymentMode(Integer paymentMode) {
		this.paymentMode = paymentMode;
	}
	public Integer getLikeCount() {
		return likeCount;
	}
	public void setLikeCount(Integer likeCount) {
		this.likeCount = likeCount;
	}
	public Integer getBookCount() {
		return bookCount;
	}
	public void setBookCount(Integer bookCount) {
		this.bookCount = bookCount;
	}
	public Integer getSpamCount() {
		return spamCount;
	}
	public void setSpamCount(Integer spamCount) {
		this.spamCount = spamCount;
	}
	
	public String getImageId() {
		return imageId;
	}
	public void setImageId(String imageId) {
		this.imageId = imageId;
	}
	
	public Integer getActivityType() {
		return activityType;
	}
	public void setActivityType(Integer activityType) {
		this.activityType = activityType;
	}
	
	public Integer getOpinion() {
		return opinion;
	}
	public void setOpinion(Integer opinion) {
		this.opinion = opinion;
	}
	
	public Timestamp getStartDate() {
		return startDate;
	}
	public void setStartDate(Timestamp startDate) {
		this.startDate = startDate;
	}
	
	public String getfDate() {
		return fDate;
	}
	public void setfDate(String fDate) {
		this.fDate = fDate;
	}
	
	public String getOwnerName() {
		return ownerName;
	}
	public void setOwnerName(String ownerName) {
		this.ownerName = ownerName;
	}
	public List<WorkingDays> getWrk() {
		return wrk;
	}
	public void setWrk(List<WorkingDays> wrk) {
		this.wrk = wrk;
	}
	
	public String getWebsite() {
		return website;
	}

	public void setWebsite(String website) {
		this.website = website;
	}
	public String getGcmKey() {
		return gcmKey;
	}
	public void setGcmKey(String gcmKey) {
		this.gcmKey = gcmKey;
	}
	public Integer getGroupId() {
		return groupId;
	}
	public void setGroupId(Integer groupId) {
		this.groupId = groupId;
	}
	public Integer getJoined() {
		return joined;
	}
	public void setJoined(Integer joined) {
		this.joined = joined;
	}
	public List<Members> getEventMembers() {
		return eventMembers;
	}
	public void setEventMembers(List<Members> eventMembers) {
		this.eventMembers = eventMembers;
	}
	public String getOwnerImage() {
		return ownerImage;
	}
	public void setOwnerImage(String ownerImage) {
		this.ownerImage = ownerImage;
	}
	
	

}
