package com.melzol.services.dao;

import java.util.Date;
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

import com.melzol.services.beans.CommentsDTO;
import com.melzol.services.model.Blogs;
import com.melzol.services.model.Comments;
import com.melzol.services.rowmappers.CommentsRowMapper;

@Repository("commentsDAO")
@Transactional
public class CommentsDAOImpl extends HibernateDaoSupport implements CommentsDAO {
	private static final Log log = LogFactory.getLog(CommentsDAOImpl.class);
	@Autowired
	private SessionFactory sessionFactory;
	private JdbcTemplate jdbcTemplate;
	  public CommentsDAOImpl() {
	       super();
	    }
	    
		@Autowired
	    public CommentsDAOImpl(DataSource dataSource) {
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
	public void save(Comments comments) {
	try {
		sessionFactory.getCurrentSession().save(comments);
	} catch (RuntimeException e) {
		e.printStackTrace();
	}
		
	}
	public Comments findById(Date createdTs) {
		log.debug("getting Comments instance with id: " + createdTs);
		try {
			Comments instance = (Comments) sessionFactory.getCurrentSession().get("com.melzol.services.model.Comments", createdTs);
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
	public void delete(Comments comments) {
		log.debug("deleting blog instance");
		try {
			sessionFactory.getCurrentSession().delete(comments);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}	
	}

	public List<CommentsDTO> findAll( int refId,int refType,int start) {
		List<CommentsDTO> list = null;
		String sql="SELECT C.ref_id,C.ref_type,C.comment,C.commented_by,C.created_ts,M.user_name,M.profile_pic "
				+ "FROM comments C left outer join members M on C.commented_by=M.member_id where C.ref_id=? and C.ref_type=? "
				+ " order by C.created_ts desc LIMIT 10 OFFSET ?";
		//try{
		list=jdbcTemplate.query(sql, new Object[]{refId,refType,start},new CommentsRowMapper());
		//}catch(Exception e){
			System.out.println("error in joinedevents"+list);
		//}
		return  list;
	}


}
