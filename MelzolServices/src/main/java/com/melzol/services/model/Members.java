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
@Table(name = "members",  uniqueConstraints = @UniqueConstraint(columnNames = "member_id"))
@JsonInclude(Include.NON_NULL)
public class Members implements java.io.Serializable{
	
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
	
	private String uDate;
	@Transient
	public String getuDate() {
		return uDate;
	}
	public void setuDate(String uDate) {
		this.uDate = uDate;
	}


	public Members() {
	
	}

	
	public Members(Integer memberId, String userName, String fullName, String mobile, Integer age, String company,
			Integer sex, Integer addressRefid, String profession, String interestHobbies, String about,
			String statusMessage, String profilePic, Integer updatedBy, Date updatedTs, String gcmKey, Integer status,
			Double latitude, Double longitude, String place, String city, String state, String country, String pincode) {
		super();
		this.memberId = memberId;
		this.userName = userName;
		this.fullName = fullName;
		this.mobile = mobile;
		this.age = age;
		this.company = company;
		this.sex = sex;
		this.addressRefid = addressRefid;
		this.profession = profession;
		this.interestHobbies = interestHobbies;
		this.about = about;
		this.statusMessage = statusMessage;
		this.profilePic = profilePic;
		this.updatedBy = updatedBy;
		this.updatedTs = updatedTs;
		this.gcmKey = gcmKey;
		this.status = status;
		this.latitude = latitude;
		this.longitude = longitude;
		this.place = place;
		this.city = city;
		this.state = state;
		this.country = country;
		this.pincode = pincode;
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "member_id", unique = true, nullable = false)
	public Integer getMemberId() {
		return memberId;
	}

	public void setMemberId(Integer memberId) {
		this.memberId = memberId;
	}
	@Column(name="user_name")
	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}
	@Column(name="full_name")
	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	@Column(name = "mobile")
	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	@Column(name="age")
	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}
	@Column(name="company")
	public String getCompany() {
		return company;
	}

	public void setCompany(String company) {
		this.company = company;
	}
	@Column(name="sex")
	public Integer getSex() {
		return sex;
	}

	public void setSex(Integer sex) {
		this.sex = sex;
	}

	@Column(name = "address_refid")
	public Integer getAddressRefid() {
		return addressRefid;
	}

	public void setAddressRefid(Integer addressRefid) {
		this.addressRefid = addressRefid;
	}
	@Column(name = "profession")
	public String getProfession() {
		return profession;
	}

	public void setProfession(String profession) {
		this.profession = profession;
	}
	@Column(name = "interest_hobbies")
	public String getInterestHobbies() {
		return interestHobbies;
	}

	public void setInterestHobbies(String interestHobbies) {
		this.interestHobbies = interestHobbies;
	}
	@Column(name = "about")
	public String getAbout() {
		return about;
	}

	public void setAbout(String about) {
		this.about = about;
	}
	@Column(name = "status_message")
	public String getStatusMessage() {
		return statusMessage;
	}

	public void setStatusMessage(String statusMessage) {
		this.statusMessage = statusMessage;
	}
	@Column(name = "profile_pic")
	public String getProfilePic() {
		return profilePic;
	}

	public void setProfilePic(String profilePic) {
		this.profilePic = profilePic;
	}
	@Column(name = "updated_by")
	public Integer getUpdatedBy() {
		return updatedBy;
	}

	public void setUpdatedBy(Integer updatedBy) {
		this.updatedBy = updatedBy;
	}
	@Column(name = "updated_ts")
	public Date getUpdatedTs() {
		return updatedTs;
	}

	public void setUpdatedTs(Date updatedTs) {
		this.updatedTs = updatedTs;
	}
	@Column(name = "gcm_key")
	public String getGcmKey() {
		return gcmKey;
	}


	public void setGcmKey(String gcmKey) {
		this.gcmKey = gcmKey;
	}

	@Column(name = "status")
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	@Column(name = "latitude")
	public Double getLatitude() {
		return latitude;
	}
	public void setLatitude(Double latitude) {
		this.latitude = latitude;
	}
	@Column(name = "longitude")
	public Double getLongitude() {
		return longitude;
	}
	public void setLongitude(Double longitude) {
		this.longitude = longitude;
	}
	@Column(name="place")
	public String getPlace() {
		return place;
	}

	public void setPlace(String place) {
		this.place = place;
	}
	@Column(name = "city")
	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}
	@Column(name = "state")
	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}
	@Column(name = "country")
	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	@Column(name = "pincode")
	public String getPincode() {
		return pincode;
	}

	public void setPincode(String pincode) {
		this.pincode = pincode;
	}


}
