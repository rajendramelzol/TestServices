package com.melzol.services.beans;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.melzol.services.model.WorkingDays;

@JsonInclude(Include.NON_NULL)
public class ServiceDTO {
	 private Integer serviceId;
	    private String name;
	    private int category;
	    private int subCategory;
	    private String descCompany;
	    private Integer avgRating;
	    private Integer votes;
	    private String workingHrs;
	    private String address;
	    private String mapDirections;
	    private String website;
	    private String phone;
	    private String mobile;
	    private String addPhone;
	    private String createdBy;
	    private Date createdTs;
		private Date updatedTs; 
	    private Integer pincode;
	    private Integer type;
	    private String email;
	    private Integer subType;
	    private Double latitude;
	    private Double longitude;
	    private Integer status;
	    private Integer bookCount;
	    private String ownerName;
	    private String fDate;
	    private String imageId;
	    public String getImageId() {
			return imageId;
		}
		public void setImageId(String imageId) {
			this.imageId = imageId;
		}
		private Integer workingdaysRefid;
	    List<WorkingDays> wrk=new ArrayList<WorkingDays>();
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
		public String getPhone() {
			return phone;
		}
		public void setPhone(String phone) {
			this.phone = phone;
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
		public Integer getPincode() {
			return pincode;
		}
		public void setPincode(Integer pincode) {
			this.pincode = pincode;
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
		public Integer getSubType() {
			return subType;
		}
		public void setSubType(Integer subType) {
			this.subType = subType;
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
		public Integer getBookCount() {
			return bookCount;
		}
		public void setBookCount(Integer bookCount) {
			this.bookCount = bookCount;
		}
		public String getOwnerName() {
			return ownerName;
		}
		public void setOwnerName(String ownerName) {
			this.ownerName = ownerName;
		}
		public String getfDate() {
			return fDate;
		}
		public void setfDate(String fDate) {
			this.fDate = fDate;
		}
		public Integer getWorkingdaysRefid() {
			return workingdaysRefid;
		}
		public void setWorkingdaysRefid(Integer workingdaysRefid) {
			this.workingdaysRefid = workingdaysRefid;
		}
		public List<WorkingDays> getWrk() {
			return wrk;
		}
		public void setWrk(List<WorkingDays> wrk) {
			this.wrk = wrk;
		}

   
}
