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

import com.melzol.services.beans.MemberDTO;
import com.melzol.services.beans.MemberRowMapper;
import com.melzol.services.model.BlogSubscribe;
import com.melzol.services.model.Members;
import com.melzol.services.rowmappers.BlogSubscriberRowMapper;

@Repository("blogSubscribeDAO")
@Transactional
public class BlogSubscribeDAOImpl extends HibernateDaoSupport implements BlogSubscribeDAO{
	private static final Log log = LogFactory.getLog(BlogSubscribeDAOImpl.class);
	@Autowired
	private SessionFactory sessionFactory;
	private JdbcTemplate jdbcTemplate;
	
	 public BlogSubscribeDAOImpl() {
	       super();
	    }
	    
		@Autowired
	    public BlogSubscribeDAOImpl(DataSource dataSource) {
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
		this.jdbcTemplate=jdbcTemplate;
		
	}
	@Override
	@Autowired
	protected HibernateTemplate createHibernateTemplate(
			SessionFactory sessionFactory) {
		return super.createHibernateTemplate(sessionFactory);
	}
	public void subscribe(BlogSubscribe bs) {
	String sql="INSERT INTO blog_subscribe (melzol_id, subscribe_id,status,subscribe_ts) VALUES (?,?,?,?) ON DUPLICATE KEY UPDATE status=?";
	//try{
	jdbcTemplate.update(sql,new Object[]{bs.getMelzolId(),bs.getSubscribeId(),bs.getStatus(),bs.getSubscribeTs(),bs.getStatus()});
	/*}catch(Exception e){
		System.out.println("error in joinedevents"+e);
	}*/
		
	}

	public List<MemberDTO> findSubscribers(int memId, int start) {
		List<MemberDTO> list = null;
		String sql="SELECT M.member_id,M.user_name,M.profile_pic,M.gcm_key,M.latitude,M.longitude,"
				+ "(select count(B.created_by) from blogs B where M.member_id=B.created_by) as blogcount "
				+ " FROM members M,blog_subscribe BS WHERE BS.melzol_id=? AND M.member_id=BS.subscribe_id "
				+ "AND BS.status=1 order by BS.subscribe_ts desc  LIMIT 10 OFFSET ?";
		try{
		list=jdbcTemplate.query(sql,new Object[]{memId,start},new BlogSubscriberRowMapper());
		System.out.println("the result in tagged members xooo838"+list);
		}catch(Exception e){
			System.out.println("error in InviteMembers"+e);
		}
		return  list;
	}

	public List<Members> findMySubscribers(int memId, int start) {
		List<Members> list = null;
		String sql="SELECT M.member_id,M.user_name,M.profile_pic,M.gcm_key,M.latitude,M.longitude "
				+ "FROM members M,blog_subscribe BS WHERE BS.subscribe_id=? AND M.member_id=BS.melzol_id AND "
				+ "BS.status=1 order by BS.subscribe_ts desc  LIMIT 10 OFFSET ?";
		try{
		list=jdbcTemplate.query(sql,new Object[]{memId,start},new MemberRowMapper());
		System.out.println("the result in tagged members xooo838"+list);
		}catch(Exception e){
			System.out.println("error in InviteMembers"+e);
		}
		return  list;
	}
}
