package com.melzol.services.dao;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.jdbc.core.JdbcTemplate;

import com.melzol.services.model.MemberNeighbourhoods;

public interface MemberNeighbourhoodsDAO {
	
	public abstract JdbcTemplate getJdbcTemplate();

	public abstract void setJdbcTemplate(JdbcTemplate jdbcTemplate);

	public abstract void init(SessionFactory sessionFactory);

	public abstract void save(MemberNeighbourhoods memberNeighbourhoods);

	public abstract List<MemberNeighbourhoods> findMemberNeighbourhoods(int memId);

}
