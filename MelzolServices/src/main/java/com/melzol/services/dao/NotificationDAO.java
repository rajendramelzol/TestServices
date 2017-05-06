package com.melzol.services.dao;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.jdbc.core.JdbcTemplate;

import com.melzol.services.beans.NotificationDTO;
import com.melzol.services.model.Notification;

public interface NotificationDAO {
	
	public abstract JdbcTemplate getJdbcTemplate();

	public abstract void setJdbcTemplate(JdbcTemplate jdbcTemplate);

	public abstract void init(SessionFactory sessionFactory);

	public abstract void save(Notification notification);

	public abstract Notification findById(Integer id);

	public abstract List<NotificationDTO> findAllNotification(int memid, int page);

	public abstract void changeStatus(int memId);

	public abstract Long findNotificationCount(int memId);

}
