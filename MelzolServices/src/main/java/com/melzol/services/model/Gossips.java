package com.melzol.services.model;

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
@Table(name = "gossips",  uniqueConstraints = @UniqueConstraint(columnNames = "gossip_id"))
@JsonInclude(Include.NON_NULL)
public class Gossips implements java.io.Serializable{
	
	private Integer gossipId;
	private String title;
	private String description;
	private String webLink;
	private Integer category;
	private Date createdDate;
	private Date updatedTs;
	private Integer createdBy;
	private Integer status;
	private Integer likeCount;
	private Integer spamCount;
	private Integer joinCount;
	private Integer unlikeCount;
	private String imageId;
	private String cDate;
	
	@Transient
	public String getcDate() {
		return cDate;
	}

	public void setcDate(String cDate) {
		this.cDate = cDate;
	}

	public Gossips(){
		
	}
	

	public Gossips(Integer gossipId, String title, String description, String webLink, Integer category,
			Date createdDate, Date updatedTs, Integer createdBy, Integer status, Integer likeCount, Integer spamCount,
			Integer joinCount, Integer unlikeCount, String imageId, String cDate) {
		super();
		this.gossipId = gossipId;
		this.title = title;
		this.description = description;
		this.webLink = webLink;
		this.category = category;
		this.createdDate = createdDate;
		this.updatedTs = updatedTs;
		this.createdBy = createdBy;
		this.status = status;
		this.likeCount = likeCount;
		this.spamCount = spamCount;
		this.joinCount = joinCount;
		this.unlikeCount = unlikeCount;
		this.imageId = imageId;
		this.cDate = cDate;
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "gossip_id", unique = true, nullable = false)
	public Integer getGossipId() {
		return gossipId;
	}

	public void setGossipId(Integer gossipId) {
		this.gossipId = gossipId;
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
	@Column(name = "web_link")
	public String getWebLink() {
		return webLink;
	}

	public void setWebLink(String webLink) {
		this.webLink = webLink;
	}

	@Column(name = "category")
	public Integer getCategory() {
		return category;
	}

	public void setCategory(Integer category) {
		this.category = category;
	}
	@Column(name = "created_date")
	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
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
	@Column(name = "join_count")
	public Integer getJoinCount() {
		return joinCount;
	}

	public void setJoinCount(Integer joinCount) {
		this.joinCount = joinCount;
	}
	@Column(name = "unlike_count")
	public Integer getUnlikeCount() {
		return unlikeCount;
	}

	public void setUnlikeCount(Integer unlikeCount) {
		this.unlikeCount = unlikeCount;
	}
	@Column(name = "image_id")
	public String getImageId() {
		return imageId;
	}

	public void setImageId(String imageId) {
		this.imageId = imageId;
	}
	

}


