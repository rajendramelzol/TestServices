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
import org.springframework.orm.hibernate4.HibernateTemplate;
import org.springframework.orm.hibernate4.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;

import com.melzol.services.model.EventMembers;

@Repository("eventMemberDAO")
@Transactional
public class EventMembersDAOImpl extends HibernateDaoSupport implements EventMembersDAO {
	
	private static final Log log = LogFactory.getLog(EventMembersDAOImpl.class);
	@Autowired
	private SessionFactory sessionFactory;
    private JdbcTemplate jdbcTemplate;
	
	
	public JdbcTemplate getJdbcTemplate() {
		return jdbcTemplate;
	}

	protected void initDao() {
		// do nothing
	}
	@Autowired
	public void init(SessionFactory sessionFactory) {
	    setSessionFactory(sessionFactory);
	    this.sessionFactory=sessionFactory;
	}
	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}
	
	@Override
	@Autowired
	protected HibernateTemplate createHibernateTemplate(
			SessionFactory sessionFactory) {
		return super.createHibernateTemplate(sessionFactory);
	}
	
	public void save(EventMembers persistentInstance) {
		try {
			sessionFactory.getCurrentSession().save(persistentInstance);
		} catch (RuntimeException re) {
			throw re;
		}
	}

	public List findByProperty(String propertyName, int value) {
		log.debug("finding Comments instance with property: " + propertyName+ ", value: " + value);
		try {
			String queryString = "from EventMembers as model where model."+ propertyName + "= :value";
			Session session=sessionFactory.getCurrentSession();
			Query query=session.createQuery(queryString);
			query.setParameter("value", value);
			return query.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public void deleteEventMembers(int eventid, int memid) {
		String sql="delete EventMembers  where eventId=:eventId and memberId=:memid ";
		try{
		Query query=sessionFactory.getCurrentSession().createQuery(sql);
			query.setParameter("eventId", eventid);
			query.setParameter("memid", memid);
			query.executeUpdate();
		}catch(Exception e){
			System.out.println("the error in increment"+e);
		}
			
		}

	public void update(String eventName, Integer id) {
		String sql="update EventMembers set eventName=:eventName  where eventId=:id";
		try{
		Query query=sessionFactory.getCurrentSession().createQuery(sql);
			query.setParameter("eventName",eventName);
			query.setParameter("id",id);
			query.executeUpdate();
		}catch(Exception e){
			System.out.println(" error "+e);
		}
			
	}

	public void updateMemberName(String firstName, Integer memberId) {
		String sql="update EventMembers set memberName=:firstName  where memberId=:memberId";
		try{
		Query query=sessionFactory.getCurrentSession().createQuery(sql);
			query.setParameter("firstName",firstName);
			query.setParameter("memberId",memberId);
			query.executeUpdate();
		}catch(Exception e){
			System.out.println("error "+e);
		}
		
	}

	public void deleteEventDetails(int eventId) {
		String sql="delete EventMembers  where eventId=:eventId";
		try{
		Query query=sessionFactory.getCurrentSession().createQuery(sql);
			query.setParameter("eventId", eventId);
			query.executeUpdate();
		}catch(Exception e){
			System.out.println("the error in increment"+e);
		}
		
	}
		

}
