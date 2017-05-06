package com.melzol.services.beans;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
@JsonInclude(Include.NON_NULL)
public class NotificationDTO {
	
	    private Integer senderId;
	    private Integer receiverId;
	    private String message;
	    private Integer type;
	    private Integer refType;
	    private Integer refId;
	    private Integer status;
	    private Integer createdBy;
	    private Date createdTs;
	    private String cDate;
	    private String uDate;
	    private String gcmKey;
	    private String userName;
	    private String profilePic;
	    
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
		public Integer getCreatedBy() {
			return createdBy;
		}
		public void setCreatedBy(Integer createdBy) {
			this.createdBy = createdBy;
		}
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
		public String getGcmKey() {
			return gcmKey;
		}
		public void setGcmKey(String gcmKey) {
			this.gcmKey = gcmKey;
		}
		public String getUserName() {
			return userName;
		}
		public void setUserName(String userName) {
			this.userName = userName;
		}
		public Date getCreatedTs() {
			return createdTs;
		}
		public void setCreatedTs(Date createdTs) {
			this.createdTs = createdTs;
		}
		public String getProfilePic() {
			return profilePic;
		}
		public void setProfilePic(String profilePic) {
			this.profilePic = profilePic;
		}
	    
	    

}
