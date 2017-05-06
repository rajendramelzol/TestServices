package com.melzol.services.service;

import java.util.List;

import com.melzol.services.beans.NotificationDTO;
import com.melzol.services.model.Notification;

public interface NotificationService {

	public void addNotification(Notification notification);

	public Notification searchById(Integer id);

	public List<NotificationDTO> searchAllNotification(int memid, int page);

	public void changeStatus(int memId);

	public Long searchNotificationCount(int memId);

}
