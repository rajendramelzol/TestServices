package com.melzol.services.dao;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.jdbc.core.JdbcTemplate;

import com.melzol.services.model.GroupMembers;

public interface GroupMembersDAO {
	
	public abstract JdbcTemplate getJdbcTemplate();

	public abstract void setJdbcTemplate(JdbcTemplate jdbcTemplate);

	public abstract void init(SessionFactory sessionFactory);

	public abstract void save(GroupMembers groupMembers);

	public abstract List<GroupMembers> findByProperty(String string, int id);

	public abstract void update(String name, Integer groupId);

	public abstract void deleteGroupDetails(int id);

	public abstract void deleteGroupMembers(int groupid, int memid);
	

}
