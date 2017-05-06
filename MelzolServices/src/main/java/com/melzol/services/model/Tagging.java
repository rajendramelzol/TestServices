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
@Table(name = "tagging",  uniqueConstraints = @UniqueConstraint(columnNames = "created_ts"))
@JsonInclude(Include.NON_NULL)
public class Tagging implements java.io.Serializable{
	
	@Column(name = "melzol_id")
	private Integer melzolId;
	
	@Column(name = "tag_melzol_id")
	private Integer tagMelzolId;
	
	@Column(name = "status")
	private Integer status;
	
	@Id
	@Column(name = "created_ts" , unique = true, nullable = false)
	private Date createdTs;
	
	@Column(name = "updated_ts" )
	private Date updatedTs;
	
	@Transient
	private String cDate;
	
	@Transient
	private String uDate;
	
	
	
	public String getcDate() {
		return cDate;
	}

	public void setcDate(String cDate) {
		this.cDate = cDate;
	}
	public String getuDate() {
		return uDate;
	}

	public void setuDate(String uDate) {
		this.uDate = uDate;
	}

	public Tagging(){
		
	}

	public Tagging(Integer melzolId, Integer tagMelzolId, Integer status, Date createdTs, Date updatedTs) {
		super();
		this.melzolId = melzolId;
		this.tagMelzolId = tagMelzolId;
		this.status = status;
		this.createdTs = createdTs;
		this.updatedTs = updatedTs;
	}

	public Integer getMelzolId() {
		return melzolId;
	}

	public void setMelzolId(Integer melzolId) {
		this.melzolId = melzolId;
	}

	public Integer getTagMelzolId() {
		return tagMelzolId;
	}

	public void setTagMelzolId(Integer tagMelzolId) {
		this.tagMelzolId = tagMelzolId;
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

	public Date getUpdatedTs() {
		return updatedTs;
	}

	public void setUpdatedTs(Date updatedTs) {
		this.updatedTs = updatedTs;
	}
	
	
	
	

}
