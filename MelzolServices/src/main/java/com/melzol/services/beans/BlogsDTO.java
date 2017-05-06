package com.melzol.services.beans;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.melzol.services.model.Comments;
@JsonInclude(Include.NON_NULL)
public class BlogsDTO {
	
	private Integer blogId;
	private String title;
	private String description;
	private String webLink;
	private Date createdTs;
	private Integer createdBy;
	private Integer status;
	private Integer likeCount;
	private Integer spamCount;
	private String imageId;
	private String ownerName;
	private String fDate;
	private Integer opinion;
	private String ownerImage;
	private String gcmKey;
	private Integer categoryId;
	private Integer subscribeStatus;
	
	List<CommentsDTO> comments=new ArrayList<CommentsDTO>();
	
	
	public Integer getBlogId() {
		return blogId;
	}
	public void setBlogId(Integer blogId) {
		this.blogId = blogId;
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
	public Date getCreatedTs() {
		return createdTs;
	}
	public void setCreatedTs(Date createdTs) {
		this.createdTs = createdTs;
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
	public String getImageId() {
		return imageId;
	}
	public void setImageId(String imageId) {
		this.imageId = imageId;
	}
	public String getOwnerName() {
		return ownerName;
	}
	public void setOwnerName(String ownerName) {
		this.ownerName = ownerName;
	}
	public String getfDate() {
		return fDate;
	}
	public void setfDate(String fDate) {
		this.fDate = fDate;
	}
	public Integer getOpinion() {
		return opinion;
	}
	public void setOpinion(Integer opinion) {
		this.opinion = opinion;
	}
	public String getOwnerImage() {
		return ownerImage;
	}
	public void setOwnerImage(String ownerImage) {
		this.ownerImage = ownerImage;
	}
	public String getGcmKey() {
		return gcmKey;
	}
	public void setGcmKey(String gcmKey) {
		this.gcmKey = gcmKey;
	}
	public Integer getCategoryId() {
		return categoryId;
	}
	public void setCategoryId(Integer categoryId) {
		this.categoryId = categoryId;
	}
	public List<CommentsDTO> getComments() {
		return comments;
	}
	public void setComments(List<CommentsDTO> comments) {
		this.comments = comments;
	}
	public Integer getSubscribeStatus() {
		return subscribeStatus;
	}
	public void setSubscribeStatus(Integer subscribeStatus) {
		this.subscribeStatus = subscribeStatus;
	}
	
	
	
	
	

}
