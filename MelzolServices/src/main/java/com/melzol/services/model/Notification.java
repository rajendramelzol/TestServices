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
@Table(name = "notification",  uniqueConstraints = @UniqueConstraint(columnNames = "created_ts"))
@JsonInclude(Include.NON_NULL)
public class Notification implements java.io.Serializable  {
	
	@Column(name = "sender_id")
	private Integer senderId;
	
	@Column(name = "receiver_id")
	private Integer receiverId;
	
	@Column(name="message")
	private String message;
	
	@Column(name = "type")
	private Integer type;
	
	@Column(name = "ref_type")
	private Integer refType;
	
	@Column(name = "ref_id")
	private Integer refId;
	
	@Column(name = "status")
	private Integer status;
	
	@Id
	@Column(name = "created_ts" , unique = true, nullable = false)
	private Date createdTs;
	
	@Transient
	private String cDate;
	
	
	
	public String getcDate() {
		return cDate;
	}
	public void setcDate(String cDate) {
		this.cDate = cDate;
	}



	public Notification(){
		
	}

	
	
	public Notification(Integer senderId, Integer receiverId, String message, Integer type, Integer refType,
			Integer refId, Integer status, Date createdTs) {
		super();
		this.senderId = senderId;
		this.receiverId = receiverId;
		this.message = message;
		this.type = type;
		this.refType = refType;
		this.refId = refId;
		this.status = status;
		this.createdTs = createdTs;
	}


	
	public Integer getSenderId() {
		return senderId;
	}

	public void setSenderId(Integer senderId) {
		this.senderId = senderId;
	}
	
	public Integer getReceiverId() {
		return receiverId;
	}

	public void setReceiverId(Integer receiverId) {
		this.receiverId = receiverId;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}
	public Integer getRefType() {
		return refType;
	}

	public void setRefType(Integer refType) {
		this.refType = refType;
	}
	public Integer getRefId() {
		return refId;
	}

	public void setRefId(Integer refId) {
		this.refId = refId;
	}
	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}
	public Date getCreatedTs() {
		return createdTs;
	}

	public void setCreatedTs(Date createdTs) {
		this.createdTs = createdTs;
	}
	
	

}
