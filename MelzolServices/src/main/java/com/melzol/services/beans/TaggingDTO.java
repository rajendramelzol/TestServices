package com.melzol.services.beans;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
@JsonInclude(Include.NON_NULL)
public class TaggingDTO {
	
	private List<NotificationDTO> notification;

	public List<NotificationDTO> getNotification() {
		return notification;
	}

	public void setNotification(List<NotificationDTO> notification) {
		this.notification = notification;
	}

	
	
	

}
