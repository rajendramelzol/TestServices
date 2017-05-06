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
@Table(name = "groups",  uniqueConstraints = @UniqueConstraint(columnNames = "group_id"))
@JsonInclude(Include.NON_NULL)
public class Group {
	private Integer groupId;
	private String title;
	private String description;
	private Integer category;
	private Integer createdBy;
	private Date createdTs;
	private Date updatedTs;
	private String imageId;
	private Integer status;
	private String fDate;
	
	

	@Transient
	public String getfDate() {
		return fDate;
	}

	public void setfDate(String fDate) {
		this.fDate = fDate;
	}

	
	

	public Group(){
		
	}
	
	
	public Group(Integer groupId, String title, String description, Integer category, Integer createdBy, Date createdTs,
			Date updatedTs) {
		super();
		this.groupId = groupId;
		this.title = title;
		this.description = description;
		this.category = category;
		this.createdBy = createdBy;
		this.createdTs = createdTs;
		this.updatedTs = updatedTs;
	}




	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "group_id", unique = true, nullable = false)
	public Integer getGroupId() {
		return groupId;
	}
	public void setGroupId(Integer groupId) {
		this.groupId = groupId;
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
	@Column(name="category")
	public Integer getCategory() {
		return category;
	}
	public void setCategory(Integer category) {
		this.category = category;
	}

	@Column(name = "created_by")
	public Integer getCreatedBy() {
		return createdBy;
	}
	public void setCreatedBy(Integer createdBy) {
		this.createdBy = createdBy;
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
	@Column(name = "image_id")
	public String getImageId() {
		return imageId;
	}

	public void setImageId(String imageId) {
		this.imageId = imageId;
	}
	@Column(name = "status")
	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}
	
	
}
