package com.melzol.services.dao;

import java.util.List;

import javax.sql.DataSource;
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

import com.melzol.services.model.Chatting;
import com.melzol.services.model.Notification;

@Repository("chattingDAO")
@Transactional
public class ChattingDAOImpl extends HibernateDaoSupport implements ChattingDAO{
	private static final Log log = LogFactory.getLog(ChattingDAOImpl.class);
	@Autowired
	private SessionFactory sessionFactory;
    private JdbcTemplate jdbcTemplate;
	
    public ChattingDAOImpl() {
       super();
    }
    
	@Autowired
    public ChattingDAOImpl(DataSource dataSource) {
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

	public void save(Chatting chatting) {
		try {
			sessionFactory.getCurrentSession().save(chatting);
		} catch (RuntimeException re) {
			throw re;
		}
		
	}
	public List findAllChatting(int memId ,int id) {
		//try {
			String queryString = "from Chatting where (senderId =:senderId AND receiverId =:receiverId) or (senderId =:sender AND receiverId =:receiver) order by createdTs asc)";
			Session session=sessionFactory.getCurrentSession();
			Query query= session.createQuery(queryString);
			query.setParameter("senderId", memId);
			query.setParameter("receiverId", id);
			query.setParameter("sender", id);
			query.setParameter("receiver", memId);
			return query.list();
		/*} catch (RuntimeException re) {
			throw re;
		}*/
	}


}
