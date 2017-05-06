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
@Table(name = "chatting",  uniqueConstraints = @UniqueConstraint(columnNames = "created_ts"))
@JsonInclude(Include.NON_NULL)
public class Chatting implements java.io.Serializable{
	
	@Column(name = "sender_id")
	private Integer senderId;
	
	@Column(name = "receiver_id")
	private Integer receiverId;
	
	@Column(name = "status")
	private Integer status;
	
	@Column(name = "message" )
	private String message;
	
	@Id
	@Column(name = "created_ts" , unique = true, nullable = false)
	private Date createdTs;
	
	@Transient
	private String cDate;
	
	
	public Chatting(){
		
	}

	public Chatting(Integer senderId, Integer receiverId, Integer status, String message, Date createdTs,
			String cDate) {
		super();
		this.senderId = senderId;
		this.receiverId = receiverId;
		this.status = status;
		this.message = message;
		this.createdTs = createdTs;
		this.cDate = cDate;
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

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Date getCreatedTs() {
		return createdTs;
	}

	public void setCreatedTs(Date createdTs) {
		this.createdTs = createdTs;
	}

	public String getcDate() {
		return cDate;
	}

	public void setcDate(String cDate) {
		this.cDate = cDate;
	}
	
	
	

}
