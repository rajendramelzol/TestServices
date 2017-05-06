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
@Table(name = "blogs",  uniqueConstraints = @UniqueConstraint(columnNames = "blog_id"))
@JsonInclude(Include.NON_NULL)
public class Blogs implements Serializable{
	
	private Integer blogId;
	private String title;
	private String description;
	private String webLink;
	private Date createdTs;
	private Date updatedTs;
	private Integer createdBy;
	private Integer status;
	private String imageId;
	private Integer likeCount;
	private Integer spamCount;
	private String fDate;
	private Integer categoryId;

	
	@Transient
	public String getfDate() {
		return fDate;
	}
	public void setfDate(String fDate) {
		this.fDate = fDate;
	}
	public Blogs(){
		
	}
	

	public Blogs(Integer blogId, String title, String description, String webLink, Date createdTs, Date updatedTs,
			Integer createdBy, Integer status, String imageId, Integer likeCount, Integer spamCount, String fDate,
			Integer categoryId) {
		super();
		this.blogId = blogId;
		this.title = title;
		this.description = description;
		this.webLink = webLink;
		this.createdTs = createdTs;
		this.updatedTs = updatedTs;
		this.createdBy = createdBy;
		this.status = status;
		this.imageId = imageId;
		this.likeCount = likeCount;
		this.spamCount = spamCount;
		this.fDate = fDate;
		this.categoryId = categoryId;
	}
	
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "blog_id", unique = true, nullable = false)
	public Integer getBlogId() {
		return blogId;
	}

	public void setBlogId(Integer blogId) {
		this.blogId = blogId;
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
	@Column(name = "image_id")
	public String getImageId() {
		return imageId;
	}
	public void setImageId(String imageId) {
		this.imageId = imageId;
	}
	
	@Column(name = "category")
	public Integer getCategoryId() {
		return categoryId;
	}
	public void setCategoryId(Integer categoryId) {
		this.categoryId = categoryId;
	}	
	
	
	
}
