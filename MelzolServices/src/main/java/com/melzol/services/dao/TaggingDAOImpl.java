package com.melzol.services.dao;

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

import com.melzol.services.model.Tagging;

@Repository("taggingDAO")
@Transactional
public class TaggingDAOImpl extends HibernateDaoSupport implements TaggingDAO{
	
	private static final Log log = LogFactory.getLog(TaggingDAOImpl.class);
	@Autowired
	private SessionFactory sessionFactory;
    private JdbcTemplate jdbcTemplate;
	
    public TaggingDAOImpl() {
        super();
     }
    @Autowired
    public TaggingDAOImpl(DataSource dataSource) {
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
	public void save(Tagging persistentInstance){
		try {
			sessionFactory.getCurrentSession().save(persistentInstance);
		} catch (RuntimeException re) {
			throw re;
		}
	}
	
	public void findUpdateTagRequest(int senderId,int receiverId,int status) {
		Query query = currentSession().createQuery("UPDATE Tagging SET status =:status where  melzolId=:senderId and tagMelzolId=:receiverId");
		query.setInteger("status", status);
		query.setInteger("senderId", senderId);
		query.setInteger("receiverId", receiverId);
		query.executeUpdate();
		
		
	}

}
