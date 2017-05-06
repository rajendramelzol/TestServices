package com.melzol.services.dao;

import java.util.List;

import javax.transaction.Transactional;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;

import com.melzol.services.model.WorkingDays;

@Repository("workingDaysDAO")
@Transactional
public class WorkingDaysDAOImpl extends HibernateDaoSupport implements WorkingDaysDAO{
	
	private static final Log log = LogFactory.getLog(WorkingDaysDAOImpl.class);
	@Autowired
	private SessionFactory sessionFactory;
	private JdbcTemplate jdbcTemplate;

	
	public JdbcTemplate geJdbcTemplate() {
		return jdbcTemplate;
	}

	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate=jdbcTemplate;
		
	}

	@Autowired
	public void init(SessionFactory sessionFactory) {
		setSessionFactory(sessionFactory);
		this.sessionFactory=sessionFactory;
		
	}
	public void save(WorkingDays workingDays) {
	try {
		sessionFactory.getCurrentSession().save(workingDays);
	} catch (RuntimeException e) {
		
		e.printStackTrace();
	}
		
	}

	public List<WorkingDays> findAll(Integer refId, int refType) {
		String sql="FROM WorkingDays where refId=:refId AND refType=:refType";
		Query query=getSessionFactory().getCurrentSession().createQuery(sql);
		query.setParameter("refId", refId);
		query.setParameter("refType", refType);
		return query.list();
	}


	public void updateWorikingDays(WorkingDays w) {
		Session s=sessionFactory.getCurrentSession();
		WorkingDays wrk=(WorkingDays) s.load(WorkingDays.class,w.getId());
		
		wrk.setStartDate(w.getStartDate());
		wrk.setEndDate(w.getEndDate());
		wrk.setDays(w.getDays());
		wrk.setStartTime(w.getStartTime());
		wrk.setEndTime(w.getEndTime());
		wrk.setImageId(w.getImageId());
	
		s.update(wrk);
		
	}

}
