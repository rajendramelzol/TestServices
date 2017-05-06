package com.melzol.services.beans;

import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.melzol.services.model.Comments;
@JsonInclude(Include.NON_NULL)
public class GossipsDTO {
	
	private Integer gossipId;
	private String title;
	private String description;
	private String webLink;
	private Integer category;
	private Date createdDate;
	private Integer createdBy;
	private String gcmKey;
	private Integer status;
	private Integer likeCount;
	private Integer spamCount;
	private Integer joinCount;
	private Integer unlikeCount;
	private String ownerName;
	private String imageId;
	private Integer activityType;
	private Integer opinion;
	
	List<CommentsDTO> comments;
	
	
	
	public Integer getGossipId() {
		return gossipId;
	}
	public void setGossipId(Integer gossipId) {
		this.gossipId = gossipId;
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
	
	public String getWebLink() {
		return webLink;
	}
	public void setWebLink(String webLink) {
		this.webLink = webLink;
	}
	public Integer getCategory() {
		return category;
	}
	public void setCategory(Integer category) {
		this.category = category;
	}
	public Date getCreatedDate() {
		return createdDate;
	}
	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
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
	public Integer getLikeCount() {
		return likeCount;
	}
	public void setLikeCount(Integer likeCount) {
		this.likeCount = likeCount;
	}
	public Integer getSpamCount() {
		return spamCount;
	}
	public void setSpamCount(Integer spamCount) {
		this.spamCount = spamCount;
	}
	
	public Integer getJoinCount() {
		return joinCount;
	}
	public void setJoinCount(Integer joinCount) {
		this.joinCount = joinCount;
	}
	public Integer getUnlikeCount() {
		return unlikeCount;
	}
	public void setUnlikeCount(Integer unlikeCount) {
		this.unlikeCount = unlikeCount;
	}
	public String getOwnerName() {
		return ownerName;
	}
	public void setOwnerName(String ownerName) {
		this.ownerName = ownerName;
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
	public String getGcmKey() {
		return gcmKey;
	}
	public void setGcmKey(String gcmKey) {
		this.gcmKey = gcmKey;
	}
	public List<CommentsDTO> getComments() {
		return comments;
	}
	public void setComments(List<CommentsDTO> comments) {
		this.comments = comments;
	}
	
	
	

}
