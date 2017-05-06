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


import com.melzol.services.model.JoiningsOpinions;

@Repository("joiningsOpinionsDAO")
@Transactional
public class JoiningsOpinionsDAOImpl extends HibernateDaoSupport implements JoiningsOpinionsDAO{
	private static final Log log = LogFactory.getLog(JoiningsOpinionsDAOImpl.class);
	@Autowired
	private SessionFactory sessionFactory;
    private JdbcTemplate jdbcTemplate;
	
    public JoiningsOpinionsDAOImpl() {
	       super();
	    }
	    
		@Autowired
	    public JoiningsOpinionsDAOImpl(DataSource dataSource) {
	        jdbcTemplate = new JdbcTemplate(dataSource);
	    }
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
	
	public void save(JoiningsOpinions jp) {
		String sql="INSERT INTO joinings_opinions (ref_id, member_id,ref_type,activity_type,join_date) VALUES (?,?,?,?,?) ON DUPLICATE KEY UPDATE activity_type=?";
		//try{
		jdbcTemplate.update(sql,new Object[]{jp.getRefId(),jp.getMemberId(),jp.getRefType(),jp.getActivityType(),jp.getJoinDate(),jp.getActivityType()});
		/*}catch(Exception e){
			System.out.println("error in joinedevents"+e);
		}*/
	}

	public void memberOpinion(int gossipId,int memId,int opinion) {
		String sql="update GossipMembers set opinion = :opinion  where gossipId=:gossipId and memberId=:memId";
		try{
		Query query=sessionFactory.getCurrentSession().createQuery(sql);
		query.setParameter("opinion", opinion);
		query.setParameter("gossipId", gossipId);
		query.setParameter("memId", memId);
		query.executeUpdate();
		}catch(Exception e){
			System.out.println("the error in increment"+e);
		}
		
	}

	public void exitGossip(int gossipId, int memId) {
		String sql="update GossipMembers set activityType = 2  where gossipId=:gossipId and memberId=:memId";
		try{
		Query query=sessionFactory.getCurrentSession().createQuery(sql);
		query.setParameter("gossipId", gossipId);
		query.setParameter("memId", memId);
		query.executeUpdate();
		}catch(Exception e){
			System.out.println("the error in increment"+e);
		}
		
		
	}
	public void opinionOnBlog(JoiningsOpinions jp) {
		String sql="INSERT INTO joinings_opinions (ref_id,member_id,ref_type,opinion,join_date) VALUES (?,?,?,?,?) ON DUPLICATE KEY UPDATE opinion=?";
		try{
		jdbcTemplate.update(sql,new Object[]{ jp.getRefId(),jp.getMemberId(),jp.getRefType(),jp.getOpinion(),jp.getJoinDate(),jp.getOpinion()});
		}catch(Exception e){
			System.out.println("error in joinedevents"+e);
		}
		
	}

}
