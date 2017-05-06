package com.melzol.services.dao;
// default package
// Generated Jun 20, 2016 5:08:51 PM by Hibernate Tools 3.4.0.CR1

import java.util.Date;
import java.util.List;

import javax.sql.DataSource;
import javax.transaction.Transactional;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.orm.hibernate4.HibernateTemplate;
import org.springframework.orm.hibernate4.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;

import com.melzol.services.beans.EventRowMapper;
import com.melzol.services.model.Event;
import com.melzol.services.util.Utils;

/**
 * Home object for domain model class Event.
 * @see .Event
 * @author Hibernate Tools
 */
@Repository("eventDAO")
@Transactional
public class EventDAOImpl extends HibernateDaoSupport implements EventDAO {

	private static final Log log = LogFactory.getLog(EventDAOImpl.class);
	@Autowired
	private SessionFactory sessionFactory;
    private JdbcTemplate jdbcTemplate;
	
    public EventDAOImpl() {
       super();
    }
    
	@Autowired
    public EventDAOImpl(DataSource dataSource) {
        jdbcTemplate = new JdbcTemplate(dataSource);
    }
	
	/* (non-Javadoc)
	 * @see com.websystique.springmvc.dao.LeadsDAO#getJdbcTemplate()
	 */
	public JdbcTemplate getJdbcTemplate() {
		return jdbcTemplate;
	}

	/* (non-Javadoc)
	 * @see com.websystique.springmvc.dao.LeadsDAO#setJdbcTemplate(org.springframework.jdbc.core.JdbcTemplate)
	 */
	protected void initDao() {
		// do nothing
	}
	/* (non-Javadoc)
	 * @see com.websystique.springmvc.dao.LeadsDAO#init(org.hibernate.SessionFactory)
	 */
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
	public void persist(Event transientInstance) {
		log.debug("persisting Event instance");
		try {
			sessionFactory.getCurrentSession().persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	public void attachDirty(Event instance) {
		log.debug("attaching dirty Event instance");
		try {
			sessionFactory.getCurrentSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Event instance) {
		log.debug("attaching clean Event instance");
		try {
			sessionFactory.getCurrentSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}
	public void save(Event persistentInstance) {
		try {
			sessionFactory.getCurrentSession().save(persistentInstance);
		} catch (RuntimeException re) {
			throw re;
		}
	}
	public void delete(Event persistentInstance) {
		log.debug("deleting Event instance");
		try {
			sessionFactory.getCurrentSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Event merge(Event detachedInstance) {
		log.debug("merging Event instance");
		try {
			Event result = (Event) sessionFactory.getCurrentSession().merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public Event findById(java.lang.Integer id) {
		log.debug("getting Event instance with id: " + id);
		try {
			Event instance = (Event) sessionFactory.getCurrentSession().get("com.melzol.services.model.Event", id);
			if (instance == null) {
				log.debug("get successful, no instance found");
			} else {
				log.debug("get successful, instance found");
			}
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}
	
	@SuppressWarnings("unchecked")
	public List<Event> findAllEvents(int category,int pincode) {
		String sql="FROM Event where category=:category and type=1 and pincode=:pincode order by fromDate desc";
		Query query=getSessionFactory().getCurrentSession().createQuery(sql);
		query.setParameter("category",category);
		query.setParameter("pincode", pincode);
		return query.list();
	}
	
	public void update(Event e) {
		Session s=sessionFactory.getCurrentSession();
		Event event=(Event) s.load(Event.class, e.getId());
		event.setEventName(e.getEventName());
		event.setCategory(e.getCategory());
		event.setFromDate(Utils.parseDate(e.getfDate()));
		event.setFtTime(e.getFtTime());
		event.setLocation(e.getLocation());
		event.setDescription(e.getDescription());
		event.setEventPic(e.getEventPic());
		event.setUpdatedDate(new Date());
		s.update(event);
	}
	public List<Event> findMyEvents(int pincode,int owner) {
		String sql="FROM Event where pincode=:pincode and owner=:owner order by fromDate desc";
		Query query=getSessionFactory().getCurrentSession().createQuery(sql);
		query.setParameter("pincode", pincode);
		query.setParameter("owner", owner);
		return query.list();
	}

	public void incrementCount(Integer eventId) {
		
		String sql="update Event set memberCount = IFNULL(memberCount, 0) + 1  where id=:eventId";
		try{
		Query query=sessionFactory.getCurrentSession().createQuery(sql);
			query.setParameter("eventId", eventId);
			query.executeUpdate();
		}catch(Exception e){
			System.out.println("the error in increment"+e);
		}
			
		}

	public void decrementCount(int eventid) {
		String sql="update Event set memberCount = IFNULL(memberCount, 0) - 1  where id=:eventId";
		try{
		Query query=sessionFactory.getCurrentSession().createQuery(sql);
			query.setParameter("eventId", eventid);
			query.executeUpdate();
		}catch(Exception e){
			System.out.println("the error in increment"+e);
		}
		
	}

	public List<Event> joinedEvents(int pincode, int memid) {
		List<Event> list = null;
		String sql="select E.id,E.event_name,E.member_count,E.status,E.from_date from event_members EM ,event E where EM.member_id=? and EM.owner !=? and EM.pincode=? AND EM.EVENT_ID=E.ID order by E.from_date desc";
		try{
		list=jdbcTemplate.query(sql,new Object[]{memid ,memid,pincode},new EventRowMapper());
		}catch(Exception e){
			System.out.println("error in joinedevents"+e);
		}
		return  list;
		 
	}

	public List<Event> findCityEvents(int category, String city) {
		String sql="FROM Event where category=:category and type=2 and city=:city order by fromDate desc";
		Query query=getSessionFactory().getCurrentSession().createQuery(sql);
		query.setParameter("category",category);
		query.setParameter("city",city);
		return query.list();
	}
		

}
