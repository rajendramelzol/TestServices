package com.melzol.services.dao;

import java.util.List;

import javax.sql.DataSource;
import javax.transaction.Transactional;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.orm.hibernate4.HibernateTemplate;
import org.springframework.orm.hibernate4.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;

import com.melzol.services.beans.TopicsDTO;
import com.melzol.services.model.Topics;
import com.melzol.services.rowmappers.TopicsRowMapper;

@Repository("topicsDAO")
@Transactional
public class TopicsDAOImpl extends HibernateDaoSupport implements TopicsDAO {
	private static final Log log = LogFactory.getLog(TopicsDAOImpl.class);
	@Autowired
	private SessionFactory sessionFactory;
	private JdbcTemplate jdbcTemplate;
	
	  public TopicsDAOImpl() {
	       super();
	    }
	    
		@Autowired
	    public TopicsDAOImpl(DataSource dataSource) {
	        jdbcTemplate = new JdbcTemplate(dataSource);
	    }

	
	public JdbcTemplate getJdbcTemplate() {
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
	@Override
	@Autowired
	protected HibernateTemplate createHibernateTemplate(
			SessionFactory sessionFactory) {
		return super.createHibernateTemplate(sessionFactory);
	}
	public void save(Topics topics) {
	try {
		sessionFactory.getCurrentSession().save(topics);
	} catch (RuntimeException e) {
		e.printStackTrace();
	}
		
	}
	public TopicsDTO findById(Integer topicId) {
		TopicsDTO topic = null;
		String sql="SELECT T.topic_id,T.title,T.description,T.ref_id,T.ref_type,T.created_by,T.created_ts,M.user_name,M.profile_pic "
				+ "FROM topics T left outer join members M on T.created_by=M.member_id where T.topic_id=? "
				+ " order by T.created_ts desc";
		//try{
		topic=jdbcTemplate.queryForObject(sql, new Object[]{topicId},new TopicsRowMapper());
		//}catch(Exception e){
			System.out.println("error in joinedevents"+topicId);
		//}
		return  topic;
	}
	public List<TopicsDTO> findAll( int refId,int refType,int start) {
		List<TopicsDTO> list = null;
		String sql="SELECT T.topic_id,T.title,T.description,T.ref_id,T.ref_type,T.created_by,T.created_ts,M.user_name,M.profile_pic "
				+ "FROM topics T left outer join members M on T.created_by=M.member_id where T.ref_id=? and T.ref_type=? "
				+ " order by T.created_ts desc LIMIT 10 OFFSET ?";
		//try{
		list=jdbcTemplate.query(sql, new Object[]{refId,refType,start},new TopicsRowMapper());
		//}catch(Exception e){
			System.out.println("error in joinedevents"+list);
		//}
		return  list;
	}

}
