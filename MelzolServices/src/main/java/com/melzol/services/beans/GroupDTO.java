package com.melzol.services.beans;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.melzol.services.model.Members;
@JsonInclude(Include.NON_NULL)
public class GroupDTO {
	//for Group
	private Integer groupId;
	private String title;
	private String description;
	private Integer category;
	private Integer createdBy;
	private Date createdTs;
	private Date updatedTs;
	private String imageId;
	private Integer status;
	private String fDate;
	private Integer memCount;
	private Integer joined;
	private String userName;
	private String profilePic;
	private String gcmKey;
	
	List<Members> groupMembers;
	List<TopicsDTO> topics;
	
	public Integer getGroupId() {
		return groupId;
	}
	public void setGroupId(Integer groupId) {
		this.groupId = groupId;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Integer getCategory() {
		return category;
	}
	public void setCategory(Integer category) {
		this.category = category;
	}
	public Integer getCreatedBy() {
		return createdBy;
	}
	public void setCreatedBy(Integer createdBy) {
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
	public String getImageId() {
		return imageId;
	}
	public void setImageId(String imageId) {
		this.imageId = imageId;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public String getfDate() {
		return fDate;
	}
	public void setfDate(String fDate) {
		this.fDate = fDate;
	}
	public Integer getMemCount() {
		return memCount;
	}
	public void setMemCount(Integer memCount) {
		this.memCount = memCount;
	}
	public Integer getJoined() {
		return joined;
	}
	public void setJoined(Integer joined) {
		this.joined = joined;
	}
	public List<Members> getGroupMembers() {
		return groupMembers;
	}
	public void setGroupMembers(List<Members> groupMembers) {
		this.groupMembers = groupMembers;
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
	public String getGcmKey() {
		return gcmKey;
	}
	public void setGcmKey(String gcmKey) {
		this.gcmKey = gcmKey;
	}
	public List<TopicsDTO> getTopics() {
		return topics;
	}
	public void setTopics(List<TopicsDTO> topics) {
		this.topics = topics;
	}
	


}
