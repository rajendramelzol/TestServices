package com.melzol.services.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@Entity
@Table(name = "member_neighbourhoods",  uniqueConstraints = @UniqueConstraint(columnNames = "created_ts"))
@JsonInclude(Include.NON_NULL)
public class MemberNeighbourhoods implements Serializable{
	
	@Column(name = "member_id")
	private Integer memberId;
	
	@Column(name = "latitude")
	private Double latitude;
	
	@Column(name = "longitude")
	private Double longitude;
	
	@Column(name = "address_refid" )
	private Integer addressRefId;
	
	@Id
	@Column(name = "created_ts" , unique = true, nullable = false)
	private Date createdTs;
	
	@Column(name = "place" )
	private String place;
	
	@Column(name = "city" )
	private String city;
	
	@Column(name = "state" )
	private String state;
	
	@Column(name = "country" )
	private String country;
	
	@Column(name = "pincode" )
	private String pincode;
	
	public MemberNeighbourhoods(){
		
	}


	public MemberNeighbourhoods(Integer memberId, Double latitude, Double longitude, Integer addressRefId,
			Date createdTs, String place, String city, String state, String country, String pincode) {
		super();
		this.memberId = memberId;
		this.latitude = latitude;
		this.longitude = longitude;
		this.addressRefId = addressRefId;
		this.createdTs = createdTs;
		this.place = place;
		this.city = city;
		this.state = state;
		this.country = country;
		this.pincode = pincode;
	}



	public Date getCreatedTs() {
		return createdTs;
	}


	public void setCreatedTs(Date createdTs) {
		this.createdTs = createdTs;
	}


	public Integer getMemberId() {
		return memberId;
	}


	public void setMemberId(Integer memberId) {
		this.memberId = memberId;
	}


	public Double getLatitude() {
		return latitude;
	}


	public void setLatitude(Double latitude) {
		this.latitude = latitude;
	}


	public Double getLongitude() {
		return longitude;
	}


	public void setLongitude(Double longitude) {
		this.longitude = longitude;
	}


	public Integer getAddressRefId() {
		return addressRefId;
	}


	public void setAddressRefId(Integer addressRefId) {
		this.addressRefId = addressRefId;
	}


	public String getPlace() {
		return place;
	}


	public void setPlace(String place) {
		this.place = place;
	}


	public String getCity() {
		return city;
	}


	public void setCity(String city) {
		this.city = city;
	}


	public String getState() {
		return state;
	}


	public void setState(String state) {
		this.state = state;
	}


	public String getCountry() {
		return country;
	}


	public void setCountry(String country) {
		this.country = country;
	}


	public String getPincode() {
		return pincode;
	}


	public void setPincode(String pincode) {
		this.pincode = pincode;
	}
	

}
