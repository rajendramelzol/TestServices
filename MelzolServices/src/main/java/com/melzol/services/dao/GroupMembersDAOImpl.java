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
import org.springframework.orm.hibernate4.support.HibernateDaoSupport;
import org.springframework.orm.hibernate4.HibernateTemplate;
import org.springframework.stereotype.Repository;

import com.melzol.services.model.GroupMembers;

@Repository("groupMemberDAO")
@Transactional
public class GroupMembersDAOImpl extends HibernateDaoSupport implements GroupMembersDAO{
	
	private static final Log log = LogFactory.getLog(GroupMembersDAOImpl.class);
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
	
	public void save(GroupMembers persistentInstance) {
		try {
			sessionFactory.getCurrentSession().save(persistentInstance);
		} catch (RuntimeException re) {
			throw re;
		}
	}

	public List findByProperty(String propertyName, int value) {
		log.debug("finding GroupMembers instance with property: " + propertyName+ ", value: " + value);
		try {
			String queryString = "from GroupMembers as model where model."+ propertyName + "= :value";
			Session session=sessionFactory.getCurrentSession();
			Query query=session.createQuery(queryString);
			query.setParameter("value", value);
			return query.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public void update(String name, Integer groupId) {
		// TODO Auto-generated method stub
		
	}

	public void deleteGroupDetails(int groupId) {
		String sql="delete GroupMembers  where groupId=:groupId";
		try{
		Query query=sessionFactory.getCurrentSession().createQuery(sql);
			query.setParameter("groupId",groupId);
			query.executeUpdate();
		}catch(Exception e){
			System.out.println("the error in increment"+e);
		}
		
	}

	public void deleteGroupMembers(int groupid, int memid) {
		String sql="delete GroupMembers  where groupId=:groupId and memberId=:memid ";
		try{
		Query query=sessionFactory.getCurrentSession().createQuery(sql);
			query.setParameter("groupId", groupid);
			query.setParameter("memid", memid);
			query.executeUpdate();
		}catch(Exception e){
			System.out.println("the error in increment"+e);
		}
		
	}


}
