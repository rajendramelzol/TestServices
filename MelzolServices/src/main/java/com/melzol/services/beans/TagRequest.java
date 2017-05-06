package com.melzol.services.beans;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.melzol.services.model.Members;

@JsonInclude(Include.NON_NULL)
public class TagRequest {
	private Integer memberId;
	private String userName;
	private String fullName;
	private String mobile;
	private Integer age;
	private String company;
	private Integer sex;
	private Integer addressRefid;
	private String profession;
	private String interestHobbies;
	private String about;
	private String statusMessage;
	private String profilePic;
	private Integer updatedBy;
	private Date updatedTs;
	private String gcmKey;
	private Integer status;
	private Double latitude;
	private Double longitude;
	private String place;
	private String city;
	private String state;
	private String country;
	private String pincode;
	private Integer taggedNeighCnt;
	private Integer activitiyCount;
	private Integer eventCount;
	private Integer totNeighCnt;
	private float popularity;
	private float rating; 
	private Integer tagStatus;
	private Integer requestStatus;
	List<MemberDTO> popularMembers= new ArrayList<MemberDTO>() ;
	List<Members> sentTagRequest= new ArrayList<Members>() ;
	List<Members> TaggedMembers= new ArrayList<Members>() ;
	public Integer getMemberId() {
		return memberId;
	}
	public void setMemberId(Integer memberId) {
		this.memberId = memberId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getFullName() {
		return fullName;
	}
	public void setFullName(String fullName) {
		this.fullName = fullName;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public Integer getAge() {
		return age;
	}
	public void setAge(Integer age) {
		this.age = age;
	}
	public String getCompany() {
		return company;
	}
	public void setCompany(String company) {
		this.company = company;
	}
	public Integer getSex() {
		return sex;
	}
	public void setSex(Integer sex) {
		this.sex = sex;
	}
	public Integer getAddressRefid() {
		return addressRefid;
	}
	public void setAddressRefid(Integer addressRefid) {
		this.addressRefid = addressRefid;
	}
	public String getProfession() {
		return profession;
	}
	public void setProfession(String profession) {
		this.profession = profession;
	}
	public String getInterestHobbies() {
		return interestHobbies;
	}
	public void setInterestHobbies(String interestHobbies) {
		this.interestHobbies = interestHobbies;
	}
	public String getAbout() {
		return about;
	}
	public void setAbout(String about) {
		this.about = about;
	}
	public String getStatusMessage() {
		return statusMessage;
	}
	public void setStatusMessage(String statusMessage) {
		this.statusMessage = statusMessage;
	}
	public String getProfilePic() {
		return profilePic;
	}
	public void setProfilePic(String profilePic) {
		this.profilePic = profilePic;
	}
	public Integer getUpdatedBy() {
		return updatedBy;
	}
	public void setUpdatedBy(Integer updatedBy) {
		this.updatedBy = updatedBy;
	}
	public Date getUpdatedTs() {
		return updatedTs;
	}
	public void setUpdatedTs(Date updatedTs) {
		this.updatedTs = updatedTs;
	}
	public String getGcmKey() {
		return gcmKey;
	}
	public void setGcmKey(String gcmKey) {
		this.gcmKey = gcmKey;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
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
	public Integer getTaggedNeighCnt() {
		return taggedNeighCnt;
	}
	public void setTaggedNeighCnt(Integer taggedNeighCnt) {
		this.taggedNeighCnt = taggedNeighCnt;
	}
	public Integer getActivitiyCount() {
		return activitiyCount;
	}
	public void setActivitiyCount(Integer activitiyCount) {
		this.activitiyCount = activitiyCount;
	}
	public Integer getEventCount() {
		return eventCount;
	}
	public void setEventCount(Integer eventCount) {
		this.eventCount = eventCount;
	}
	public Integer getTotNeighCnt() {
		return totNeighCnt;
	}
	public void setTotNeighCnt(Integer totNeighCnt) {
		this.totNeighCnt = totNeighCnt;
	}
	public float getPopularity() {
		return popularity;
	}
	public void setPopularity(float popularity) {
		this.popularity = popularity;
	}
	public float getRating() {
		return rating;
	}
	public void setRating(float rating) {
		this.rating = rating;
	}
	public Integer getTagStatus() {
		return tagStatus;
	}
	public void setTagStatus(Integer tagStatus) {
		this.tagStatus = tagStatus;
	}
	public Integer getRequestStatus() {
		return requestStatus;
	}
	public void setRequestStatus(Integer requestStatus) {
		this.requestStatus = requestStatus;
	}
	public List<MemberDTO> getPopularMembers() {
		return popularMembers;
	}
	public void setPopularMembers(List<MemberDTO> popularMembers) {
		this.popularMembers = popularMembers;
	}
	public List<Members> getSentTagRequest() {
		return sentTagRequest;
	}
	public void setSentTagRequest(List<Members> sentTagRequest) {
		this.sentTagRequest = sentTagRequest;
	}
	public List<Members> getTaggedMembers() {
		return TaggedMembers;
	}
	public void setTaggedMembers(List<Members> taggedMembers) {
		TaggedMembers = taggedMembers;
	}	

}
