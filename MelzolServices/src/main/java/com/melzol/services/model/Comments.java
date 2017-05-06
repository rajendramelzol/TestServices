package com.melzol.services.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.persistence.UniqueConstraint;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@Entity
@Table(name = "comments",  uniqueConstraints = @UniqueConstraint(columnNames = "created_ts"))
@JsonInclude(Include.NON_NULL)
public class Comments implements java.io.Serializable{
	
	private String comment;
	private Integer refId;
	private Integer refType;
	private Integer commentedBy;
	private Date createdTs;
	private String cDate;
	
	@Transient
	public String getcDate() {
		return cDate;
	}

	public void setcDate(String cDate) {
		this.cDate = cDate;
	}

	public Comments(){
		
	}

	public Comments(String comment, Integer refId, Integer refType, Integer commentedBy, Date createdTs) {
		//this.id = id;
		this.comment = comment;
		this.refId = refId;
		this.refType = refType;
		this.commentedBy = commentedBy;
		this.createdTs = createdTs;
	}
	/*@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}*/
	@Column(name = "comment")
	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}
	@Column(name = "ref_id")
	public Integer getRefId() {
		return refId;
	}

	public void setRefId(Integer refId) {
		this.refId = refId;
	}
	@Column(name = "ref_type")
	public Integer getRefType() {
		return refType;
	}

	public void setRefType(Integer refType) {
		this.refType = refType;
	}
	@Column(name = "commented_by")
	public Integer getCommentedBy() {
		return commentedBy;
	}

	public void setCommentedBy(Integer commentedBy) {
		this.commentedBy = commentedBy;
	}

	@Id
	@Column(name = "created_ts", unique = true, nullable = false)
	public Date getCreatedTs() {
		return createdTs;
	}

	public void setCreatedTs(Date createdTs) {
		this.createdTs = createdTs;
	}
	
	

}
