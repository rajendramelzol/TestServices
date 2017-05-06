package com.melzol.services.model;

import static javax.persistence.GenerationType.IDENTITY;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@Entity
@Table(name = "services", uniqueConstraints = @UniqueConstraint(columnNames = "service_id"))
@JsonInclude(Include.NON_NULL)
public class Services implements java.io.Serializable {
	
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "service_id", unique = true, nullable = false)
	private Integer serviceId;
	@Column(name = "name")
	private String name;
	@Column(name = "category")
	private int category;
	@Column(name = "sub_category")
	private int subCategory; 
	@Column(name = "description")
	private String descCompany;
	@Column(name = "avg_rating")
	private Integer avgRating;
	@Column(name = "votes")
	private Integer votes;
	@Column(name = "working_hrs")
	private String workingHrs;
	@Column(name = "address")
	private String address;
	@Column(name = "map_directions")
	private String mapDirections;
	@Column(name = "website")
	private String website;
	@Column(name = "mobile")
	private String mobile;
	@Column(name = "add_phone")
	private String addPhone;
	@Column(name = "images_path")
	private String imagePath;
	@Column(name = "created_by")
	private String createdBy;
	@Column(name = "created_ts")
	private Date createdTs;
	@Column(name = "updated_ts")
	private Date updatedTs; 
	@Column(name = "type")
	private Integer type;
	@Column(name = "email_id")
	private String email;
	@Column(name = "latitude")
	private Double latitude;
	@Column(name = "longitude")
	private Double longitude;
	@Column(name = "status")
	private Integer status;
	
	
	public Services(){
		
	}
	
	public Services(Integer serviceId, String name, int category, int subCategory, String descCompany,
			Integer avgRating, Integer votes, String workingHrs, String address, String mapDirections, String website,
			 String mobile, String addPhone, String imagePath, String createdBy, Date createdTs,
			Date updatedTs, Integer type, String email, Double latitude, Double longitude, Integer status) {
		this.serviceId = serviceId;
		this.name = name;
		this.category = category;
		this.subCategory = subCategory;
		this.descCompany = descCompany;
		this.avgRating = avgRating;
		this.votes = votes;
		this.workingHrs = workingHrs;
		this.address = address;
		this.mapDirections = mapDirections;
		this.website = website;
		this.mobile = mobile;
		this.addPhone = addPhone;
		this.imagePath = imagePath;
		this.createdBy = createdBy;
		this.createdTs = createdTs;
		this.updatedTs = updatedTs;
		this.type = type;
		this.email = email;
		this.latitude = latitude;
		this.longitude = longitude;
		this.status = status;
	}
	
	public Integer getServiceId() {
		return serviceId;
	}

	public void setServiceId(Integer serviceId) {
		this.serviceId = serviceId;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public int getCategory() {
		return category;
	}

	public void setCategory(int category) {
		this.category = category;
	}
	
	public int getSubCategory() {
		return subCategory;
	}

	public void setSubCategory(int subCategory) {
		this.subCategory = subCategory;
	}
	
	public String getDescCompany() {
		return descCompany;
	}

	public void setDescCompany(String descCompany) {
		this.descCompany = descCompany;
	}
	
	public Integer getAvgRating() {
		return avgRating;
	}

	public void setAvgRating(Integer avgRating) {
		this.avgRating = avgRating;
	}
	
	public Integer getVotes() {
		return votes;
	}

	public void setVotes(Integer votes) {
		this.votes = votes;
	}
	
	public String getWorkingHrs() {
		return workingHrs;
	}

	public void setWorkingHrs(String workingHrs) {
		this.workingHrs = workingHrs;
	}
	
	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}
	
	public String getMapDirections() {
		return mapDirections;
	}

	public void setMapDirections(String mapDirections) {
		this.mapDirections = mapDirections;
	}
	
	public String getWebsite() {
		return website;
	}

	public void setWebsite(String website) {
		this.website = website;
	}
	
	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	
	public String getAddPhone() {
		return addPhone;
	}

	public void setAddPhone(String addPhone) {
		this.addPhone = addPhone;
	}
	
	public String getImagePath() {
		return imagePath;
	}

	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
	}
	
	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
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
	
	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}
	
	
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
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
	
	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}
	
	
	
	
}