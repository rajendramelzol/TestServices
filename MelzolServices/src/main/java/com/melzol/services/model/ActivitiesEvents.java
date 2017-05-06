package com.melzol.services.model;

import static javax.persistence.GenerationType.IDENTITY;

import java.io.Serializable;
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
@Table(name = "activities_events",  uniqueConstraints = @UniqueConstraint(columnNames = "id"))
@JsonInclude(Include.NON_NULL)
public class ActivitiesEvents implements Serializable{
	
	private Integer id;
	private String title;
	private String description;
	private Integer type;
	private Integer subType;
	private Integer category;
	private Double latitude;
	private Double longitude;
	private Integer activityMode;
	private String mobile;
	private String address;
	private String emailId;
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
	private String website;
	private Integer groupId;
	

	public ActivitiesEvents(){
		
	}
	
	

	
	public ActivitiesEvents(Integer id, String title, String description, Integer type, Integer subType,
			Integer category, Double latitude, Double longitude, Integer activityMode, String mobile, String address,
			String emailId, Date createdTs, Date updatedTs, Integer createdBy, Integer status, Integer approvedBy,
			Integer amount, Integer paymentMode, Integer likeCount, Integer bookCount, Integer spamCount,
			String website, Integer groupId) {
		super();
		this.id = id;
		this.title = title;
		this.description = description;
		this.type = type;
		this.subType = subType;
		this.category = category;
		this.latitude = latitude;
		this.longitude = longitude;
		this.activityMode = activityMode;
		this.mobile = mobile;
		this.address = address;
		this.emailId = emailId;
		this.createdTs = createdTs;
		this.updatedTs = updatedTs;
		this.createdBy = createdBy;
		this.status = status;
		this.approvedBy = approvedBy;
		this.amount = amount;
		this.paymentMode = paymentMode;
		this.likeCount = likeCount;
		this.bookCount = bookCount;
		this.spamCount = spamCount;
		this.website = website;
		this.groupId = groupId;
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
	@Column(name = "title")
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}
	@Column(name = "description")
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	@Column(name = "type")
	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}
	@Column(name = "sub_type")
	public Integer getSubType() {
		return subType;
	}

	public void setSubType(Integer subType) {
		this.subType = subType;
	}
	@Column(name = "category")
	public Integer getCategory() {
		return category;
	}

	public void setCategory(Integer category) {
		this.category = category;
	}
	@Column(name = "latitude")
	public Double getLatitude() {
		return latitude;
	}

	public void setLatitude(Double latitude) {
		this.latitude = latitude;
	}
	@Column(name = "longitude")
	public Double getLongitude() {
		return longitude;
	}

	public void setLongitude(Double longitude) {
		this.longitude = longitude;
	}
	@Column(name = "activity_mode")
	public Integer getActivityMode() {
		return activityMode;
	}

	public void setActivityMode(Integer activityMode) {
		this.activityMode = activityMode;
	}
	@Column(name = "mobile")
	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	@Column(name = "address")
	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}
	@Column(name = "email_id")
	public String getEmailId() {
		return emailId;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}
	@Column(name = "created_ts")
	public Date getCreatedTs() {
		return createdTs;
	}

	public void setCreatedTs(Date createdTs) {
		this.createdTs = createdTs;
	}
	@Column(name = "updated_ts")
	public Date getUpdatedTs() {
		return updatedTs;
	}

	public void setUpdatedTs(Date updatedTs) {
		this.updatedTs = updatedTs;
	}

	@Column(name = "created_by")
	public Integer getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(Integer createdBy) {
		this.createdBy = createdBy;
	}
	@Column(name = "status")
	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}
	@Column(name = "approved_by")
	public Integer getApprovedBy() {
		return approvedBy;
	}

	public void setApprovedBy(Integer approvedBy) {
		this.approvedBy = approvedBy;
	}
	@Column(name = "amount")
	public Integer getAmount() {
		return amount;
	}

	public void setAmount(Integer amount) {
		this.amount = amount;
	}
	@Column(name = "payment_mode")
	public Integer getPaymentMode() {
		return paymentMode;
	}

	public void setPaymentMode(Integer paymentMode) {
		this.paymentMode = paymentMode;
	}
	@Column(name = "like_count")
	public Integer getLikeCount() {
		return likeCount;
	}

	public void setLikeCount(Integer likeCount) {
		this.likeCount = likeCount;
	}
	@Column(name = "book_count")
	public Integer getBookCount() {
		return bookCount;
	}

	public void setBookCount(Integer bookCount) {
		this.bookCount = bookCount;
	}
	@Column(name = "spam_count")
	public Integer getSpamCount() {
		return spamCount;
	}

	public void setSpamCount(Integer spamCount) {
		this.spamCount = spamCount;
	}
	
	@Column(name = "website")
	public String getWebsite() {
		return website;
	}

	public void setWebsite(String website) {
		this.website = website;
	}

	@Column(name = "group_id")
	public Integer getGroupId() {
		return groupId;
	}
	public void setGroupId(Integer groupId) {
		this.groupId = groupId;
	}
	
	

}
