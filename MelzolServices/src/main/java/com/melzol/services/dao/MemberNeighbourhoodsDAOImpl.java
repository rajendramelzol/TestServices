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
import com.melzol.services.model.MemberNeighbourhoods;

@Repository("memberNeighbourhoodsDAO")
@Transactional
public class MemberNeighbourhoodsDAOImpl extends HibernateDaoSupport implements MemberNeighbourhoodsDAO{
	private static final Log log = LogFactory.getLog(MemberNeighbourhoodsDAOImpl.class);
	@Autowired
	private SessionFactory sessionFactory;
    private JdbcTemplate jdbcTemplate;
	
    public MemberNeighbourhoodsDAOImpl() {
       super();
    }
    
	@Autowired
    public MemberNeighbourhoodsDAOImpl(DataSource dataSource) {
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

	public void save(MemberNeighbourhoods memberNeighbourhoods) {
		try {
			sessionFactory.getCurrentSession().save(memberNeighbourhoods);
		} catch (RuntimeException re) {
			throw re;
		}
		
	}
	public List findMemberNeighbourhoods(int memId) {
		//try {
			String queryString = "from MemberNeighbourhoods where memberId =:memberId order by createdTs desc)";
			Session session=sessionFactory.getCurrentSession();
			Query query= session.createQuery(queryString);
			query.setParameter("memberId", memId);
			return query.list();
		/*} catch (RuntimeException re) {
			throw re;
		}*/
	}


}
