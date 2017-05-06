package com.melzol.services.dao;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.jdbc.core.JdbcTemplate;

import com.melzol.services.beans.GroupDTO;
import com.melzol.services.model.Group;

public interface GroupDAO {
	
	public abstract JdbcTemplate getJdbcTemplate();

	public abstract void setJdbcTemplate(JdbcTemplate jdbcTemplate);

	public abstract void init(SessionFactory sessionFactory);

	public abstract void save(Group group);

	public abstract Group findById(Integer groupId);

	public abstract void update(Group group);

	public abstract void delete(Group group);

	public abstract List<GroupDTO> findGroupsByCat(String city, int cat, int start);

	public abstract GroupDTO findGroupView(int id, int memid);

	public abstract List<GroupDTO> findMyGroups(int memId, int start);

	public abstract List<GroupDTO> findMemberCreatedGroups(int memId, int start);

}
