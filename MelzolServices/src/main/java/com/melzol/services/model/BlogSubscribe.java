package com.melzol.services.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@Entity
@Table(name = "blog_subscribe")
@JsonInclude(Include.NON_NULL)
public class BlogSubscribe implements java.io.Serializable{
	
	@Id
	@Column(name = "melzol_id")
	private Integer melzolId;
	
	@Id
	@Column(name = "subscribe_id")
	private Integer subscribeId;
	
	@Column(name = "status")
	private Integer status;
	

	@Column(name = "subscribe_ts" )
	private Date subscribeTs;
	
	
	public BlogSubscribe(){
		
	}


	

	public BlogSubscribe(Integer melzolId, Integer subscribeId, Integer status, Date subscribeTs) {
		super();
		this.melzolId = melzolId;
		this.subscribeId = subscribeId;
		this.status = status;
		this.subscribeTs = subscribeTs;
	}




	public Integer getMelzolId() {
		return melzolId;
	}


	public void setMelzolId(Integer melzolId) {
		this.melzolId = melzolId;
	}


	public Integer getSubscribeId() {
		return subscribeId;
	}


	public void setSubscribeId(Integer subscribeId) {
		this.subscribeId = subscribeId;
	}


	public Integer getStatus() {
		return status;
	}


	public void setStatus(Integer status) {
		this.status = status;
	}




	public Date getSubscribeTs() {
		return subscribeTs;
	}




	public void setSubscribeTs(Date subscribeTs) {
		this.subscribeTs = subscribeTs;
	}


	

}
