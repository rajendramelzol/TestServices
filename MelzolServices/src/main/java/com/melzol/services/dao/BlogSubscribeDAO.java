package com.melzol.services.dao;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.jdbc.core.JdbcTemplate;

import com.melzol.services.beans.MemberDTO;
import com.melzol.services.model.BlogSubscribe;
import com.melzol.services.model.Members;

public interface BlogSubscribeDAO {
	
public abstract JdbcTemplate getJdbcTemplate();
	
	public abstract void setJdbcTemplate(JdbcTemplate jdbcTemplate);
	
	public abstract void init(SessionFactory sessionFactory);

	public abstract void subscribe(BlogSubscribe blogSubscribe);

	public abstract List<MemberDTO> findSubscribers(int memId, int start);

	public abstract List<Members> findMySubscribers(int memId, int start);

}
