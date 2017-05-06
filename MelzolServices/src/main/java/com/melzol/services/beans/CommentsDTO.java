package com.melzol.services.beans;

import java.sql.Timestamp;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
@JsonInclude(Include.NON_NULL)
public class CommentsDTO {
	
	private String comment;
	private Integer refId;
	private Integer refType;
	private Integer commentedBy;
	private String commentedName;
	private Timestamp createdTs;
	private String cDate;
	private String userName;
	private String profilePic;
	private String createdDate;
	
	
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
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
	public Integer getCommentedBy() {
		return commentedBy;
	}
	public void setCommentedBy(Integer commentedBy) {
		this.commentedBy = commentedBy;
	}
	public String getCommentedName() {
		return commentedName;
	}
	public void setCommentedName(String commentedName) {
		this.commentedName = commentedName;
	}
	
	public Timestamp getCreatedTs() {
		return createdTs;
	}
	public void setCreatedTs(Timestamp createdTs) {
		this.createdTs = createdTs;
	}
	public String getcDate() {
		return cDate;
	}
	public void setcDate(String cDate) {
		this.cDate = cDate;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getProfilePic() {
		return profilePic;
	}
	public void setProfilePic(String profilePic) {
		this.profilePic = profilePic;
	}
	public String getCreatedDate() {
		return createdDate;
	}
	public void setCreatedDate(String createdDate) {
		this.createdDate = createdDate;
	}
	
	
	

}
