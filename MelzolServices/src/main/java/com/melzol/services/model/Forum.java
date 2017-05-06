package com.melzol.services.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.persistence.UniqueConstraint;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@Entity
@Table(name="forum" , uniqueConstraints=@UniqueConstraint(columnNames="forum_id"))
@JsonInclude(Include.NON_NULL)
public class Forum implements Serializable{
	private Integer forumId;
	private String title;
	private String description;
	private Date createdTs;
	private Date updatedTs;
	private String imageId;
	private Integer createdBy;
	private Integer status;
	private Double latitude;
	private Double longitude;
	private Integer likeCount;
	private Integer spamCount;
	private String fDate;
	
	

	@Transient
	public String getfDate() {
		return fDate;
	}

	public void setfDate(String fDate) {
		this.fDate = fDate;
	}

	public Forum(){
		
	}
	

	public Forum(Integer forumId, String title, String description, Date createdTs, Date updatedTs, String imageId,
			Integer createdBy, Integer status, Double latitude, Double longitude, Integer likeCount, Integer spamCount,
			String fDate) {
		super();
		this.forumId = forumId;
		this.title = title;
		this.description = description;
		this.createdTs = createdTs;
		this.updatedTs = updatedTs;
		this.imageId = imageId;
		this.createdBy = createdBy;
		this.status = status;
		this.latitude = latitude;
		this.longitude = longitude;
		this.likeCount = likeCount;
		this.spamCount = spamCount;
		this.fDate = fDate;
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "forum_id", unique = true, nullable = false)
	public Integer getForumId() {
		return forumId;
	}

	public void setForumId(Integer forumId) {
		this.forumId = forumId;
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
	@Column(name = "image_id")
	public String getImageId() {
		return imageId;
	}
	public void setImageId(String imageId) {
		this.imageId = imageId;
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
	@Column(name="status")
	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	@Column(name = "like_count")
	public Integer getLikeCount() {
		return likeCount;
	}

	public void setLikeCount(Integer likeCount) {
		this.likeCount = likeCount;
	}
	@Column(name = "spam_count")
	public Integer getSpamCount() {
		return spamCount;
	}

	public void setSpamCount(Integer spamCount) {
		this.spamCount = spamCount;
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
	

}
