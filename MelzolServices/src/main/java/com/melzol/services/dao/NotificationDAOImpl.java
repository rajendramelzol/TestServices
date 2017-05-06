package com.melzol.services.dao;

import java.util.List;

import javax.sql.DataSource;
import javax.transaction.Transactional;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.orm.hibernate4.HibernateTemplate;
import org.springframework.orm.hibernate4.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;

import com.melzol.services.beans.NotificationDTO;
import com.melzol.services.model.Notification;
import com.melzol.services.rowmappers.NotificationRowMapper;

@Repository("notificationDAO")
@Transactional
public class NotificationDAOImpl extends HibernateDaoSupport implements NotificationDAO{
	private static final Log log = LogFactory.getLog(NotificationDAOImpl.class);
	@Autowired
	private SessionFactory sessionFactory;
    private JdbcTemplate jdbcTemplate;
	
    public NotificationDAOImpl() {
       super();
    }
    
	@Autowired
    public NotificationDAOImpl(DataSource dataSource) {
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

	public void save(Notification notification) {
		try {
			sessionFactory.getCurrentSession().save(notification);
		} catch (RuntimeException re) {
			throw re;
		}
		
	}

	public Notification findById(Integer id) {
		log.debug("getting Notification instance with id: " + id);
		try {
			Notification instance = (Notification) sessionFactory.getCurrentSession().get("com.melzol.services.model.Notification", id);
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

	public List<NotificationDTO> findAllNotification(int memid, int page) {
		List<NotificationDTO> list = null;
		String sql="SELECT N.*,M.profile_pic,M.user_name "
				+ " FROM notification N, members M WHERE N.sender_id=M.member_id  AND N.receiver_id=? "
				+ "order by N.created_ts desc LIMIT 10 OFFSET ?";
		try{
			list=jdbcTemplate.query(sql,new Object[]{memid,page},new NotificationRowMapper());
			}catch(Exception e){
				System.out.println("error in joinedevents"+e);
			}
			return  list; 
	}

	public void changeStatus(int memId) {
		String sql="update Notification set status = 1  where receiverId=:receiverId";
		try{
		Query query=sessionFactory.getCurrentSession().createQuery(sql);
			query.setParameter("receiverId", memId);
			query.executeUpdate();
		}catch(Exception e){
			System.out.println("the error "+e);
		}
	}

	public Long findNotificationCount(int memId) {
		Query query = currentSession().createQuery(
		        "select count(*) from Notification  where receiverId=:receiverId and status= 2");
		query.setInteger("receiverId", memId);
		
		return (Long) query.uniqueResult();
		//return count;
	}

}
