package com.melzol.services.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.melzol.services.beans.NotificationDTO;
import com.melzol.services.dao.NotificationDAO;
import com.melzol.services.model.Notification;

@Repository
@Service("notificationService")
@Transactional(propagation=Propagation.SUPPORTS, readOnly=true)
public class NotificationServiceImpl implements NotificationService {
	private NotificationDAO notificationDAO;

	public NotificationDAO getNotificationDAO() {
		return notificationDAO;
	}
	@Autowired
	public void setNotificationDAO(NotificationDAO notificationDAO) {
		this.notificationDAO = notificationDAO;
	}
	public void addNotification(Notification notification) {
		notificationDAO.save(notification);
		
	}
	public Notification searchById(Integer id) {
		return notificationDAO.findById(id);
	}
	public List<NotificationDTO> searchAllNotification(int memid, int page) {
		return notificationDAO.findAllNotification(memid,page);
	}
	public void changeStatus(int memId) {
		notificationDAO.changeStatus(memId);
	}

	public Long searchNotificationCount(int memId) {
	
		return notificationDAO.findNotificationCount(memId);
	}
	
	
	
	
	

}
