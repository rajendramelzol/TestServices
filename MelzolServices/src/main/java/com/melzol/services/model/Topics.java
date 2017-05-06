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
@Table(name = "topics",  uniqueConstraints = @UniqueConstraint(columnNames = "topic_id"))
@JsonInclude(Include.NON_NULL)
public class Topics implements java.io.Serializable{
	
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "topic_id" , unique = true, nullable = false)
	private Integer topicId;
	
	@Column(name = "title")
	private String title;
	
	@Column(name = "description")
	private String description;
	
	@Column(name = "ref_id")
	private Integer refId;
	
	@Column(name = "ref_type")
	private Integer refType;
	
	@Column(name = "status")
	private Integer status;
	
	@Column(name = "created_by" )
	private Integer createdBy;
	

	@Column(name = "created_ts")
	private Date createdTs;
	

	public Topics(){
		
	}

	

	public Topics(Integer topicId, String title, String description, Integer refId, Integer refType, Integer status,
			Integer createdBy, Date createdTs) {
		super();
		this.topicId = topicId;
		this.title = title;
		this.description = description;
		this.refId = refId;
		this.refType = refType;
		this.status = status;
		this.createdBy = createdBy;
		this.createdTs = createdTs;
	}



	public Integer getTopicId() {
		return topicId;
	}

	public void setTopicId(Integer topicId) {
		this.topicId = topicId;
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

	public Integer getRefId() {
		return refId;
	}

	public void setRefId(Integer refId) {
		this.refId = refId;
	}

	public Integer getRefType() {
		return refType;
	}

	public void setRefType(Integer refType) {
		this.refType = refType;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Integer getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(Integer createdBy) {
		this.createdBy = createdBy;
	}

	public Date getCreatedTs() {
		return createdTs;
	}

	public void setCreatedTs(Date createdTs) {
		this.createdTs = createdTs;
	}
	
	

}
